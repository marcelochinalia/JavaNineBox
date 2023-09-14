package com.api.ninebox.services.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class NineboxUtils {

    public boolean validateEmailFormat(String email){
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                        .matcher(email)
                        .matches();
    }
}
