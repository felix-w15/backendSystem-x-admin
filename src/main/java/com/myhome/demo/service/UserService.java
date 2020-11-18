package com.myhome.demo.service;

import com.myhome.demo.domain.User;

import java.sql.SQLException;

public interface UserService {
    public void updateJdbcUser(String user, String pwd, int role_id);
    public void updateJdbcUser(String user, String pwd);
    public User searchJdbcUser(String user);
    public void insertJdbcUser(String user, String pwd);
    public void deleteJdbcUser(String user);
}
