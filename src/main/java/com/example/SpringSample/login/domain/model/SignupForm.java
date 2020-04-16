package com.example.SpringSample.login.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {

    /* 【6-3-2】ポイント message属性　各アノテーション属性にmessage属性を付ける事によって
    message.propatiesの独自keyと紐付けする事ができるようになります。
    {}をつかない場合、設定した文字がエラーメッセージとしてとして表示されます。
    @NotBlank(message = "パスワード入ってない"
    */
    //必須入力、メールアドレス形式
    @NotBlank(message = "{require_check}")
    @Email(message = "{email_check}")
    private String userId; // ユーザーID

    //必須入力、長さ4から100桁まで、半角英数字のみ
    @NotBlank(message = "{require_check}")
    @Length(message = "{length_check}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "{pattern_check}")
    private String password; // パスワード

    //必須入力
    @NotBlank(message = "{require_check}")
    private String userName; // ユーザー名
    /* 【6-2-2】ポイント　＠DateTimeFormat(データバインド用　アノテーション)
        ＠をフィールドに付ける事で画面から渡されてきた文字列を日付型に変換してくれます。
        なおpatern属性にどのようなフォーマットでそうしんされるかを設定します
        ＠NumberFormat(pattern ="#,###")
    */
    //必須入力
    @NotNull(message = "{require_check}")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday; // 誕生日

    //値が20から100まで
    @Min(value = 20, message = "{min_check}")
    @Max(value = 100,message = "{max_check}")
    private int age; // 年齢

    //falseのみ可能
    @AssertFalse(message = "{false_check}")
    private boolean marriage; // 結婚ステータス
}
