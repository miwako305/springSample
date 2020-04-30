package com.example.SpringSample.recipe.repository.recipelist.mybatis;

import com.example.SpringSample.recipe.domain.recipelist.model.Recipe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecipeMapper {
    // 全件取得用
    public List<Recipe> selectALL();
}
