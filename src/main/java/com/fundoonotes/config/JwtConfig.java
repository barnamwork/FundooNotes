package com.fundoonotes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private Secret secret = new Secret();
    private Expiration expiration = new Expiration();

    public static class Secret {
        private String key;
        public String getKey() { return key; }
        public void setKey(String key) { this.key = key; }
    }

    public static class Expiration {
        private long ms;
        public long getMs() { return ms; }
        public void setMs(long ms) { this.ms = ms; }
    }

    public Secret getSecret() { return secret; }
    public void setSecret(Secret secret) { this.secret = secret; }
    public Expiration getExpiration() { return expiration; }
    public void setExpiration(Expiration expiration) { this.expiration = expiration; }
}