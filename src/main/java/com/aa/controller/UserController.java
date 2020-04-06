package com.aa.controller;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 嘉宇 on 2020/4/4.
 */
@Controller
public class UserController {
     @RequestMapping("/hello")
     @ResponseBody
    public  String hello(){
        System.out.println("User.Hello");
        return  "ok";

    }
    @RequestMapping("/add")

    public  String add(){
        return  "user/add";
    }
    @RequestMapping("/update")
    public  String update(){
        return  "user/update";

    }
    @RequestMapping("/tologin")
    public  String tologin(){
        return  "/login";
    }
    @RequestMapping("/noAuth")
    public  String noAuth(){
        return  "/noAuth";
    }
    /*
    * 测试thy
    * */
    @RequestMapping("/thymeleaf")
   // @ResponseBody
    public  String testThymeleaf(Model model){
        //把数据存入model
        model.addAttribute("name","嘉鱼");
        //返回test.html
        return  "test";

    }

    /*
    * 登陆逻辑
    * */
    @RequestMapping("/login")
    public  String login(String name,String password,Model model){
        System.out.println("name:"+name);
        System.out.println("passwore:"+password);
        /*
        * 使用shrio 验证认证
        * */
        //1.获得Subject
        Subject subject = SecurityUtils.getSubject();
        //2.获得用户密码
         UsernamePasswordToken token  =new  UsernamePasswordToken(name,password);
        //3.执行登陆方法
        try {
            subject.login(token);
            return  "redirect:/thymeleaf";
        }catch (UnknownAccountException e){
            //不存在
            model.addAttribute("msg","用户不存在");
            return  "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return  "login";
        }
    }
}
