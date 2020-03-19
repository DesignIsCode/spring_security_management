package org.zzq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zzq.entities.SysRole;
import org.zzq.entities.SysUser;
import org.zzq.mapper.UserMapper;
import org.zzq.service.RoleService;
import org.zzq.service.UserService;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
                UserDetails userDetails = new User(sysUser.getUsername(), sysUser.getPassword(),
                        sysUser.getStatus() == 1,
                        true,
                        true,
                        true,
                        authorities);
                return userDetails;
            }
        }catch (Exception e){
            e.printStackTrace();
            //认证失败
            return null;
        }
    }

    public void save(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.save(user);
    }

    public List<SysUser> findAll() {
        return userMapper.findAll();
    }

    public Map<String, Object> toAddRolePage(Integer id) {
        List<SysRole> allRoles = roleService.findAll();
        List<Integer> myRoles = userMapper.findRolesByUid(id);
        Map<String, Object> map = new HashMap();
        map.put("allRoles", allRoles);
        map.put("myRoles", myRoles);
        return map;
    }

    public void addRoleToUser(Integer userId, Integer[] ids) {
        userMapper.removeRoles(userId);
        if(ids.length>0){
            for (Integer rid : ids) {
                System.out.println("uid + " + userId + " , rid + " + rid);
                userMapper.addRoles(userId, rid);
            }
        }
    }
}
