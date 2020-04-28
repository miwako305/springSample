package com.example.SpringSample.recipelist.domain.repository.mybatis;

import com.example.SpringSample.recipelist.domain.model.Recipe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecipeMapper {
    // 全件取得用
    public List<Recipe> selectALL();
}
