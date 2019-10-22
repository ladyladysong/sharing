package com.sharing.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;

    private Long orderNo;

    private Integer status;

    private String description;

    private Integer tag;

    private Date createTime;

    private Date acceptTime;

    private Date returnTime;

    private BigDecimal locationLatitute;

    private BigDecimal locationLongitude;

    private Integer userProfileId;

    private String assets;

    private Integer acceptUserId;

    public Order(Integer id, Long orderNo, Integer status, String description, Integer tag, Date createTime, Date acceptTime, Date returnTime, BigDecimal locationLatitute, BigDecimal locationLongitude, Integer userProfileId, String assets, Integer acceptUserId) {
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
        this.assets = assets;
        this.acceptUserId = acceptUserId;
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

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets == null ? null : assets.trim();
    }

    public Integer getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(Integer acceptUserId) {
        this.acceptUserId = acceptUserId;
    }
}