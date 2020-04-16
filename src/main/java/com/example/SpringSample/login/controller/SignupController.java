package com.example.SpringSample.login.controller;

import com.example.SpringSample.login.domain.model.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.Map;

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

    /** 【6-2-2】ポイント1　@ModelAttribute 引数のフォームクラスに　＠ModelAttributeアノテーションを付けると自動的に
    　Modelクラスに登録してくれます。
    　引数に書かないのであれば、　model.add.Atribute("SignupForm",form);
    　@ModelAttributeを付けた場合、デフォルトではクラス名の頭文字を小文字に変えた文字列がキー名に登録されます。
    　↓ソースでは　signupFormの形で登録されています。
    　もしキー名を変えたいのであれば　＠ModelAtribute（"キー名"）とパラメーターを指定します。*/
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {

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

    /*　
    【6-2-2】ポイント BindingResult データバインドの結果の受け取り。
     データバインドの結果を受け取るには、メソッドの引数にBindengResultクラスを追加します。
    　このクラスのhasErrors()メソッドでデータバインドに失敗しているかどうかがわかります。
     バリデーションエラーに対してもhasErrors()メソッドで失敗しているかどうかが判ります　*/
     /*【6-3-1】 ポイント1　バリデーションの実施　バリデーションを実施するには引数のフォームクラスに@Valitatedを付けます。
     *バリデーションの結果はBindingResultクラスに入っています。その為、バリデーションクラスを使う場合でも引数に設定する必要があります。
     */

    public String postSignUp(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult, Model model) {


        /*【6-2-2】 ポイント3　データバインド失敗の場合 データバインドに失敗した場合、
        BindResultのhasErrors()メソッドでfalseが返却されます。↓ソースでは失敗した場合ユーザー
        登録画面に戻ります。その際にはgetSignUpメソッドを呼び出しています。
        理由としてラジオボタンの初期化を行ってくれる為です。
        */

        // 入力チェックに引っかかった場合、ユーザー登録画面に戻る。
        if(bindingResult.hasErrors()){
            //Getリクエスト用のメソッドを呼び出してユーザー登録用画面に戻ります
            return getSignUp(form,model);
        }

        // formの中身をコンソールに出して確認します。
        System.out.println(form);

        /*【6-1-3】ポイント4 リダイレクトする場合はメソッドの返却値にredirect:<遷移先のパス>を指定する
          リダイレクトすると遷移先のcontrollerクラスのメソッドが呼ばれます　*/
        // login.htmlにリダイレクト
        return "redirect:/login";
    }
}