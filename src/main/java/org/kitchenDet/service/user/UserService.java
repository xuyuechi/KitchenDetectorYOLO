package org.kitchenDet.service.user;


import org.kitchenDet.pojo.User;

public interface UserService {
    public User login(String userCode, String password);
    public boolean modifyPwd(String id,String newPwd);
}
