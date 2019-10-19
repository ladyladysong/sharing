package com.sharing.service;

import com.sharing.common.ServerResponse;
import com.sharing.pojo.Order;

import java.math.BigDecimal;
import java.util.List;

public interface iOrderService {
    ServerResponse createOrder(Order order);
    ServerResponse list(BigDecimal latitute, BigDecimal longitude, Integer uid);
    ServerResponse getOrderDetail(Long orderNo, Integer uid);
    ServerResponse changeOrderStatus(Long orderNo, Integer status, Integer uid);
    ServerResponse getCandidates(Long orderNo,Integer uid);
}
