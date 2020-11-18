package com.myhome.demo.service;

import com.myhome.demo.domain.Roler;


import java.util.List;

public interface RoleService {
    public void updateJdbcRoler(int role_id, int node_id);
    public List<Roler> searchJdbcRoler(int role_id);
    public void insertJdbcRoler(int role_id, int node_id);
    public void deleteJdbcRoler(int role_id);
}
