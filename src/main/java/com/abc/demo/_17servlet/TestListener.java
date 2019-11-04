package com.abc.demo._17servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by lihj on 2019/10/27.
 */
@WebListener
public class TestListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener inital");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener destory");
    }

}
