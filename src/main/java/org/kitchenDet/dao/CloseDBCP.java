package org.kitchenDet.dao;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CloseDBCP implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application shutting down, closing DBCP...");
        AbandonedConnectionCleanupThread.checkedShutdown();
        BaseDao.dataSource.close();
        System.out.println("DBCP successfully closed");
        ServletContextListener.super.contextDestroyed(sce);
    }
}
