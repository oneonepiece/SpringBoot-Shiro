package com.lin.shiro.core.controller.blog;

import com.lin.shiro.core.entity.shiro.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;

/**
 * UpdateController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Controller
public class UpdateController {


    @RequestMapping("/user/update")
    public String userUpdate(User user, ServletRequest request){

        Subject subject = SecurityUtils.getSubject();
        //noruser 普通用户
        if (subject.hasRole("noruser")){
            //有权限
            System.out.println("我是普通用户,");
        }
        //管理员
        if (subject.hasRole("manage")){

            System.out.println("我是管理员");
        }
        //管理员
        if (!subject.hasRole("abcdefg")){
            System.out.println("是的,我不是abcdefg用户");
        }
        if(subject.isPermitted("delete")){
            System.out.println("是的,我有delete权限");
        }

        return "/index";
    }


}
