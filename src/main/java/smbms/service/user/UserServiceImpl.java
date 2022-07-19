package smbms.service.user;

import com.smbms.dao.BaseDao;
import com.smbms.dao.user.UserDao;
import com.smbms.dao.user.UserDaoImpl;
import com.smbms.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User业务层
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao; //业务层会调用DAO层，因此在这里引入DAO层(UserDao)

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try{
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection,userCode);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,null,null);
        }
        if(user!=null && !user.getUserPassword().equals(password))
            user=null;
        return user;
    }

    @Override
    public boolean modifyPwd(int id, String newPwd) {
        int res = 0;
        try {
            Connection conn = BaseDao.getConnection();
            res = userDao.updatePwd(conn, id, newPwd);
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(res==1)
            return true;
        else
            return false;
    }
}
