package com.lin.shiro.core.service.impl;


import com.lin.shiro.core.dao.PermissionMapper;
import com.lin.shiro.core.dao.RoleMapper;
import com.lin.shiro.core.dao.UserMapper;
import com.lin.shiro.core.entity.shiro.Role;
import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.UserService;
import com.lin.shiro.core.util.shiro.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserServiceImpl  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;


    //根据用户名返回角色名,流程:查到user,根据userid查到管理的roleid,根据roleid查到role名
    @Override
    public Set<String> findRoles(String username) {

        User user = userMapper.findByUsername(username);
        if (user == null){
            return null;
        }
        //找到该用户的角色id 集合
        List<String> roles_ids = roleMapper.findRolesByUserIds(user.getUser_id());
        List<Role> roles =  roleMapper.findRoleByRids(roles_ids);
        Set<String> role_name = new HashSet<String>();
        for (int i=0 ; i<roles.size(); i++){
            role_name.add(roles.get(i).getName());
        }
       return role_name;
    }

    //根据用户名返回该用户权限名 , 流程:根据用户名找到用户,根据用户id找到关联的roleid ,
    // 关联的roleid找到管理的permissionid,根据id找到permission的name
    @Override
    public Set<String> findPermissions(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null){
            return null;
        }
        //找到该用户的角色id 集合
        List<String>  roles_ids = roleMapper.findRolesByUserIds(user.getUser_id());

        Set<String> permi = new HashSet<>();
        for (String s : roles_ids){
            Set<String> ps = findPermissionsByRole(s);
            permi.addAll(ps);
        }
        return permi;

    }

    //根据角色id ,返回角色的权限 name 的集合
    @Override
    public Set<String> findPermissionsByRole(String role_id) {

      //  List<Role>  roles = roleMapper.findRoles(user.getUser_id());
        //得到权限id 集合
        Set<String> permi_id = permissionMapper.findPermissions(role_id);
        Set<String> permi_name = permissionMapper.findPeNamesByPeIds(permi_id);
        return permi_name;
    }

    //根据用户名返回用户
    @Override
    public User findByUsername(String username) {

        User user = userMapper.findByUsername(username);
        if (user == null){
            return null;
        }
        return user;
    }

//    //创建用户
//    @Override
//    public User createUser(User user) {
//        PasswordHelper.encryptPassword(user);
//        int i =1;
//        i = userMapper.createUser(user);
//        return i<0 ? null : user;
//    }


}
