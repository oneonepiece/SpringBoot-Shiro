//package com.lin.shiro.core.config;
//
//import org.apache.shiro.authc.credential.DefaultPasswordService;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.mgt.DefaultSecurityManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public DefaultPasswordService defaultPasswordService(){
//        return new DefaultPasswordService();
//    }
//
//    @Bean
//    public CustomSecurityRealm customSecurityRealm(){
//        return new CustomSecurityRealm();
//    }
//
//    @Bean
//    public DefaultSecurityManager securityManager(){
//        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        securityManager.setRealm(customSecurityRealm());
//        return securityManager;
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
//        return new LifecycleBeanPostProcessor();
//    }
//
////    @Bean
////    public MethodInvokingFactoryBean methodInvokingFactoryBean(){
////        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
////        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
////        methodInvokingFactoryBean.setArguments(new Object[]{securityManager()});
////        return methodInvokingFactoryBean;
////    }
//
//
//
//    @Bean(name = "mysecurityManager")
//    public DefaultWebSecurityManager mysecurityManager() {
//        DefaultWebSecurityManager mysecurityManager = new DefaultWebSecurityManager();
//        mysecurityManager.setRealm(customSecurityRealm());
//        return mysecurityManager;
//    }
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager mysecurityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(mysecurityManager);
//        //拦截器.
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
//        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("logout", "logout");
//        filterChainDefinitionMap.put("/user/login", "anon");
//        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
//        filterChainDefinitionMap.put("/user/**", "anon");
//        filterChainDefinitionMap.put("/test/**", "authc");
//        filterChainDefinitionMap.put("/templates/page/**", "authc");
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/login.html");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/templates/page/fail.html");//未授权跳转
//        //登录成功跳转的链接 (这个不知道怎么用，我都是自己实现跳转的)
//        shiroFilterFactoryBean.setSuccessUrl("/templates/page/main.html");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//
//        /**
//     * 凭证匹配器
//     * 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
//     * @return
//     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//        return hashedCredentialsMatcher;
//    }
//
//    @Bean
//    @DependsOn(value="lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
//        return new DefaultAdvisorAutoProxyCreator();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
//        return authorizationAttributeSourceAdvisor;
//    }
//
//}
