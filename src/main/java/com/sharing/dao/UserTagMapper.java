package com.sharing.dao;

import com.sharing.pojo.UserTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTagMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUserId(Integer userProfileId);

    int insert(UserTag record);

    int insertSelective(UserTag record);

    UserTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTag record);

    int updateByPrimaryKey(UserTag record);

    List<Integer> selectByUserId(Integer uid);

    int checkUserTag(@Param("uid") Integer uid, @Param("tag") Integer tag);
}