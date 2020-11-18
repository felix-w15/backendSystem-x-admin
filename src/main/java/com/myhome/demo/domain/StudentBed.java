package com.myhome.demo.domain;

public class StudentBed {
    public StudentBed(int stu_id, int bed_id) {
        this.stu_id = stu_id;
        this.bed_id = bed_id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getBed_id() {
        return bed_id;
    }

    public void setBed_id(int bed_id) {
        this.bed_id = bed_id;
    }

    private int stu_id;
    private int bed_id;
}
