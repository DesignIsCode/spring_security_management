package org.zzq.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zzq.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
