package com.api.ninebox.controllers.advice;

import com.api.ninebox.dto.ValidationsDto;
import com.api.ninebox.security.service.exception.InvalidPasswordException;
import com.api.ninebox.services.exceptions.InvalidTokenException;
import com.api.ninebox.services.exceptions.NineBoxNotFoundException;
import com.api.ninebox.services.exceptions.NineBoxValidatedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NineBoxApplicationAdvice {
    @ExceptionHandler(NineBoxValidatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationsDto handleNineBoxException(NineBoxValidatedException ex){ return ex.getValidations(); }

    @ExceptionHandler(NineBoxNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String handleNineBoxException(NineBoxNotFoundException ex){ return ""; }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUserNameNotFoundException(UsernameNotFoundException ex){ return ex.getMessage(); }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUserNameNotFoundException(InvalidPasswordException ex){ return ex.getMessage(); }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleInvalidTokenException(InvalidTokenException ex){ return ex.getMessage(); }
}
