package com.lin.shiro.core.service;


import com.lin.shiro.core.entity.shiro.User;

import java.util.Set;

/**
 * UserService  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public interface UserService {

    Set<String> findRoles(String username);// 根据用户名查找其角色

    Set<String> findPermissions(String username); //根据用户名查找其权限

    public Set<String> findPermissionsByRole(String role_id); // 根据角色id查找权限

    User findByUsername(String username);// 根据用户名查找用户

//
//    public User createUser(User user); //创建账户
//    public void changePassword(String user_id, String newPassword);//修改密码
//    public void correlationRoles(String user_id, String... role_ids); //添加用户-角色关系
//    public void uncorrelationRoles(String user_id, String... role_ids);// 移除用户-角色关系





}
