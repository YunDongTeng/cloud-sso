package com.fosung.demo.service;

import com.fosung.demo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = userService.getUseryByUsername(username);

        if (null == sysUser) {
            throw new UsernameNotFoundException(username);
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        return new User(sysUser.getUsername(), sysUser.getPassword(), authorityList);
    }
}
