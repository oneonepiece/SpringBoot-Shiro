package com.lin.shiro.core.config;

import com.lin.shiro.core.realm.CustomSecurityRealm;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Configuration
public class ShiroConfig {

//      <!--
//    关于这里,
//            1. 如果你定义是多个同名的key, 那么Shiro会将其合并.
//					2. 如果当前请求匹配多个key, Shiro只会取第一个匹配的.
//					3. 以上是默认行为, 如果有特殊需求可以覆写。
//            -->
//            /login.jsp = authc
//            /logout = logout
//            /unauthorized.jsp = anon
//			/main**           = authc
//            /admin/list**     = authc,perms[admin:manage]
//            /user/info-anon** = anon
//            /user/info**      = authc
//    /** = anon
//

        @Bean(name = "shiroFilter")
        public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
            ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
            shiroFilterFactoryBean.setSecurityManager(securityManager);
            shiroFilterFactoryBean.setLoginUrl("/login");
            shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
            Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
            // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
            filterChainDefinitionMap.put("/webjars/**", "anon");
            filterChainDefinitionMap.put("/login", "anon");
            filterChainDefinitionMap.put("/", "anon");
            filterChainDefinitionMap.put("/front/**", "anon");
            filterChainDefinitionMap.put("/api/**", "anon");

            filterChainDefinitionMap.put("/admin/**", "authc");
            filterChainDefinitionMap.put("/user/**", "authc");
            //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
            filterChainDefinitionMap.put("/**", "authc");
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
            return shiroFilterFactoryBean;

        }

        @Bean
        public SecurityManager securityManager() {
            DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
            defaultSecurityManager.setRealm(customSecurityRealm());
            return defaultSecurityManager;
        }

        @Bean
        public CustomSecurityRealm customSecurityRealm() {
            CustomSecurityRealm customSecurityRealm = new CustomSecurityRealm();
            return customSecurityRealm;
        }

}

//
//
//    /**
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
//        @Bean
//    public UserRealm myShiroRealm() {
//        UserRealm myShiroRealm = new UserRealm();
//        //使用加密
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return myShiroRealm;
//    }
//
//
//    @Bean
//    public CacheManager cacheManager() {
//        // Caching isn't needed in this example, but we will use the MemoryConstrainedCacheManager for this example.
//        return new MemoryConstrainedCacheManager();
//    }
//
//
//    //public SecurityManager securityManager() {
//    @Bean
//    public SecurityManager mysecurityManager() {
//        DefaultWebSecurityManager mysecurityManager = new DefaultWebSecurityManager();
//        mysecurityManager.setRealm(myShiroRealm());
//        return mysecurityManager;
//    }
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager mysecurityManager){
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
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
//        return new LifecycleBeanPostProcessor();
//    }

//    /**
//     * 注册全局异常处理
//     * @return
//     */
//    @Bean(name = "exceptionHandler")
//    public HandlerExceptionResolver handlerExceptionResolver() {
//        return new ExceptionHandler();
//    }


