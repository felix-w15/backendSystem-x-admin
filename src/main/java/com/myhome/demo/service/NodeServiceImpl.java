package com.myhome.demo.service;

import com.myhome.demo.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class NodeServiceImpl implements NodeService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void updateJdbcNode(int node_id, String url, String description) {
        String sql = "update node set url=?,description=? where role_id=?";
        jdbcTemplate.update(sql, new Object[]{url, description, node_id});
    }

    @Override
    public List<Node> searchJdbcNode(int node_id) {
        String sql = "SELECT node_id,url,description FROM node WHERE node_id=?";
        List<Node> nodeList = jdbcTemplate.query(sql, new RowMapper<Node>() {
            @Override
            public Node mapRow(ResultSet rs, int rowNum) throws SQLException {
                Node Node = new Node();
                Node.setNode_id(rs.getInt("node_id"));
                Node.setUrl(rs.getString("url"));
                Node.setDescription(rs.getString("description"));
                return Node;
            }
        }, node_id);
        return nodeList;
    }

    @Override
    public void insertJdbcNode(int node_id, String url, String description) {
        String sql = "insert into node (node_id, url, description) values (?,?,?)";
        jdbcTemplate.update(sql, new Object[]{node_id, url, description});
    }

    @Override
    public void deleteJdbcNode(int node_id) {
        String sql = "delete from node where node_id=?";
        jdbcTemplate.update(sql, new Object[]{node_id});
    }
}
