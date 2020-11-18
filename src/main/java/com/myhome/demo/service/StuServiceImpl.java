package com.myhome.demo.service;

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
public class StuServiceImpl implements StudentService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void updateJdbcStu(int stu_id, String pwd){
        String sql = "update student set pwd=? where stu_id=?";
        jdbcTemplate.update(sql, new Object[]{pwd,stu_id});
    }
    @Override
    public Student searchJdbcStu(int stu_id) {
        String sql = "SELECT stu_id,pwd FROM student WHERE stu_id=?";
        List<Student> stuList = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student(0, "");
                student.setStu_id(rs.getInt("stu_id"));
                student.setPwd(rs.getString("pwd"));
                return student;
            }
        }, stu_id);
        if(stuList.size() == 1)
            return  stuList.get(0);
        else
            return  new Student(-1, "");
    }
    @Override
    public void insertJdbcStu(int stu_id, String pwd) {
        String sql = "insert into student (stu_id, pwd) values (?,?)";
        jdbcTemplate.update(sql, new Object[]{stu_id, pwd});

    }
    @Override
    public void deleteJdbcStu(int stu_id) {
            String sql = "delete from student where stu_id=?";
            jdbcTemplate.update(sql, new Object[]{stu_id});
    }

    @Override
    public List<Student> searchAllJdbcStu() {
        String sql = "SELECT stu_id,pwd FROM student ORDER BY stu_id";
        List<Student> stuList = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student(0,"");
                student.setStu_id(rs.getInt("stu_id"));
                student.setPwd(rs.getString("pwd"));
                return student;
            }
        });
        return  stuList;
    }
}
