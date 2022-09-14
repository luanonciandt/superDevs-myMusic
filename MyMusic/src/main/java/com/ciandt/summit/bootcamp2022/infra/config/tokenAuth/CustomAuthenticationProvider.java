package com.ciandt.summit.bootcamp2022.infra.config.tokenAuth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final String TOKEN_URI = "http://localhost:8080/api/v1/token/authorizer";


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = (String) authentication.getCredentials();
        String token = (String) authentication.getPrincipal();
        return processToken(name, token);
    }

    public Authentication processToken(String name, String token) {
        String body = String.format("{\"data\":{\"name\":\"%s\",\"token\":\"%s\"}}", name, token);
        int status = 0;

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(TOKEN_URI))
                    .headers("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            status = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (status == 201) {
            return new PreAuthenticatedAuthenticationToken("AuthenticatedUser", name, Collections.singleton(new SimpleGrantedAuthority("USER")));
        } else {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.equals(authentication);
    }
}