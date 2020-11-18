package com.myhome.demo.domain;

public class Node {
    private int node_id;
    private String url;
    private String description;

    public Node(int node_id, String url, String description) {
        this.node_id = node_id;
        this.url = url;
        this.description = description;
    }

    public Node() {
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
