package com.api.ninebox.security.jwt;

import com.api.ninebox.security.repository.AppUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Autowired
    private AppUserRepository userRepo;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration.in.minutes}")
    private long expiration;

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (expiration * 60000)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token){
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            Date nowDate = new Date(System.currentTimeMillis());

            return expirationDate.after(nowDate);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public String getUserToken(String token) throws ExpiredJwtException {
        return getClaims(token).getSubject();
    }
}