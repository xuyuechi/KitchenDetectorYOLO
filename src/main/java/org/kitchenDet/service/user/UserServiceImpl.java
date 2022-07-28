package org.kitchenDet.service.user;

import org.junit.Test;
import org.kitchenDet.dao.BaseDao;
import org.kitchenDet.dao.user.UserDao;
import org.kitchenDet.dao.user.UserDaoImpl;
import org.kitchenDet.pojo.User;
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
    public User login(String username, String password) {
        Connection connection = null;
        User user = null;

        try{
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection,username);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.close(connection,null,null);
        }
        if(user!=null && !user.getPassword().equals(password))
            user=null;
        return user;
    }

    @Override
    public boolean modifyPwd(String id, String newPwd) {
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

    @Test
    public void test(){
        User user = login("lzl","123456");
        System.out.println(user.getUsername());
    }
}
