package smbms.dao.user;

import com.smbms.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public User getLoginUser(Connection connection,String userCode) throws SQLException;
    public int updatePwd(Connection connection,int id, String password) throws SQLException;
}
