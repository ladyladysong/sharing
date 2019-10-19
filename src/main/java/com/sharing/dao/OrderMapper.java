package com.sharing.dao;

import com.sharing.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectList(@Param("latituteUp") BigDecimal latituteUp,@Param("latituteBot") BigDecimal latituteBot, @Param("longitudeUp") BigDecimal longitudeUp, @Param("longitudeBot") BigDecimal longitudeBot, @Param("tags") List<Integer> tags);

    Order selectByOrderNo(Long orderNo);

}