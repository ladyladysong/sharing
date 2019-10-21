package com.sharing.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sharing.common.Const;
import com.sharing.common.ServerResponse;
import com.sharing.dao.OrderMapper;
import com.sharing.dao.UserMapper;
import com.sharing.dao.UserTagMapper;
import com.sharing.pojo.Order;
import com.sharing.pojo.User;
import com.sharing.service.iOrderService;
import com.sharing.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service("iOrderService")
public class iOrderImpl implements iOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse createOrder(Order order) {
        long orderNo=generateOrderNo();
        order.setOrderNo(orderNo);
        order.setStatus(Const.OrderStatusEnum.PUBLISHED.getCode());
        int result=orderMapper.insert(order);
        if(result==0){
            return ServerResponse.createByErrorMessage("create order failed");
        }
        Map map= Maps.newHashMap();
        map.put("oid",orderNo);
        return ServerResponse.createBySuccess(map);
    }

    private long generateOrderNo(){
        long currentTime=System.currentTimeMillis();
        return currentTime+currentTime%(new Random().nextInt(10)+1);
    }

    public ServerResponse list(BigDecimal latitute, BigDecimal longitude,Integer uid){
        BigDecimal latituteUp=latitute.add(Const.PositionRange.RANGE);
        BigDecimal latituteBot=latitute.subtract(Const.PositionRange.RANGE);
        BigDecimal longitudeUp=longitude.add(Const.PositionRange.RANGE);
        BigDecimal longitudeBot=longitude.subtract(Const.PositionRange.RANGE);
        PageHelper.startPage(Const.PageSettings.PAGE_NUMBER,Const.PageSettings.PAGE_SIZE);
        List<Integer> tags=userTagMapper.selectByUserId(uid);
        if(tags.isEmpty()){
            for(Const.tag t:Const.tag.values()){
                tags.add(t.getCode());
            }
        }
        List<Order> orders=orderMapper.selectList(latituteUp,latituteBot,longitudeUp,longitudeBot,tags);
        if(orders.isEmpty()){
            return ServerResponse.createByErrorMessage("no available orders");
        }
        List<OrderVo> orderVoList= Lists.newArrayList();
        for(Order o:orders){
            if(o.getStatus().equals(Const.OrderStatusEnum.PUBLISHED.getCode())){
                OrderVo orderVo=assembleOrderVo(o);
                orderVoList.add(orderVo);
            }
        }
        PageInfo pageInfo=new PageInfo(orders);
        pageInfo.setList(orderVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    private OrderVo assembleOrderVo(Order order){
        OrderVo orderVo=new OrderVo();
        orderVo.setOrderNo(order.getOrderNo());
        orderVo.setDescription(order.getDescription());
        orderVo.setStatus(order.getStatus());
        orderVo.setTag(order.getTag());
        orderVo.setUserProfileId(order.getUserProfileId());
        orderVo.setLocationLatitute(order.getLocationLatitute());
        orderVo.setLocationLongitude(order.getLocationLongitude());
        orderVo.setAcceptTime(order.getAcceptTime());
        orderVo.setCreateTime(order.getCreateTime());
        orderVo.setReturnTime(order.getReturnTime());
        orderVo.setAcceptUserId(order.getAcceptUserId());
        String asts=order.getAssets();
        List<String> assets=Lists.newArrayList();
        if(!StringUtils.isBlank(asts)){
            String[] strings=order.getAssets().split(";");
            for (String s: strings){
                assets.add(s);
            }
            orderVo.setAssets(assets);
        }
        return orderVo;
    }

    public ServerResponse getOrderDetail(Long orderNo, Integer uid){
        Order order=orderMapper.selectByOrderNo(orderNo);
        if(order==null){
            return ServerResponse.createByErrorMessage("no such order");
        }
        if (!order.getUserProfileId().equals(uid)){
            return ServerResponse.createByErrorMessage("illegal operation, try to get others' order detail");
        }
        OrderVo orderVo=assembleOrderVo(order);
        if(order.getAcceptUserId().equals(uid)){
            orderVo.setAcceptUserId(null);
        }
        return ServerResponse.createBySuccess(orderVo);
    }

    public ServerResponse changeOrderStatus(Long orderNo, Integer status, Integer uid){
        Order order=orderMapper.selectByOrderNo(orderNo);
        if(order==null){
            return ServerResponse.createByErrorMessage("no such order");
        }
//        if (!order.getUserProfileId().equals(uid)){
//            return ServerResponse.createByErrorMessage("illegal operation, try to alter others' order status");
//        }
        order.setStatus(status);
        if(status.equals(Const.OrderStatusEnum.ACCEPTED.getCode())){
            order.setAcceptUserId(uid);
        }
        int result=orderMapper.updateByPrimaryKeySelective(order);
        if (result==0){
            return ServerResponse.createByErrorMessage("update failed");
        }
        return ServerResponse.createBySuccess();
    }

    public ServerResponse getCandidates(Long orderNo, Integer uid){
        Order order=orderMapper.selectByOrderNo(orderNo);
        if(order==null){
            return ServerResponse.createByErrorMessage("no such order");
        }
        BigDecimal latitute=order.getLocationLatitute();
        BigDecimal longitude=order.getLocationLongitude();
        BigDecimal latituteUp=latitute.add(Const.PositionRange.RANGE);
        BigDecimal latituteBot=latitute.subtract(Const.PositionRange.RANGE);
        BigDecimal longitudeUp=longitude.add(Const.PositionRange.RANGE);
        BigDecimal longitudeBot=longitude.subtract(Const.PositionRange.RANGE);
        Integer orderTag=order.getTag();
        PageHelper.startPage(Const.PageSettings.PAGE_NUMBER,Const.PageSettings.PAGE_SIZE);
        List<User> userList=userMapper.selectList(latituteUp,latituteBot,longitudeUp,longitudeBot);
        List<User> result=Lists.newArrayList();
        for(User user:userList){
            int temp=userTagMapper.checkUserTag(user.getId(),orderTag);
            if(temp==1 && !user.getId().equals(uid)){
                result.add(user);
            }
        }
        if(result.isEmpty()){
            return ServerResponse.createBySuccess("no available users");
        }
        PageInfo pageInfo=new PageInfo();
        pageInfo.setList(result);
        return ServerResponse.createBySuccess(pageInfo);
    }

}
