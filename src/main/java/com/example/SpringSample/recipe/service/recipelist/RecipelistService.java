package com.example.SpringSample.recipe.service.recipelist;

import com.example.SpringSample.recipe.domain.recipelist.model.Recipe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Qualifier("RecipelistServiceMybatisImpl")
public interface RecipelistService {
    //全件検索用メソッド
    List<Recipe> selectALL();
}
