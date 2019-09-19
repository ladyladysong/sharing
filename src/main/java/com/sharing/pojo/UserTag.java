package com.sharing.pojo;

public class UserTag {
    private Integer id;

    private Integer tag;

    private Integer userProfileId;

    public UserTag(Integer id, Integer tag, Integer userProfileId) {
        this.id = id;
        this.tag = tag;
        this.userProfileId = userProfileId;
    }

    public UserTag() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Integer userProfileId) {
        this.userProfileId = userProfileId;
    }
}