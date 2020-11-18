package com.myhome.demo.service;

import com.myhome.demo.domain.DormBedInfo;

import java.util.List;

public interface DormBedInfoService {
    public DormBedInfo searchJdbcDormBed(int bed_id);
    public DormBedInfo searchJdbcDormBed(int dorm_id, int layer_id, int room_id, int bed_num);
    public void insertJdbcDormBed(int bed_id, int dorm_id, int layer_id, int room_id, int bed_num);
    public void deleteJdbcDormBed(int bed_id);
    public void deleteJdbcDormBedTotal(int bed_id);
    public List<DormBedInfo> searchAllJdbcDormBed();
}
