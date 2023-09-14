package com.api.ninebox.controllers;

import com.api.ninebox.dto.LoginDto;
import com.api.ninebox.security.jwt.JwtService;
import com.api.ninebox.security.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("ninebox/api/auth")
public class AppAuthController {

    @Autowired
    private AppUserDetailsService userDetailsService;
    @Autowired
    private JwtService jwtService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public String authentication(@RequestBody @Valid LoginDto login){
        UserDetails user = userDetailsService.authenticate(login);
        return jwtService.generateToken(user);
    }
}
