package com.base.wang.web;

import com.base.wang.entity.BasAccount;
import com.base.wang.service.BasAccountService;
import com.base.wang.util.StringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    BasAccountService accountService;
    /**
     * 用户和密码记录
     */
    static public Map<String, String> userList = new HashMap<String, String>();

    static {
        userList.put("admin", "123456");
        userList.put("test", "123456");
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        List<String> permissionList = new ArrayList<String>();
        permissionList.add("user:add");
        permissionList.add("user:delete");
        if (userName.equals("admin")) {
            permissionList.add("user:query");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        if (userName.equals("admin")) {
            info.addRole("admin");
        }
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        if(StringUtil.isEmpty(userName)){
            return null;
        }
        //查找用户账号
        BasAccount account=accountService.findByUserName(userName);
        if(account==null){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, account.getPassword(), this.getName());
        return info;
    }
}