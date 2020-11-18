package com.myhome.demo.domain;

public class StuWithBedIno {
    public StuWithBedIno(int stu_id, int bed_num, int dorm_id, int layer_id, int room_id) {
        this.stu_id = stu_id;
        this.bed_num = bed_num;
        this.dorm_id = dorm_id;
        this.layer_id = layer_id;
        this.room_id = room_id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getBed_num() {
        return bed_num;
    }

    public void setBed_num(int bed_num) {
        this.bed_num = bed_num;
    }

    public int getDorm_id() {
        return dorm_id;
    }

    public void setDorm_id(int dorm_id) {
        this.dorm_id = dorm_id;
    }

    public int getLayer_id() {
        return layer_id;
    }

    public void setLayer_id(int layer_id) {
        this.layer_id = layer_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    private int stu_id;
    private int bed_num;
    private int dorm_id;
    private int layer_id;
    private int room_id;
}
