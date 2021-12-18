package com.libokai.mapper;

import com.libokai.pojo.EmailPassword;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmailPasswordMapper {
    int deleteByPrimaryKey(@Param("email") String email, @Param("password") String password);

    int insert(EmailPassword record);

    List<EmailPassword> selectAll();

    String selectByPrimaryKey(String email);

}