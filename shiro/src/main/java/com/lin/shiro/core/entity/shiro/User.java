package com.lin.shiro.core.entity.shiro;

import java.util.Date;

/**
 * User  功能描述
 *
 * @Author Lin
 * @Description //TODO $  user--role
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class User {

    private int id;
    private String user_id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String phone;
    private int sex;
    private int age;
    private int status; //用户状态
    private Date create_time;
    private Date update_time;
    private Date last_login_time; //最后登录时间


    public void user(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }
    public String getSaltAndUsername(){
        return username + salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }
}
