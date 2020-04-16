package com.example.SpringSample.login.domain.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class SignupForm {

    private String userId; // ユーザーID
    private String password; // パスワード
    private String userName; // ユーザー名

    /* 【6-2-2】ポイント　＠DateTimeFormat(データバインド用　アノテーション)
     ＠をフィールドに付ける事で画面から渡されてきた文字列を日付型に変換してくれます。
     なおpatern属性にどのようなフォーマットでそうしんされるかを設定します。
    　・＠NumberFormat(pattern ="#,###")
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday; // 誕生日
    private int age; // 年齢
    private boolean marriage; // 結婚ステータス
}
