package smbms.dao.user;

import com.smbms.dao.BaseDao;
import com.smbms.pojo.User;

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
        String sql = "select * from smbms_user where userCode=?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if (connection != null) {
            Object[] params = {userCode};
            rs = BaseDao.execute(connection, sql, params, rs, pstm);
            if (rs.next()) {
                user = new User();
                user.setAddress(rs.getString("address"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getDate("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getDate("modifyDate"));
                user.setId(rs.getInt("id"));
                BaseDao.close(null, pstm, rs);
            }
        }
        return user;
    }

    @Override
    public int updatePwd(Connection connection, int id,String password) throws SQLException {
        String sql = "update smbms_user set userPassword=? where id=?";
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
