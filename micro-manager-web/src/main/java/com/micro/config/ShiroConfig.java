package com.micro.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.util.StringUtil;
import com.micro.entity.Tmenu;
import com.micro.mapper.TmenuMapper;
import com.micro.realm.MyRealm;

/**
 * @Author: GJ
 * @Description: Shiro配置类
 * @Date: Created in 2018/2/8 13:29
 * @param
 */
@Configuration
public class ShiroConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;
	@Autowired(required = false)
	private TmenuMapper tmenuMapper;
	
	/**
	 * Shiro生命周期处理器
	 * 
	 * @return
	 */
	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		// shiroFilterFactoryBean.setLoginUrl("/login.ftl");

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		shiroFilterFactoryBean.setLoginUrl("/tologin");
		shiroFilterFactoryBean.setUnauthorizedUrl("/tologin");

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置记住我或认证通过可以访问的地址(配置不会被拦截的链接 顺序判断)
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/user/login", "anon");
		filterChainDefinitionMap.put("/drawImage", "anon");

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/admin/user/logout", "logout");

		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		// 自定义加载权限菜单关系
        List<Tmenu> menuList = tmenuMapper.queryAll();
		for (Tmenu menu : menuList) {
			if (StringUtil.isNotEmpty(menu.getName())) {
				String permission = "perms[" + menu.getName() + "]";
				filterChainDefinitionMap.put(menu.getName(), permission);
			}
		}
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myRealm());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		// 注入记住我管理器(本地cookie);
		// securityManager.setRememberMeManager(rememberMeManager());
		// 自定义session管理 使用redis
		// securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean
	public MyRealm myRealm() {
		MyRealm myRealm = new MyRealm();
		myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myRealm;
	}

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * 所以我们需要修改下doGetAuthenticationInfo中的代码; ）
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));

		return hashedCredentialsMatcher;
	}

	

	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * 不要使用 DefaultAdvisorAutoProxyCreator 会出现二次代理的问题，这里不详述
	 * 
	 * @return
	 */
	/*
	 * @Bean
	 * 
	 * @DependsOn({"lifecycleBeanPostProcessor"}) public
	 * DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
	 * DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new
	 * DefaultAdvisorAutoProxyCreator();
	 * advisorAutoProxyCreator.setProxyTargetClass(true); return
	 * advisorAutoProxyCreator; }
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * cookie对象; 记住密码实现起来也是比较简单的，主要看下是如何实现的。
	 * 
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		System.out.println("ShiroConfiguration.rememberMeCookie()");
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;
	 * 
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

	/**
	 * shiro session的管理
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * 配置shiro redisManager 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(timeout);
		// redisManager.setPassword(password);
		return redisManager;
	}

}
