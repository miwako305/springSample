package com.example.SpringSample.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/*
 * コントローラークラス
 * ユーザー登録用画面
 *
 */
@Controller
public class SignupController {

    //【6-1-3】ポイント3　タイムリーフを使って値を動的に変更する為にMapを用意
    //ラジオボタン用変数
    private Map<String, String> radioMarriage;

    /**
     * ラジオボタンの初期化メソッド.
     */
    private Map<String, String> initRadioMarrige() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 既婚、未婚をMapに格納
        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    /**
     * ユーザー登録画面のGETメソッド用処理.
     */
    @GetMapping("/signup")
    public String getSignUp(Model model) {

        // ラジオボタンの初期化メソッド呼び出し
        radioMarriage = initRadioMarrige();

        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioMarriage", radioMarriage);

        // signup.htmlに画面遷移
        return "login/signup";
    }

    /**
     * ユーザー登録画面のPOSTメソッド用処理.
     */
    @PostMapping("/signup")
    public String postSignUp(Model model) {
        //【6-1-3】ポイント4 リダイレクトする場合はメソッドの返却値にredirect:<遷移先のパス>を指定する
        // リダイレクトすると遷移先のcontrollerクラスのメソッドが呼ばれます
        // login.htmlにリダイレクト
        return "redirect:/login";
    }
}