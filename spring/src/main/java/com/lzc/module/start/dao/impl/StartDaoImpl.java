package com.lzc.module.start.dao.impl;

import com.lzc.module.start.dao.StartDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class StartDaoImpl implements StartDao, InitializingBean, DisposableBean {

    private int startNum;

    private String startName;

    public StartDaoImpl(String startNameInput) {
        this.startName = startNameInput;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    @Override
    public void start() {
        System.out.println("StartDaoImpl start , start num : " + startNum + ", start name : " + startName);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("StartDaoImpl destroy");
    }

    /**
     * 属性设置完之后才会执行这个方法，set之类的
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("StartDaoImpl init");
    }
}
