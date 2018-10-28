package com.petronas.fetchtechexam.model;

/**
 * Created by hungtv on 08/10/2018.
 */
public class AccountCredentials {
    private String username;
    private String password;
    private String companyId;
    private String userId;
    public AccountCredentials() {
    }

    public AccountCredentials(String username, String password, String companyId, String userId) {
        this.username = username;
        this.password = password;
        this.companyId = companyId;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
