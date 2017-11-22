package com.arawn.cms.realm;

import com.arawn.cms.entity.Manager;
import com.arawn.cms.service.ManagerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 自定义Realm
 * Created by ArawN on 2017/11/18.
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private ManagerService managerService;

    /**
     * 为当前登录用户授予角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 验证当前登录的用户
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Manager manager = managerService.queryByUsername(username);
        if (manager == null) {
            return null;
        }

        SecurityUtils.getSubject().getSession().setAttribute("currentUser", manager);
        AuthenticationInfo authCInfo = new SimpleAuthenticationInfo(manager.getUsername(), manager.getPassword(), "xxx");
        return authCInfo;
    }

}
