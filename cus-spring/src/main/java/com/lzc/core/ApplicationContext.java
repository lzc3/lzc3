package com.lzc.core;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.lzc.Constant.SINGLETON;

@Slf4j
public class ApplicationContext {

    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> singletonObjectMap = new ConcurrentHashMap<>();

    private final ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    /**
     * 扫描configClass注解中ComponentScan对应值的包
     * 遍历包中的每个class，判断是否包含Component注解，构造beanDefinition放入beanDefinitionMap; 判断是否为BeanPostProcessor，是的话，beanPostProcessorList
     * 根据beanDefinitionMap初始化单例bean
     *
     * @param configClass configClass
     */
    @SneakyThrows
    public ApplicationContext(Class<?> configClass) {
        String pathValue = Optional.ofNullable(configClass)
                .filter(item -> item.isAnnotationPresent(ComponentScan.class))
                .map(item -> item.getAnnotation(ComponentScan.class))
                .map(ComponentScan::value)
                .map(value -> value.replace(".", "/"))
                .orElse(null);
        if (StringUtils.isEmpty(pathValue)) {
            log.info("未根据configClass找到ComponentScan扫描路径");
            return;
        }

        List<String> classNames = collectClassNames(pathValue);
        if (CollectionUtils.isEmpty(classNames)) {
            log.info("ComponentScan扫描路径下无class文件");
            return;
        }

        ClassLoader classLoader = ApplicationContext.class.getClassLoader();
        for (String className : classNames) {
            generateBeanDefinition(classLoader, className);
        }

        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (SINGLETON.equals(beanDefinition.getScope())) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjectMap.put(beanName, bean);
            }
        }

    }

    @SneakyThrows
    protected List<String> collectClassNames(String pathValue) {
        List<String> classNames = new ArrayList<>();
        ClassLoader classLoader = ApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(pathValue);
        Path path = Paths.get(resource.toURI());
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                String parentPathName = path.getParent().toString();
                parentPathName = parentPathName.substring(parentPathName.indexOf("com"));
                parentPathName = parentPathName.replace("\\", ".");

                String pathName = path.getFileName().toString();
                String className = parentPathName + "." + pathName.substring(0, pathName.indexOf(".class"));
                classNames.add(className);
                return FileVisitResult.CONTINUE;
            }
        });
        return classNames;
    }

    @SneakyThrows
    private void generateBeanDefinition(ClassLoader classLoader, String className) {
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
    }

    @SneakyThrows
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getType();
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
