package com.lzc.module.start.factory;

import com.lzc.module.start.service.StartService;
import com.lzc.module.start.service.impl.StartServiceImpl;
import org.springframework.beans.factory.FactoryBean;

public class StartServiceFactoryBean implements FactoryBean<StartService> {
    @Override
    public StartService getObject() throws Exception {
        return new StartServiceImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return StartService.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
