package com.northernarc.jwtdemo.service;

import com.northernarc.jwtdemo.model.JpaUser;
import com.northernarc.jwtdemo.repo.JpaUserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private JpaUserRepo jpaUserRepo;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JpaUser user = jpaUserRepo.findByUsername(username);
        return User.builder().
                username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    @PostConstruct
    public void init() {
//        JpaUser user = new JpaUser();
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("password"));
//        user.setRole("USER");
//        jpaUserRepo.save(user);
//
//        JpaUser admin = new JpaUser();
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("password"));
//        admin.setRole("ADMIN");
//        jpaUserRepo.save(admin);
//
//        JpaUser underwriter = new JpaUser();
//        underwriter.setUsername("underwriter");
//        underwriter.setPassword(passwordEncoder.encode("password"));
//        underwriter.setRole("UNDERWRITER");
//        jpaUserRepo.save(underwriter);

    }
}
