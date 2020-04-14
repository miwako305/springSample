package com.example.SpringSample.trySpring;
import lombok.Data;

/*
 * 検索結果を入れるクラス
 * domainクラスと呼ばれる　レポジトリー・サービスクラス間で渡すクラスの事をさす。
 *
 */

// 【3-4】ポイント　@Data アノテーションを付けるとgetterやsetterなどを自動生成してくれます。
// これはSpringの機能ではなく、Lombokの機能です。
@Data
public class Employee {
    private int employeeId;
    private String employeeName;
    private int age;
}
