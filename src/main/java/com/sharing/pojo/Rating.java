package com.sharing.pojo;

import java.math.BigDecimal;

public class Rating {
    private Integer id;

    private BigDecimal rate;

    private Integer orderId;

    private Integer userProfileId;

    public Rating(Integer id, BigDecimal rate, Integer orderId, Integer userProfileId) {
        this.id = id;
        this.rate = rate;
        this.orderId = orderId;
        this.userProfileId = userProfileId;
    }

    public Rating() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Integer userProfileId) {
        this.userProfileId = userProfileId;
    }
}