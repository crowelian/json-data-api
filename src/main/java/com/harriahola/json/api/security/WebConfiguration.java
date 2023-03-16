package com.harriahola.json.api.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class WebConfiguration {
    private boolean enableJwtAuthentication;

    public boolean isEnabledJwtAuthentication() {
        return enableJwtAuthentication;
    }

    public void setEnableJwtAuthentication(boolean setValue) {
        enableJwtAuthentication = setValue;
    }
}
