package com.fundoonotes.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtil {

    public String generateToken(Long userId) {
        return UUID.randomUUID() + ":" + userId;
    }
    public Long getUserIdFromToken(String token) {
        return Long.parseLong(token.split(":")[1]);
    }
}