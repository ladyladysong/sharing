package com.sharing.service.Impl;

import com.sharing.common.ServerResponse;
import com.sharing.dao.OrderMapper;
import com.sharing.dao.RatingMapper;
import com.sharing.dao.UserMapper;
import com.sharing.pojo.Order;
import com.sharing.pojo.Rating;
import com.sharing.pojo.User;
import com.sharing.service.iRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service("iRatingService")
public class iRatingImpl implements iRatingService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RatingMapper ratingMapper;

    @Override
    @Transactional
    public ServerResponse rateUser(Integer uid, Integer oid, double rate) {
        User user=userMapper.selectByPrimaryKey(uid);
        if(user==null){
            return ServerResponse.createByErrorMessage("invalid user");
        }
        Order order=orderMapper.selectByPrimaryKey(oid);
        if(order==null){
            return ServerResponse.createByErrorMessage("invalid order");
        }
        int count=ratingMapper.checkByUidAndOid(uid,oid);
        if(count>0){
            return ServerResponse.createBySuccessMessage("rate already exist");
        }
        Rating rating=new Rating();
        rating.setOrderId(oid);
        rating.setUserProfileId(uid);
        rating.setRate(new BigDecimal(rate));
        int result=ratingMapper.insert(rating);
        if (result==0){
            return ServerResponse.createByErrorMessage("rating failed");
        }
        int orderCount=ratingMapper.countByUser(uid);
        BigDecimal newRate=user.getRate().multiply(BigDecimal.valueOf(orderCount-1)).add(BigDecimal.valueOf(rate));
        newRate.divide(BigDecimal.valueOf(orderCount));
        user.setRate(newRate);
        int updateResult=userMapper.updateByPrimaryKeySelective(user);
        if (updateResult==0){
            return ServerResponse.createByErrorMessage("rating failed");
        }
        return ServerResponse.createBySuccess();
    }
}
