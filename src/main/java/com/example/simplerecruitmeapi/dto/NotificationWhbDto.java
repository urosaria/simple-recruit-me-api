package com.example.simplerecruitmeapi.dto;

import com.example.simplerecruitmeapi.enums.AuthenticationType;
import com.example.simplerecruitmeapi.enums.HttpMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificationWhbDto {

    @NotBlank(message = "Notification Whb - Endpoint is required.")
    private String endpoint;

    @NotNull(message = "Notification Whb - Method is required.")
    private HttpMethod method;

    @NotNull(message = "Notification Whb - Authentication is required.")
    private AuthenticationType authentication;

    private String uid;
    private String pwd;
    private String token;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public AuthenticationType getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationType authentication) {
        this.authentication = authentication;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
