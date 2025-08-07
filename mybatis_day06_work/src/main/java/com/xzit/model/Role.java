package com.xzit.model;

import lombok.Data;


public class Role {
    private int rid;
    private String rName;

    public Role() {
    }

    public Role(int rid, String rName) {
        this.rid = rid;
        this.rName = rName;
    }

    /**
     * 获取
     * @return rid
     */
    public int getRid() {
        return rid;
    }

    /**
     * 设置
     * @param rid
     */
    public void setRid(int rid) {
        this.rid = rid;
    }

    /**
     * 获取
     * @return rName
     */
    public String getRName() {
        return rName;
    }

    /**
     * 设置
     * @param rName
     */
    public void setRName(String rName) {
        this.rName = rName;
    }

    public String toString() {
        return "Role{rid = " + rid + ", rName = " + rName + "}";
    }
}
