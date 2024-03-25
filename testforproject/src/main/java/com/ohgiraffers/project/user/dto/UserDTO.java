package com.ohgiraffers.project.user.dto;


import java.sql.Date;

public class UserDTO {


    private int code;
    private String email;
    private String password;
    private String nickname;
    private String phone;
    private String sns;
    private String status;
    private String auth;
    private Date registDate;
    private Date updateDate;
    private int imageNo;


    public UserDTO() {
    }

    public UserDTO(int code, String email, String password, String nickname, String phone, String sns, String status, String auth, Date registDate, Date updateDate, int imageNo) {
        this.code = code;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.sns = sns;
        this.status = status;
        this.auth = auth;
        this.registDate = registDate;
        this.updateDate = updateDate;
        this.imageNo = imageNo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSns() {
        return sns;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "code=" + code +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", sns='" + sns + '\'' +
                ", status='" + status + '\'' +
                ", auth='" + auth + '\'' +
                ", registDate=" + registDate +
                ", updateDate=" + updateDate +
                ", imageNo=" + imageNo +
                '}';
    }
}
