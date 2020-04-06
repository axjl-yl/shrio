package com.aa.shrio;

import com.aa.domain.User;
import com.aa.sevice.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 嘉宇 on 2020/4/4.
 */
public class UserRealm extends AuthorizingRealm {
    /*
    *  so quan luo ji
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
      //  System.out.println(info);
        //添加资源
         //info.addStringPermission("user:add");
        //info.addStringPermission("user:update");
        //去数据库查
        //当前用户Id
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject);
        User user= (User) subject.getPrincipal();
        System.out.println("id:++"+user);
        User byId = userService.findById(user.getId());
        System.out.println("dd:"+byId);
        info.addStringPermission(byId.getPrems());

        return info;
    }
/*
*  执行认证逻辑
* */
    //注入业务
    @Autowired
    private UserService userService;
    //编写realm的判断逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设数据库用户名 密码
       // String name="aaaa";
      //  String password ="1111";
        //编写shrio判断逻辑 判断用户命和密码
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;

        User user = userService.findByName(token1.getUsername());

        if (user==null){
            //用户不存在
            return  null;
        }
        //判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
