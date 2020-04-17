package com.example.SpringSample.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
【7-3-2】ポイント1：＠Aspect　AOPクラスには＠Aspectアノテーションを付けます。
同時にDIコンテナへの　bean定義をする為@Componentアノテーションを付けます。
この二つをセットで付けると覚えて置いてください。
*/
@Aspect
@Component
public class LogAspct {
    /*
【7-3-2】ポイント2：AOPの実装
　AOPを実装するメソットには＠Beforeや＠Afterのアノテーションを付けます。JointPoint（タイミング）
 と同じ名称を指定します。このパラメーターにはどのクラスのメソットが呼び出されたかを指定します。
 ・"excution(<戻り値><パッケージ名>.<クラス名>.<メソッド名>(<引数>))"
    ex)"execution( * com.example.SpringSample.login.controller.LoginController.getLogin(..))"
 ・"excution(<全ての戻り値><全てのパッケージ名>.<全てのController>.<全てのメソッド名>(<全ての引数>))"
 　　ex)"execution( *　*..*.*Controller.*(..))"
 パッケージ名・クラス名には正規表現が使用できます(凡庸化)。
 『*』　一階層のパッケージ　メソッド1つの引数
 『..』0以上のパッケージ　0以上の引数/全ての引数
 『+』　クラス名の後に付けると指定クラスのサブクラス/実装クラス
 『*..*』 全てのパッケージ
 『*Controller』末尾がControllerの全てのPKG
*/
    @Before("execution( * *..*.*Controller.*(..))")
    public void startLog(JoinPoint jp) {
        System.out.println("メソッド開始：" + jp.getSignature());
    }

  //  @After("execution(**..*.*Controller.*(..))")
      @After("execution( * *..*.*Controller.*(..))")
    public void endog(JoinPoint jp) {
        System.out.println("メソッド終了：" + jp.getSignature());

    }

}
