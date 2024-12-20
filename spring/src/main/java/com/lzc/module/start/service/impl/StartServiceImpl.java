package com.lzc.module.start.service.impl;

import com.lzc.module.start.service.StartService;
import com.lzc.module.start.dao.StartDao;

public class StartServiceImpl implements StartService {

    private StartDao startDao;

    public void setStartDao(StartDao startDao) {
        this.startDao = startDao;
    }

    @Override
    public void start() {
        System.out.println("StartServiceImpl start");

        if (startDao != null) {
            startDao.start();
        }

    }
}
