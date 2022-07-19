package org.kitchenDet.dao.user;

import org.kitchenDet.dao.BaseDao;
import org.kitchenDet.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserDAO层
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        String sql = "select * from user where ID=?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if (connection != null) {
            Object[] params = {userCode};
            rs = BaseDao.execute(connection, sql, params, rs, pstm);
            if (rs.next()) {
                user = new User();
                user.setPhone(rs.getInt("phone"));
                user.setID(rs.getString("ID"));
                user.setMail(rs.getString("mail"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                BaseDao.close(null, pstm, rs);
            }
        }
        return user;
    }

    @Override
    public int updatePwd(Connection connection, String id,String password) throws SQLException {
        String sql = "update user set password=? where ID=?";
        PreparedStatement pstm = null;
        Object[] params = {password,id};
        int result = 0;
        if (connection != null) {

            result = BaseDao.execute(connection,sql,params,pstm);
            BaseDao.close(null,pstm,null);
        }
        return result;
    }
}
