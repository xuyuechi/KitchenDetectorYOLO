package org.scnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 增删改查公共类
 */
public class BaseDao {
    public static ResultSet execute(Connection connection,String sql,Object[] params,ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for(int i=1;i<params.length;i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static int execute(Connection connection,String sql,Object[] params,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for(int i=1;i<params.length;i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

    public static boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;
        if(resultSet!=null){
            try{
                resultSet.close();
                resultSet = null;//促使垃圾回收器回收
            }catch(SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if(preparedStatement!=null){
            try{
                preparedStatement.close();
                preparedStatement = null;//促使垃圾回收器回收
            }catch(SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if(connection!=null){
            try{
                connection.close();
                connection = null;//促使垃圾回收器回收
            }catch(SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
