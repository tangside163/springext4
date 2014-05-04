package com.tangsi.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tangsi.pojo.Role;
import com.tangsi.pojo.User;
import com.tangsi.service.UserService;

/**
 * 权限验证
 * 
 * @author Administrator
 * 
 */
@Component("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 验证当前Subject（可理解为当前用户）所拥有的权限，且给其授权。
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		if (principals == null) {
			throw new AuthorizationException("Principal对象不能为空");
		}

		String name = (String) getAvailablePrincipal(principals);
		List<String> roles = new ArrayList<String>();  //角色信息
		// 简单默认一个用户与角色，实际项目应User user = userService.getByAccount(name);
		User user = this.userService.getUserByUsername(name);
		List<Role> userRoles = user.getRoles();
		if(userRoles != null && !userRoles.isEmpty()) {
			for(Role role : userRoles) {
				roles.add(role.getName());
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 增加角色
		info.addRoles(roles);
		return info;
		
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		if (token.getUsername() == null) {
			throw new AccountException("用户名不能为空");
		}
		User user = this.userService.getUserByUsername(token.getUsername());
		if (user == null) {
			throw new UnknownAccountException("该用户不存在");
		}
		if (user != null) {
			if (!user.getPassword().equals(new String(token.getPassword()))) {
				throw new AccountException("密码不正确");
			}
			return new SimpleAuthenticationInfo(user.getName(),
					user.getPassword(), user.getNickName());
		}
		return null;

	}
}
