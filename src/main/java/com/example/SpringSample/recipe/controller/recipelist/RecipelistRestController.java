package com.example.SpringSample.recipe.controller.recipelist;

import com.example.SpringSample.recipe.domain.recipelist.model.Recipe;
import com.example.SpringSample.recipe.service.recipelist.RecipelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
REST用コントローラー
【11-2-3】@RestController
REST用のコントローラーには@RestControllerをつけます。
こうする事で各メソッドの戻り値にhtmlファイル以外を指定可能になります。
【11-3-1】@RestController
【11-3-2】@RequestBody HTTPリクエストのbody部分を引数にマッピンングしてくれる
 */
@RestController
public class RecipelistRestController {

    @Autowired
    @Qualifier("RecipelistServiceMybatisImpl")
    RecipelistService service;

    @GetMapping("/recipelist/get")
    public List<Recipe> getUserMany() {
        // ユーザー全件取得　（json）
        return service.selectALL();
    }
}
