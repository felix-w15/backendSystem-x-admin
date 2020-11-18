package com.myhome.demo.service;

import com.myhome.demo.domain.DormTotalInfo;
import com.myhome.demo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class DormTotalSeviceImpl implements DormTotalService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public DormTotalInfo searchJdbcDormTotal(int dormId){
        String sql = "SELECT * FROM dormTotalInfo WHERE dormId=?";
        List<DormTotalInfo> dormList = jdbcTemplate.query(sql, new RowMapper<DormTotalInfo>() {
            @Override
            public DormTotalInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                DormTotalInfo dorm = new DormTotalInfo(0,0,0,0, 0);
                dorm.setDormId(rs.getInt("dormId"));
                dorm.setLayers(rs.getInt("layers"));
                dorm.setRoomPerLayer(rs.getInt("roomPerLayer"));
                dorm.setBedPerRoom(rs.getInt("bedPerRoom"));
                dorm.setUsedRoom(rs.getInt("usedRoom"));
                return dorm;
            }
        }, dormId);
        if(dormList.size() == 1)
            return dormList.get(0);
        else
            return  new DormTotalInfo(-1,-1,-1,-1, -1);
    }
    @Override
    public void insertJdbcDormTotal(int dormId, int layers, int roomPerLayer, int bedPerRoom, int usedRoom) {
        String sql = "insert into dormTotalInfo (dormId, layers, roomPerLayer, bedPerRoom, usedRoom)" +
                " values (?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{dormId, layers, roomPerLayer, bedPerRoom, usedRoom});

    }
    @Override
    public void deleteJdbcDormTotal(int dormId) {
        String sql = "delete from dormTotalInfo where dormId=?";
        jdbcTemplate.update(sql, new Object[]{dormId});
    }

    @Override
    public void updateAddJdbcDormTotal(int dormId) {
        DormTotalInfo dormTotalInfo = searchJdbcDormTotal(dormId);

        String sql = "update dormTotalInfo set usedRoom=? where dormId=?";
        jdbcTemplate.update(sql, new Object[]{dormTotalInfo.getUsedRoom()+1, dormId});
    }
    @Override
    public void updateDelJdbcDormTotal(int dormId) {
        DormTotalInfo dormTotalInfo = searchJdbcDormTotal(dormId);
        System.out.println("???????"+dormTotalInfo.getUsedRoom());
        String sql = "update dormTotalInfo set usedRoom=? where dormId=?";
        jdbcTemplate.update(sql, new Object[]{dormTotalInfo.getUsedRoom()-1, dormId});
    }

    @Override
    public List<DormTotalInfo> searchAllJdbcDormTotal() {
        String sql = "SELECT * FROM dormTotalInfo ORDER BY dormId";
        List<DormTotalInfo> dormList = jdbcTemplate.query(sql, new RowMapper<DormTotalInfo>() {
            @Override
            public DormTotalInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                DormTotalInfo dorm = new DormTotalInfo(0,0,0,0, 0);
                dorm.setDormId(rs.getInt("dormId"));
                dorm.setLayers(rs.getInt("layers"));
                dorm.setRoomPerLayer(rs.getInt("roomPerLayer"));
                dorm.setBedPerRoom(rs.getInt("bedPerRoom"));
                dorm.setUsedRoom(rs.getInt("usedRoom"));
                return dorm;
            }
        });
        return  dormList;
    }
}
