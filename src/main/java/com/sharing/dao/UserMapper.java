package com.sharing.dao;

import com.sharing.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User isUserExist(String email);

    User isValidUser(String email, String password);

    User addUser(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}