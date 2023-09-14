package com.api.ninebox.security.service;

import com.api.ninebox.dto.LoginDto;
import com.api.ninebox.security.entity.AppUser;
import com.api.ninebox.security.repository.AppUserRepository;
import com.api.ninebox.security.service.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private AppUserRepository repo;
    @Autowired
    private PasswordEncoder encoder;

    public UserDetails authenticate(LoginDto dto){
        UserDetails user = loadUserByUsername(dto.getUsername());

        if (encoder.matches(dto.getPassword(), user.getPassword())){
            return user;
        }
        throw new InvalidPasswordException();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser model = repo
                            .findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not exists."));

        return User.builder()
                .username(model.getUsername())
                .password(model.getPassword())
                .roles(model.getRoles())
                .build();
    }
}