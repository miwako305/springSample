package com.example.SpringSample.recipelist.domain.service;

import com.example.SpringSample.recipelist.domain.model.Recipe;

import java.util.List;

public interface RecipelistService {
    //全件検索用メソッド
    List<Recipe> selectALL();
}
