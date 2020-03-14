package org.zzq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zzq.entities.SysRole;
import org.zzq.entities.SysUser;
import org.zzq.mapper.UserMapper;
import org.zzq.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 认证业务
     * @param username 用户在浏览器输入的用户名
     * @return UserDetails 是springsecurity自己的用户对象
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            //根据用户名做查询
            SysUser sysUser = userMapper.findByName(username);
            if (null == sysUser) {
                return null;
            }else {
                List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
                List<SysRole> roles = sysUser.getRoles();
                for (SysRole role:
                     roles) {
                    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                }
                UserDetails userDetails = new User(sysUser.getUsername(),sysUser.getPassword(), authorities);
                return userDetails;
            }
        }catch (Exception e){
            e.printStackTrace();
            //认证失败
            return null;
        }

    }
}
