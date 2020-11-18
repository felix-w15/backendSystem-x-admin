package com.myhome.demo.domain;

public class DormTotalInfo {
    private int dormId;

    public DormTotalInfo(int dormId, int layers, int roomPerLayer, int bedPerRoom, int usedRoom) {
        this.dormId = dormId;
        this.layers = layers;
        this.roomPerLayer = roomPerLayer;
        this.bedPerRoom = bedPerRoom;
        this.usedRoom = usedRoom;
    }

    public int getDormId() {
        return dormId;
    }

    public void setDormId(int dormId) {
        this.dormId = dormId;
    }

    public int getLayers() {
        return layers;
    }

    public void setLayers(int layers) {
        this.layers = layers;
    }

    public int getRoomPerLayer() {
        return roomPerLayer;
    }

    public void setRoomPerLayer(int roomPerLayer) {
        this.roomPerLayer = roomPerLayer;
    }


    public int getBedPerRoom() {
        return bedPerRoom;
    }

    public void setBedPerRoom(int bedPerRoom) {
        this.bedPerRoom = bedPerRoom;
    }

    private int layers;

    private int roomPerLayer;

    private int bedPerRoom;

    public int getUsedRoom() {
        return usedRoom;
    }

    public void setUsedRoom(int usedRoom) {
        this.usedRoom = usedRoom;
    }

    private int usedRoom;
}
