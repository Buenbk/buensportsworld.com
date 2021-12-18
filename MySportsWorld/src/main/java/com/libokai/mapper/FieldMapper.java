package com.libokai.mapper;

import com.libokai.pojo.Field;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FieldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Field record);

    Field selectByPrimaryKey(Integer id);

    List<Field> selectAll();

    int updateByPrimaryKey(Field record);

    Field selectFieldDetails(Integer id);

    Field selectByPrimaryKeySimple(Integer id);
}