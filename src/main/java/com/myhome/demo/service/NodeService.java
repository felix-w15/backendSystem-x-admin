package com.myhome.demo.service;

import com.myhome.demo.domain.Node;

import java.util.List;

public interface NodeService {
    public void updateJdbcNode(int node_id, String url, String description);
    public List<Node> searchJdbcNode(int node_id);
    public void insertJdbcNode(int node_id, String url, String description);
    public void deleteJdbcNode(int node_id);
}
