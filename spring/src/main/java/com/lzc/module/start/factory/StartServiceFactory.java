package com.lzc.module.start.factory;

import com.lzc.module.start.service.StartService;
import com.lzc.module.start.service.impl.StartServiceImpl;

public class StartServiceFactory {

    public static StartService getStartService() {
        return new StartServiceImpl();
    }

    public StartService getStartServiceNoStatic() {
        return new StartServiceImpl();
    }

}
