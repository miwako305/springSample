package com.example.SpringSample.recipelist.domain.service.mybatis;

import com.example.SpringSample.recipelist.domain.model.Recipe;
import com.example.SpringSample.recipelist.domain.repository.mybatis.RecipeMapper;
import com.example.SpringSample.recipelist.domain.service.RecipelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("RecipelistServiceMybatisImpl")
public class RecipelistServiceMybatisImpl implements RecipelistService {

    @Autowired
    RecipeMapper recipeMapper;

    @Override
    public List<Recipe> selectALL() {
        //select実行
        return recipeMapper.selectALL();
    }
}
