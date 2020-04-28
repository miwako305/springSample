package com.example.SpringSample.recipelist.controller;

import com.example.SpringSample.recipelist.domain.model.Recipe;
import com.example.SpringSample.recipelist.domain.service.RecipelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecipelistController {

    @Autowired
    RecipelistService recipeService;
    // 結婚ステータスのラジオボタン用変数
    private Map<String, String> radioMarriage;

    /**
     * ラジオボタンの初期化メソッド（ユーザー登録画面と同じ）.
     */
    private Map<String, String> initRadioMaterials() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 選択結果を格納
        radio.put("卵", "false");
        radio.put("茄子", "false");
        radio.put("条件なし", "true");
        return radio;
    }


    /**
     * ホーム画面のGET用メソッド
     */
    @GetMapping("/recipelist")
    public String getHome(@ModelAttribute Recipe recipe, Model model) {

        // ラジオボタンの初期化メソッド呼び出し
        var radioMaterials = initRadioMaterials();

        // ラジオボタン用のMapをModelに登録
        model.addAttribute("Materials", radioMaterials);

        //コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "recipelist/resultTable::resultTable_contents");

        List<Recipe> recipes = recipeService.selectALL();
        model.addAttribute("recipes", recipes);
        return "recipelist/index";
    }
}
