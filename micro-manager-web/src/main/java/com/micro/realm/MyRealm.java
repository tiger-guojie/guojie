package com.micro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.micro.entity.Tmenu;
import com.micro.entity.Trole;
import com.micro.entity.Tuser;
import com.micro.mapper.TmenuMapper;
import com.micro.mapper.TroleMapper;
import com.micro.mapper.TuserMapper;
import com.micro.mapper.TuserroleMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * 自定义Realm
 * @author GJ
 *
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private TuserMapper tuserMapper;
	
	@Resource
	private TroleMapper troleMapper;

	@Resource
	private TuserroleMapper tuserroleMapper;
	
	@Resource
	private TmenuMapper tmenuMapper;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Tuser userToken=(Tuser) SecurityUtils.getSubject().getPrincipal();

		//User user=userRepository.findByUserName(userName);
		//根据用户名查询出用户记录
		Example tuserExample=new Example(Tuser.class);
		tuserExample.or().andEqualTo("userName",userToken.getUserName());
		Tuser user=tuserMapper.selectByExample(tuserExample).get(0);


		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

		//List<Role> roleList=roleRepository.findByUserId(user.getId());
		List<Trole> roleList = troleMapper.selectRolesByUserId(user.getId());

		Set<String> roles=new HashSet<String>();
		if(roleList.size()>0){
			for(Trole role:roleList){
				roles.add(role.getName());
				//List<Tmenu> menuList=menuRepository.findByRoleId(role.getId());
				//根据角色id查询所有资源
				List<Tmenu> menuList=tmenuMapper.selectMenusByRoleId(role.getId());
				for(Tmenu menu:menuList){
					info.addStringPermission(menu.getName()); // 添加权限
				}
			}
		}
		info.setRoles(roles);
		return info;
	}

	/**
	 * 权限认证
				*/
		@Override
		protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
			String userName=(String)token.getPrincipal();
			//User user=userRepository.findByUserName(userName);
			Example tuserExample=new Example(Tuser.class);
			tuserExample.or().andEqualTo("userName",userName);
			Tuser user=tuserMapper.selectByExample(tuserExample).get(0);
			if(user!=null){
				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
		                user, //用户
		                user.getPassword(), //密码
		                ByteSource.Util.bytes(userName),
		                getName()  //realm name
		        );
		        // 当验证都通过后，把用户信息放在session里
		        Session session = SecurityUtils.getSubject().getSession();
		        session.setAttribute("userSession", user);
		        session.setAttribute("userSessionId", user.getId());
		        return authenticationInfo;
			}else{
				return null;
			}
	}

}
