package com.tangsi.auth;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		String username = (String) principals.getPrimaryPrincipal();
		System.out.println("验证权限");
		return null;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		User user = this.userService.getUserByUsername(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getName(),
					user.getPassword(), user.getNickName());
		}
		return null;

	}
}
