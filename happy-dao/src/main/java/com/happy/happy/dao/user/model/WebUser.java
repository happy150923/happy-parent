package com.happy.happy.dao.user.model;

import java.util.Date;

/**
 * Corresponds to the database table { web_user }
 * @mbg.generated 2016-26-08 14:59:54
 */
public class WebUser {
    /**
     * web_user.id
     * primary key
     * @mbg.generated 2016-26-08 14:59:54
     */
    private Integer id;

    /**
     * web_user.user_name
     * the name of user
     * @mbg.generated 2016-26-08 14:59:54
     */
    private String userName;

    /**
     * web_user.user_pwd
     * the password of user
     * @mbg.generated 2016-26-08 14:59:54
     */
    private String userPwd;

    /**
     * web_user.user_email
     * the email of user
     * @mbg.generated 2016-26-08 14:59:54
     */
    private String userEmail;

    /**
     * web_user.last_login_time
     * the last login time of user
     * @mbg.generated 2016-26-08 14:59:54
     */
    private Date lastLoginTime;

    /**
     * web_user.create_time
     * time of creating data
     * @mbg.generated 2016-26-08 14:59:54
     */
    private Date createTime;

    /**
     * web_user.is_deleted
     * is deleted or not. 0:no,1:yes
     * @mbg.generated 2016-26-08 14:59:54
     */
    private Short isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }
}