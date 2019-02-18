package com.auth.demo.service.impl;

import com.auth.demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * Applied userRepository to convert {@code User} from db to {@code User} in Spring Security.
 *
 * @author Alexander Mok
 * @since 2018/12/26
 */
public class UserDetailsServiceImpl
        implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.auth.demo.entity.User> userOptional =
                userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            com.auth.demo.entity.User entity = userOptional.get();
            UserDetails user =
                    User.builder()
                            .username(entity.getUsername())
                            .password(entity.getPassword())
                            .authorities(entity.getGrantedAuthoritiesList())
                            .build();
            return user;
        }
        return null;
    }
}
