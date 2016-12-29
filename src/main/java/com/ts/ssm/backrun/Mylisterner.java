package com.ts.ssm.backrun;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by ts on 2016/12/26.
 */
public class Mylisterner implements ServletContextListener{


    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.printf("线程开时启动！");
        new Thread(new Run()).start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
