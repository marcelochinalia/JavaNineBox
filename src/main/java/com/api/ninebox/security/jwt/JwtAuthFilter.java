package com.api.ninebox.security.jwt;

import com.api.ninebox.security.service.AppUserDetailsService;
import com.api.ninebox.services.exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private AppUserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, AppUserDetailsService userDetailsService){
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filter) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        System.out.println("Peguei o token:" + authorization);

        if (authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];

            if (jwtService.isTokenValid(token)){
                UserDetails user = userDetailsService.loadUserByUsername(
                        jwtService.getUserToken(token)
                );

                UsernamePasswordAuthenticationToken userAuth =
                        new UsernamePasswordAuthenticationToken(user,
                                                                null,
                                                                user.getAuthorities());

                userAuth.setDetails(new WebAuthenticationDetailsSource()
                                            .buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(userAuth);
            }
        }
        filter.doFilter(request, response);
    }
}
