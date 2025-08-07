package com.xzit.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


public class Users {
    private int uid;
    private String uname;
    private String pwd;
    private LocalDateTime lastTime;
    private List<Role> roles;
    private List<Permission> permissions;

    public Users() {
    }

    public Users(int uid, String uname, String pwd, LocalDateTime lastTime, List<Role> roles, List<Permission> permissions) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;
        this.lastTime = lastTime;
        this.roles = roles;
        this.permissions = permissions;
    }

    /**
     * 获取
     * @return uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * 设置
     * @param uid
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * 获取
     * @return uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * 设置
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * 获取
     * @return pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置
     * @param pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取
     * @return lastTime
     */
    public LocalDateTime getLastTime() {
        return lastTime;
    }

    /**
     * 设置
     * @param lastTime
     */
    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取
     * @return roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * 设置
     * @param roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * 获取
     * @return permissions
     */
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * 设置
     * @param permissions
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String toString() {
        return "Users{uid = " + uid + ", uname = " + uname + ", pwd = " + pwd + ", lastTime = " + lastTime + ", roles = " + roles + ", permissions = " + permissions + "}";
    }
}
