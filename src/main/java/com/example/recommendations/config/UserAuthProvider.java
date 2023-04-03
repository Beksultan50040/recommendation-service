package com.example.recommendations.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.recommendations.dto.UserDto;
import com.example.recommendations.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {


    @Value("${jwt.public.key}")
    private String jwtSecret;

    private final UserService userService;

    @PostConstruct
    protected void init(){

        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    public String createToken (String login) {

        Date now = new Date();
        Date expire = new Date(now.getTime() + 3_600_000);

        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(expire)
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public Authentication validateToken(String token){

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto userDto = userService.findByLogin(decoded.getIssuer());

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDto.getRoles()));

        return new UsernamePasswordAuthenticationToken(userDto, null, authorities);


    }


}
