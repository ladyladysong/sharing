package com.sharing.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;

    private String orderNo;

    private Byte status;

    private String description;

    private Integer tag;

    private Date createTime;

    private Date acceptTime;

    private Date returnTime;

    private BigDecimal locationLatitute;

    private BigDecimal locationLongitude;

    private Integer userProfileId;

    public Order(Integer id, String orderNo, Byte status, String description, Integer tag, Date createTime, Date acceptTime, Date returnTime, BigDecimal locationLatitute, BigDecimal locationLongitude, Integer userProfileId) {
        this.id = id;
        this.orderNo = orderNo;
        this.status = status;
        this.description = description;
        this.tag = tag;
        this.createTime = createTime;
        this.acceptTime = acceptTime;
        this.returnTime = returnTime;
        this.locationLatitute = locationLatitute;
        this.locationLongitude = locationLongitude;
        this.userProfileId = userProfileId;
    }

    public Order() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
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

    public Integer getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Integer userProfileId) {
        this.userProfileId = userProfileId;
    }
}