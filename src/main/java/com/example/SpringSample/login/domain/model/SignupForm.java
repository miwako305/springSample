package com.example.SpringSample.login.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {

    //必須入力、メールアドレス形式
    @NotBlank
    @Email
    private String userId; // ユーザーID

    //必須入力、長さ4から100桁まで、半角英数字のみ
    @NotBlank
    @Length(min = 4, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password; // パスワード

    //必須入力
    @NotBlank


    private String userName; // ユーザー名
    /* 【6-2-2】ポイント　＠DateTimeFormat(データバインド用　アノテーション)
        ＠をフィールドに付ける事で画面から渡されてきた文字列を日付型に変換してくれます。
        なおpatern属性にどのようなフォーマットでそうしんされるかを設定します
        ＠NumberFormat(pattern ="#,###")
    */
    //必須入力
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday; // 誕生日

    //値が20から100まで
    @Min(20)
    @Max(100)
    private int age; // 年齢

    //falseのみ可能
    @AssertFalse
    private boolean marriage; // 結婚ステータス
}
