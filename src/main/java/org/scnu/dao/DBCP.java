package org.scnu.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 操作数据库的公共类
 */
public class DBCP {
    public static DruidDataSource dataSource;
    static{
        try{
            Properties pro = new Properties();
            InputStream is = DBCP.class.getClassLoader().getResourceAsStream("db.properties");
            pro.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pro);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Statement stat,Connection conn){
        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void close(ResultSet rs,Statement stat,Connection conn){
        close(stat, conn);
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
