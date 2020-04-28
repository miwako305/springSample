package com.example.SpringSample.recipelist.domain.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Recipe {


    private int menu_id; // メニューID
    private int recipe_id; // レシピID
    private Date update_time; //　登録日
    private String menu_name; //メニュー名
    private String menu_memo; // 備考
    private String method1; // 手順1
    private String method2; //　手順2
    private String method3; //　手順3
    private String method4; //　手順4
    private String method5; //　手順5
    private String method6; //　手順6
    private String method7; //　手順7
    private String method8; //　手順8
    private String method9; //　手順9
    private String material1_vol; // 材料1　分量
    private String material2_vol; // 材料2　分量
    private String material3_vol; // 材料3　分量
    private String material4_vol; // 材料4　分量
    private String material5_vol; // 材料5　分量
    private String material1; // 材料1　
    private String material2; // 材料2
    private String material3; // 材料3
    private String material4; // 材料4
    private String material5; // 材料5
}
