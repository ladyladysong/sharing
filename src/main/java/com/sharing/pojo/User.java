package com.sharing.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String email;

    private String username;

    private String password;

    private Byte gender;

    private String tel;

    private String image;

    private Date createTime;

    public User(Integer id, String email, String username, String password, Byte gender, String tel, String image, Date createTime) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.tel = tel;
        this.image = image;
        this.createTime = createTime;
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

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}