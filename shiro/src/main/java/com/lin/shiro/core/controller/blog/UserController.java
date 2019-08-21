package com.lin.shiro.core.controller.blog;


import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;

/**
 * UserController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User loginUser, ServletRequest request){


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(),loginUser.getPassword());
        if(!subject.isAuthenticated()){

            subject.login(token);
        }
        //获取上一次请求路径
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = "";
        if(savedRequest != null){
            url = savedRequest.getRequestUrl();
        }else{
//            url = "/page/main.html";
            url = "/page/index.html";
        }
        return url;
    }

//    @RequestMapping("/register")
//    public ModelAndView add(User user){
//        ModelAndView view = new ModelAndView();
//        userService.createUser(user);
//        view.setViewName("redirect:/login.html");
//        return view;
//    }

    @RequestMapping("/logout")
    public String logout(User loginUser){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "已注销";
    }
}