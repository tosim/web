package top.tosim.eqmanager.service;

import top.tosim.eqmanager.entity.User;

public interface UserService {
    public void userRegist(User user);
    public User loginCheck(String loginName,String password);
}
