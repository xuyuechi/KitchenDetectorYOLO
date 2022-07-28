package org.kitchenDet.service.user;


import org.kitchenDet.pojo.User;

public interface UserService {
    public User login(String username, String password);
    public boolean modifyPwd(String id,String newPwd);
}
