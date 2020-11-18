package com.myhome.demo.service;

import com.myhome.demo.domain.Student;
import com.myhome.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void updateJdbcUser(String user, String pwd){
        String sql = "update users set pwd=? where user=?";
        jdbcTemplate.update(sql, new Object[]{pwd, user});
    }

    @Override
    public void updateJdbcUser(String user, String pwd, int role_id){
        String sql = "update users set pwd=?,role_id=? where user=?";
        jdbcTemplate.update(sql, new Object[]{pwd, role_id, user});
    }

    @Override
    public User searchJdbcUser(String username){
        String sql = "SELECT user,pwd,role_id FROM users WHERE user=?";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUser(rs.getString("user"));
                user.setPwd(rs.getString("pwd"));
                user.setRole_id(rs.getInt("role_id"));
                return user;
            }
        }, username);
        if(userList.size() == 1)
            return  userList.get(0);
        else
            return  new User("", "", 0);
    }

    @Override
    public void insertJdbcUser(String username, String pwd){
        String sql = "insert into users (user, pwd) values (?,?)";
        jdbcTemplate.update(sql, new Object[]{username, pwd});
    }

    @Override
    public void deleteJdbcUser(String username){
        String sql = "delete from users where user=?";
        jdbcTemplate.update(sql, new Object[]{username});
    }
}
