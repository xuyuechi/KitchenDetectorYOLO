package smbms.service.user;

import com.smbms.pojo.User;

public interface UserService {
    public User login(String userCode,String password);
    public boolean modifyPwd(int id,String newPwd);
}
