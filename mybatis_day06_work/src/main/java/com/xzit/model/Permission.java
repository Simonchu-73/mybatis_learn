package com.xzit.model;

import lombok.Data;


public class Permission {
    private int pid;
    private String pName;
    private String url;

    public Permission() {
    }

    public Permission(int pid, String pName, String url) {
        this.pid = pid;
        this.pName = pName;
        this.url = url;
    }

    /**
     * 获取
     * @return pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * 设置
     * @param pid
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * 获取
     * @return pName
     */
    public String getPName() {
        return pName;
    }

    /**
     * 设置
     * @param pName
     */
    public void setPName(String pName) {
        this.pName = pName;
    }

    /**
     * 获取
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "Permission{pid = " + pid + ", pName = " + pName + ", url = " + url + "}";
    }
}
