package com.myhome.demo.service;

import com.myhome.demo.domain.Student;
import com.myhome.demo.domain.StudentBed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StuBedServiceImpl implements StuBedService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void updateJdbcStuBed(int stu_id, int bed_id){
        String sql = "update stu_bed set bed_id=? where stu_id=?";
        jdbcTemplate.update(sql, new Object[]{bed_id,stu_id});
    }
    @Override
    public StudentBed searchJdbcStuBed(int stu_id) {
        String sql = "SELECT stu_id,bed_id FROM stu_bed WHERE stu_id=?";
        List<StudentBed> stuBedList = jdbcTemplate.query(sql, new RowMapper<StudentBed>() {
            @Override
            public StudentBed mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentBed studentBed = new StudentBed(0, 0);
                studentBed.setStu_id(rs.getInt("stu_id"));
                studentBed.setBed_id(rs.getInt("bed_id"));
                return studentBed;
            }
        }, stu_id);
        if(stuBedList.size() == 1)
            return  stuBedList.get(0);
        else
            return  new StudentBed(-1, -1);
    }
    @Override
    public void insertJdbcStuBed(int stu_id, int bed_id) {
        String sql = "insert into stu_bed (stu_id, bed_id) values (?,?)";
        jdbcTemplate.update(sql, new Object[]{stu_id, bed_id});

    }
    @Override
    public void deleteJdbcStuBed(int stu_id) {
        String sql = "delete from stu_bed where stu_id=?";
        jdbcTemplate.update(sql, new Object[]{stu_id});
    }

    @Override
    public List<StudentBed> searchAllJdbcStuBed() {
        String sql = "SELECT stu_id,bed_id FROM student ORDER BY stu_id";
        List<StudentBed> stuBedList = jdbcTemplate.query(sql, new RowMapper<StudentBed>() {
            @Override
            public StudentBed mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentBed studentBed = new StudentBed(0, 0);
                studentBed.setStu_id(rs.getInt("stu_id"));
                studentBed.setBed_id(rs.getInt("bed_id"));
                return studentBed;
            }
        });
        return  stuBedList;
    }

    @Override
    public StudentBed searchJdbcStuBedByBedId(int bed_id){
        String sql = "SELECT stu_id,bed_id FROM stu_bed WHERE bed_id=?";
        List<StudentBed> stuBedList = jdbcTemplate.query(sql, new RowMapper<StudentBed>() {
            @Override
            public StudentBed mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentBed studentBed = new StudentBed(0, 0);
                studentBed.setStu_id(rs.getInt("stu_id"));
                studentBed.setBed_id(rs.getInt("bed_id"));
                return studentBed;
            }
        }, bed_id);
        if(stuBedList.size() == 1)
            return  stuBedList.get(0);
        else
            return  new StudentBed(-1, -1);
    }
}
