package com.sharing.dao;

import com.sharing.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User isUserExist(String email);

    User isValidUser(@Param("email")String email, @Param("password")String password);

    User addUser(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}