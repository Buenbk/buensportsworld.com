package com.libokai.mapper;

import com.libokai.pojo.Article2Tag;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Article2TagMapper {
    int deleteByPrimaryKey(@Param("articleId") Integer articleId, @Param("tagId") Integer tagId);

    int insert(Article2Tag record);

    List<Article2Tag> selectAll();
}