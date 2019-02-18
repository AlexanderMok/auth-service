package com.auth.demo.service.impl;

import com.auth.demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Mok
 * @since 2019/01/29
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;
}
