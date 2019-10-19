package com.sharing.dao;

import com.sharing.pojo.Order;
import com.sharing.pojo.User;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User isUserExist(String email);

    User isValidUser(@Param("email")String email, @Param("password")String password);

    int addNewUser(User user);

    int updateLoById(@Param("id") Integer id, @Param("latitute") BigDecimal latitute, @Param("longitude") BigDecimal longitude);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkEmail(String email);

    User selectByEmail(@Param("email") String email, @Param("password") String password);

    List<User> selectList(@Param("latituteUp") BigDecimal latituteUp, @Param("latituteBot") BigDecimal latituteBot, @Param("longitudeUp") BigDecimal longitudeUp, @Param("longitudeBot") BigDecimal longitudeBot);

}