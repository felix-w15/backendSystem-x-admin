package com.myhome.demo.service;

import com.myhome.demo.domain.Roler;
import com.myhome.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void updateJdbcRoler(int role_id, int node_id) {
        String sql = "update role_access set node_id=? where role_id=?";
        jdbcTemplate.update(sql, new Object[]{node_id, role_id});
    }

    @Override
    public List<Roler> searchJdbcRoler(int role_id) {
        String sql = "SELECT role_id,node_id FROM role_access WHERE role_id=?";
        List<Roler> rolerList = jdbcTemplate.query(sql, new RowMapper<Roler>() {
            @Override
            public Roler mapRow(ResultSet rs, int rowNum) throws SQLException {
                Roler roler = new Roler();
                roler.setRole_id(rs.getInt("role_id"));
                roler.setNode_id(rs.getInt("node_id"));
                return roler;
            }
        }, role_id);
        return rolerList;
    }

    @Override
    public void insertJdbcRoler(int role_id, int node_id) {
        String sql = "insert into role_access (role_id, node_id) values (?,?)";
        jdbcTemplate.update(sql, new Object[]{role_id, node_id});
    }

    @Override
    public void deleteJdbcRoler(int role_id) {
        String sql = "delete from role_access where role_id=?";
        jdbcTemplate.update(sql, new Object[]{role_id});
    }


}
