package com.myhome.demo.service;

import com.myhome.demo.domain.Student;

import java.util.List;


public interface StudentService {
    public void updateJdbcStu(int stu_id, String pwd);
    public Student searchJdbcStu(int stu_id);
    public void insertJdbcStu(int stu_id, String pwd);
    public void deleteJdbcStu(int stu_id);
    public List<Student> searchAllJdbcStu();
}
