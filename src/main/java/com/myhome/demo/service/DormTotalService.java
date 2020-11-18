package com.myhome.demo.service;

import com.myhome.demo.domain.DormTotalInfo;

import java.util.List;

public interface DormTotalService {
    public DormTotalInfo searchJdbcDormTotal(int dormId);
    public void insertJdbcDormTotal(int dormId, int layers, int roomPerLayer, int bedPerRoom, int usedRoom);
    public void deleteJdbcDormTotal(int dormId);
    public void updateAddJdbcDormTotal(int dormId);
    public void updateDelJdbcDormTotal(int dormId);
    public List<DormTotalInfo> searchAllJdbcDormTotal();
}
