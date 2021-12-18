package com.libokai.mapper;

import com.libokai.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByEmail(String email);

    int updateByPrimaryKeySimple(User record);

    int insertSimple(User record);

}