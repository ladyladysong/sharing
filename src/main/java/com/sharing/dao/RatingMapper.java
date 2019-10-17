package com.sharing.dao;

import com.sharing.pojo.Rating;
import org.apache.ibatis.annotations.Param;

public interface RatingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rating record);

    int insertSelective(Rating record);

    Rating selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rating record);

    int updateByPrimaryKey(Rating record);

    int checkByUidAndOid(@Param("uid") Integer uid, @Param("oid") Integer oid);

    int countByUser(Integer uid);
}