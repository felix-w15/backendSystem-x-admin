package com.myhome.demo.domain;

public class Student {
    private int stu_id;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    private String pwd;
    public  Student(int stu_id, String pwd){
        this.stu_id = stu_id;
        this.pwd = pwd;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }



}
