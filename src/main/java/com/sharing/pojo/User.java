package com.sharing.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private Integer id;

    private String email;

    private String username;

    private String password;

    private Integer gender;

    private String tel;

    private String image;

    private BigDecimal rate;

    private Date createTime;

    private BigDecimal locationLatitute;

    private BigDecimal locationLongitude;

    public User(Integer id, String email, String username, String password, Integer gender, String tel, String image, BigDecimal rate, Date createTime, BigDecimal locationLatitute, BigDecimal locationLongitude) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.tel = tel;
        this.image = image;
        this.rate = rate;
        this.createTime = createTime;
        this.locationLatitute = locationLatitute;
        this.locationLongitude = locationLongitude;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getLocationLatitute() {
        return locationLatitute;
    }

    public void setLocationLatitute(BigDecimal locationLatitute) {
        this.locationLatitute = locationLatitute;
    }

    public BigDecimal getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(BigDecimal locationLongitude) {
        this.locationLongitude = locationLongitude;
    }
}