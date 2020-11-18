package com.myhome.demo.service;

import com.myhome.demo.domain.DormBedInfo;
import com.myhome.demo.domain.DormTotalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DormBedInfoServiceImpl implements DormBedInfoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public DormBedInfo searchJdbcDormBed(int bed_id) {
        String sql = "SELECT * FROM bed_info WHERE bed_id=?";
        List<DormBedInfo> bedList = jdbcTemplate.query(sql, new RowMapper<DormBedInfo>() {
            @Override
            public DormBedInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                DormBedInfo bed = new DormBedInfo(0, 0, 0, 0, 0);
                bed.setBed_id(rs.getInt("bed_id"));
                bed.setDorm_id(rs.getInt("dorm_id"));
                bed.setLayer_id(rs.getInt("layer_id"));
                bed.setRoom_id(rs.getInt("room_id"));
                bed.setBed_num(rs.getInt("bed_num"));

                return bed;
            }
        }, bed_id);
        if (bedList.size() == 1)
            return bedList.get(0);
        else
            return new DormBedInfo(-1, -1, -1, -1, -1);
    }

    @Override
    public DormBedInfo searchJdbcDormBed(int dorm_id, int layer_id, int room_id, int bed_num) {
        String sql = "SELECT * FROM bed_info WHERE dorm_id=? AND layer_id=? AND room_id = ? AND bed_num=?";
        List<DormBedInfo> bedList = jdbcTemplate.query(sql, new RowMapper<DormBedInfo>() {
            @Override
            public DormBedInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                DormBedInfo bed = new DormBedInfo(0, 0, 0, 0, 0);
                bed.setBed_id(rs.getInt("bed_id"));
                bed.setDorm_id(rs.getInt("dorm_id"));
                bed.setLayer_id(rs.getInt("layer_id"));
                bed.setRoom_id(rs.getInt("room_id"));
                bed.setBed_num(rs.getInt("bed_num"));

                return bed;
            }
        }, new Object[]{dorm_id, layer_id, room_id, bed_num});
        if (bedList.size() == 1)
            return bedList.get(0);
        else
            return new DormBedInfo(-1, -1, -1, -1, -1);
    }

    @Override
    public void insertJdbcDormBed(int bed_id, int dorm_id, int layer_id, int room_id, int bed_num) {
        String sql = "insert into bed_info (bed_id, dorm_id, layer_id, room_id, bed_num)" +
                " values (?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{bed_id, dorm_id, layer_id, room_id, bed_num});
    }

    @Override
    public void deleteJdbcDormBed(int bed_id) {
        String sql = "delete from bed_info where bed_id=?";
        jdbcTemplate.update(sql, new Object[]{bed_id});
    }

    @Override
    public void deleteJdbcDormBedTotal(int dorm_id) {
        String sql = "delete from bed_info where dorm_id=?";
        jdbcTemplate.update(sql, new Object[]{dorm_id});
    }

    @Override
    public List<DormBedInfo> searchAllJdbcDormBed() {
        String sql = "SELECT * FROM bed_info ORDER BY bed_id";
        List<DormBedInfo> bedList = jdbcTemplate.query(sql, new RowMapper<DormBedInfo>() {
            @Override
            public DormBedInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                DormBedInfo bed = new DormBedInfo(0, 0, 0, 0, 0);
                bed.setBed_id(rs.getInt("bed_id"));
                bed.setDorm_id(rs.getInt("dorm_id"));
                bed.setLayer_id(rs.getInt("layer_id"));
                bed.setRoom_id(rs.getInt("room_id"));
                bed.setBed_num(rs.getInt("bed_num"));

                return bed;
            }
        });
        return bedList;
    }
}
