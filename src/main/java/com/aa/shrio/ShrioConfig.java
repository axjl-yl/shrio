package com.aa.shrio;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 嘉宇 on 2020/4/4.
 */
@Configuration//配置类
public class ShrioConfig {
/*
* ShiroFilterFactoryBean    Shiro菲亚特 法特bean
* */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        //设置完全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shrio内置管理器
        /*anon: 无需认证
        *authc ：必须认证
        * user:rememberme的功能可以直接访问
        * perms：必须得到资源访问
        * role:角色权限访问
        * */
        Map<String,String> filerMap = new LinkedHashMap<String, String>();
       // filerMap.put("/add","authc");
       // filerMap.put("/update","authc");
        filerMap.put("/thymeleaf","anon");
        filerMap.put("/login","anon");
        //执行过滤器
        filerMap.put("/add","perms[user:add]");
        filerMap.put("/update","perms[user:update]");
        filerMap.put("/*","authc");
        //修改调整登陆页面
       shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filerMap);
        return  shiroFilterFactoryBean;
    }

    /*
    * DefaultWebSecurityManager 的发特web色特马内特
    * */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm  userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return  securityManager;
    }
    /* realm ruang目
    * */
    @Bean(name = "userRealm")
    public  UserRealm getRealm(){
        return  new  UserRealm();
    }
       /*
       * 配置shrioDialect
       * */
       @Bean
       public ShiroDialect shiroDialect() {
           return new ShiroDialect();
       }
}
