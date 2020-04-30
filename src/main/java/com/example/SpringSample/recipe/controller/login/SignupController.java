package com.example.SpringSample.recipe.controller.login;

import com.example.SpringSample.recipe.domain.login.model.GroupOrder;
import com.example.SpringSample.recipe.domain.login.model.SignupForm;
import com.example.SpringSample.recipe.domain.login.model.User;
import com.example.SpringSample.recipe.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    @Autowired
    private UserService userService;

    //【6-1-3】ポイント3　タイムリーフを使って値を動的に変更する為にMapを用意
    //ラジオボタン用変数
    private Map<String, String> radioMarriage;

    /**
     * ラジオボタンの初期化メソッド.
     */
    private Map<String, String> initRadioMarrige() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 既婚、未婚をMapに格納
        return Map.ofEntries(
                Map.entry("既婚", "true"),
                Map.entry("未婚", "false")
        );
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

    public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model) {


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

        // insert用変数
        User user = new User();

        user.setUserId(form.getUserId()); //ユーザーID
        user.setPassword(form.getPassword()); //パスワード
        user.setUserName(form.getUserName()); //ユーザー名
        user.setBirthday(form.getBirthday()); //誕生日
        user.setAge(form.getAge()); //年齢
        user.setMarriage(form.isMarriage()); //結婚ステータス
        user.setRole("ROLE_GENERAL"); //ロール（一般）

        // ユーザー登録処理
        boolean result = userService.insert(user);

        // ユーザー登録結果の判定
        if (result == true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }

        /*【6-1-3】ポイント4 リダイレクトする場合はメソッドの返却値にredirect:<遷移先のパス>を指定する
          リダイレクトすると遷移先のcontrollerクラスのメソッドが呼ばれます　*/
        // login.htmlにリダイレクト
        return "redirect:/login";
    }

    /**
     * DataAccessException発生時の処理メソッド
     * 【9-4】@ExceptionHandler（コントローラー毎にメソッドを作成可能）
     * @ExceptionHandler アノテーションを付けたメソッドを用意すると、
     * メソッドはexceptionごとに複数の例外処理を実装することが可能です
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー（DB）：ExceptionHandler");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    /**
     * Exception発生時の処理メソッド.
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー：ExceptionHandler");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "SignupControllerでExceptionが発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

}
