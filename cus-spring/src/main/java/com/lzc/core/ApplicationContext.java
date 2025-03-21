package com.lzc.core;

import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static com.lzc.Constant.SINGLETON;

public class ApplicationContext {

    private final Class configClass;


    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> singletonObjectMap = new ConcurrentHashMap<>();

    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    /**
     * 扫描configClass注解中ComponentScan对应值的包
     * 遍历包中的每个class，判断是否包含Component注解，构造beanDefinition放入beanDefinitionMap; 判断是否为BeanPostProcessor，是的话，beanPostProcessorList
     * 根据beanDefinitionMap初始化单例bean
     * @param configClass
     */

    public ApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 扫描对应路径下的包
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value(); // 获取的是相对路径
            path = path.replace(".", "/");

            ClassLoader classLoader = ApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            if (resource != null) {
                String absolutePath = resource.getFile(); // 获取绝对路径
                File file = new File(absolutePath);
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (File f : files) {
                        String fileName = f.getAbsolutePath();

                        if (fileName.endsWith(".class")) {
                            String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                            className = className.replace("\\", ".");

                            try {
                                Class<?> clazz = classLoader.loadClass(className);
                                if (clazz.isAnnotationPresent(Component.class)) {


                                    if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                        BeanPostProcessor instance = (BeanPostProcessor)clazz.newInstance();
                                        beanPostProcessorList.add(instance);
                                    }

                                    Component componentAnnotation = clazz.getAnnotation(Component.class);
                                    String beanName = componentAnnotation.value();

                                    if (StringUtils.isEmpty(beanName)) {
                                        beanName = Introspector.decapitalize(clazz.getSimpleName());
                                    }

                                    // 创建beanDefinition
                                    BeanDefinition beanDefinition = BeanDefinition.builder()
                                            .type(clazz)
                                            .build();
                                    if (clazz.isAnnotationPresent(Scope.class)) {
                                        Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                        String scope = scopeAnnotation.value();
                                        beanDefinition.setScope(scope);
                                    } else {
                                        beanDefinition.setScope(SINGLETON);
                                    }
                                    beanDefinitionMap.put(beanName, beanDefinition);
                                }
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (InstantiationException e) {
                                throw new RuntimeException(e);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }

        }

        // 创造单例bean
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (SINGLETON.equals(beanDefinition.getScope())) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjectMap.put(beanName, bean);
            }
        }

    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        try {
            Object instance = clazz.getConstructor().newInstance();

            // 依赖注入
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    declaredField.setAccessible(true);
                    declaredField.set(instance, getBean(declaredField.getName()));
                }
            }

            // Aware
            if (instance instanceof BeanNameAware) {
                BeanNameAware beanNameAware = (BeanNameAware) instance;
                beanNameAware.setBeanName(beanName);
            }


            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(beanName, instance);
            }

            // 初始化
            if (instance instanceof InitializingBean) {
                InitializingBean initializingBean = (InitializingBean) instance;
                initializingBean.afterPropertiesSet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(beanName, instance);
            }

            // BeanPostProcessor


            return instance;
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();
            if (SINGLETON.equals(scope)) {
                Object bean = singletonObjectMap.get(beanName);
                if (bean == null) {
                    Object newBean = createBean(beanName, beanDefinition);
                    singletonObjectMap.put(beanName, newBean);
                }
                return bean;
            } else {
                return createBean(beanName, beanDefinition);
            }
        }
    }

}
