package com.myhome.demo.domain;

public class User {
    private String user;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    private int role_id;
    public User(){

    }


    public User(String username, String pwd, int role_id){
        this.pwd = pwd;
        this.user = username;
        this.role_id = role_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    private String pwd;
}
