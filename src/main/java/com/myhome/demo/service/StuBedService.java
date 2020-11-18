package com.myhome.demo.service;

import com.myhome.demo.domain.Student;
import com.myhome.demo.domain.StudentBed;

import java.util.List;

public interface StuBedService {
    public void updateJdbcStuBed(int stu_id, int bed_id);
    public StudentBed searchJdbcStuBed(int stu_id);
    public StudentBed searchJdbcStuBedByBedId(int bed_id);
    public void insertJdbcStuBed(int stu_id, int bed_id);
    public void deleteJdbcStuBed(int stu_id);
    public List<StudentBed> searchAllJdbcStuBed();
}
