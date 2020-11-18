package com.myhome.demo.domain;



public class Roler {


    public Roler(int role_id, int node_id) {
        this.role_id = role_id;
        this.node_id = node_id;
    }

    public Roler() {
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    private int role_id;
    private int node_id;
}
