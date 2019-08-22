package com.lin.shiro.core.controller.blog;

import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.UserService;
import com.lin.shiro.core.util.Result;
import com.lin.shiro.core.util.ResultGenerator;
import com.lin.shiro.core.util.shiro.PasswordHelper;
import javafx.beans.binding.ObjectExpression;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * IndexController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Controller
public class IndexController {

    @Autowired
    UserService userService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index(HttpServletRequest request) {
        return  "index";
    }

    /**
     * Login
     *
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        //如果已经登录
        if(subject.isAuthenticated()){

            return "/index";
        }
        return   "/anyone/login";
    }

    //test:
//    User user1 = new User();
//        user1.setUser_id("3");
//        user1.setUsername("user1");
//        user1.setPassword("123");
//        12b6e20ad450e28f00cbbad5b5cb1e8a  b4a8cc4e5d8541c846d7d3cf583dc718
//
//    User user2 = new User();
//        user1.setUser_id("4");
//        user1.setUsername("user2");
//        user1.setPassword("123");
//        8efecea996366148a49202bc73219c24  656e10ede4e12ae2407ce04a91b522a3
    @PostMapping("/login")
    @ResponseBody
    public Result login(User loginUser , ServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(),loginUser.getPassword());
        if(!subject.isAuthenticated()){
            try {
                //没有认证就登录
                subject.login(token);
            }catch (Exception e){
                System.out.println("fail");
                return ResultGenerator.genFailResult("登录失败");
            }
        }
        //获取上一次请求路径
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = "";
        if(savedRequest != null){
            //测试发现有时候会跑到图标界面下
            if (savedRequest.getRequestUrl().equals("/favicon.ico")){
                return ResultGenerator.genSuccessResult((Object)"/index");
            }
            url = savedRequest.getRequestUrl();
        }else{
//            url = "/page/main.html";
            url = "/index";
        }
        return  ResultGenerator.genSuccessResult((Object)url);

    }


    //    @RequestMapping("/register")
//    public ModelAndView add(User user){
//        ModelAndView view = new ModelAndView();
//        userService.createUser(user);
//        view.setViewName("redirect:/login.html");
//        return view;
//    }

    @RequestMapping("/logout")
    public Result logout(User loginUser){
        Subject subject = SecurityUtils.getSubject();

        subject.logout();
        return ResultGenerator.genSuccessResult("已注销");
    }



    /**
     * 未授权
     *
     * @return
     */
    @GetMapping("/notRole")
    public String notRole(HttpServletRequest request) {
        return   "/anyone/notRole";
    }



}
