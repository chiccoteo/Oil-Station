package com.sigma.oilstation.secret;

import com.sigma.oilstation.entity.User;
import com.sigma.oilstation.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value(value = "${jwt.secretKey}")
    private String secretKey;

    @Value(value = "${jwt.expireDateInMilliSecond}")
    private Long expirationDateInMilliSecond;

    private final UserRepository userRepo;

    public String generateToken(User user) {
        Date issueDate = new Date();
        Date expireDate = new Date(issueDate.getTime() + expirationDateInMilliSecond);
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(issueDate)
                .claim("role", user.getRole().getName())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (
                MalformedJwtException malformedJwtException) {
            System.err.println("Broken token");
        } catch (
                SignatureException s) {
            System.err.println("The key word is error");
        } catch (
                UnsupportedJwtException unsupportedJwtException) {
            System.err.println("Unused token");
        } catch (IllegalArgumentException ex) {
            System.err.println("An empty token");
        }
        return false;
    }

    public Optional<User> getUserFromToken(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userRepo.findByUsername(username);
    }

}
