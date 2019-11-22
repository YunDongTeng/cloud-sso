package com.fosung.demo.service;

import com.fosung.demo.entity.SysUser;
import com.fosung.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public SysUser getUseryByUsername(String username) {
        return userRepository.queryByUsername(username);
    }


}
