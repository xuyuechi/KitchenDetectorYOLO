package org.scnu.service.user;

import org.scnu.dao.BaseDao;
import org.scnu.dao.DBCP;
import org.scnu.pojo.User;

import java.sql.Connection;

public interface UserService {
    public User login(String username,String password);
}
