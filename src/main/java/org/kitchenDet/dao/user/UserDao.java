package org.kitchenDet.dao.user;

import org.kitchenDet.pojo.User;
import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public User getLoginUser(Connection connection, String userCode) throws SQLException;
    public int updatePwd(Connection connection,String id, String password) throws SQLException;
}
