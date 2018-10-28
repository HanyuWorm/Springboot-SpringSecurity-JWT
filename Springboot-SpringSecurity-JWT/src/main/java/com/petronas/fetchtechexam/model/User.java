package com.petronas.fetchtechexam.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@javax.persistence.Table(name = "user")
public class User {
    @Id
    @Column(name = "CompanyId")
    private int companyId;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "PassWord")
    private String passWord;

    @Column(name = "role")
    private String role;

    @Column(name = "username")
    private String username;
    public User() {
    }

    public User(int companyId, int userId, String passWord,String role,String username) {
        this.companyId = companyId;
        this.userId = userId;
        this.passWord = passWord;
        this.role = role;
        this.username=username;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
