package com.lv.dt_server.commons;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

import static com.auth0.jwt.RegisteredClaims.ISSUER;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.data.expire-time}")
    private int expireTime;

    @Value("${jwt.data.token-secret}")
    private String tokenSecret;

    public String generateToken(String userName) {

        long currentTime = System.currentTimeMillis();
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);

        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date(currentTime))// 签发时间
                .withExpiresAt(new Date(currentTime + expireTime * 1000L))// 过期时间戳
                .withClaim("userName", userName)
                .sign(algorithm);
    }

    public boolean verifyToken(String token) {
        try {
            if (!StringUtils.hasLength(token)) {
                return false;
            }
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            String username = decodedJWT.getClaim("userName").asString();
            if (!StringUtils.hasLength(username)) {
                return false;
            }
        } catch (TokenExpiredException e) {
            return false;
        }
        return true;
    }
}
