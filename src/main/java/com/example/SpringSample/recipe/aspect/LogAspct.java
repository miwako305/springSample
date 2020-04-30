package com.example.SpringSample.recipe.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
    【7-3-2】ポイント2：AOPの実装 ＠Beforeや＠After
   　AOPを実装するメソットには＠Beforeや＠Afterのアノテーションを付けます。JointPoint（タイミング）
     と同じ名称を指定します。このパラメーターにはどのクラスのメソットが呼び出されたかを指定します。
       ex)@After(....) public void endog(JoinPoint jp) {}
    ・"excution(<戻り値><パッケージ名>.<クラス名>.<メソッド名>(<引数>))"
       ex)"execution( * com.example.SpringSample.recipelist.controller.LoginController.getLogin(..))"
    ・"excution(<全ての戻り値><全てのパッケージ名>.<全てのController>.<全てのメソッド名>(<全ての引数>))"
    　　ex)"execution( *　*..*.*Controller.*(..))"

    【パッケージ名・クラス名で使用可能な正規表現】
    『*』　一階層のパッケージ　メソッド1つの引数
    『..』0以上のパッケージ　0以上の引数/全ての引数
    『+』　クラス名の後に付けると指定クラスのサブクラス/実装クラス
    『*..*』 全てのパッケージ
    『*Controller』末尾がControllerの全てのPKG
*/

    /**
　　　* コントローラークラスのログ出力用アスペクト.
     */
    /*
    【7-3-4】pointcut(実行場所)の指定方法 @Around
    ・bean(<bean名>) :DIに登録されているBean名でAOPの対象を指定できます。正規表現も使えます。
    　ex)@Around("bean(*Controller)")
    ・@annotation(<アノテーションメソッドパッケージ名.クラス名>)　：指定したアノテーションが付いているメソッドを全て対象とします。
　　　 ex)@Around("@annotation(org.springframework.web.bind.annotation.GetMapping")
＿  ・@within(<アノテーションメソッドパッケージ名.クラス名>)　：指定したアノテーションが付いているメソッドを全て対象とします。
      ex)@Around("@within(org.springframework.web.bind.annotation.GetMapping")
 */
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("メソッド開始： " + jp.getSignature());

        try {
            /*
            ポイント２：メソッド実行
            【7-3-3】Aroundを使う場合、アノテーションを付けたメソッドの中でAOP対象クラスのメソッドを直接実行します。
            proceedメソッドの前後で任意の処理を実行する事ができるようになります。
            returnには実行結果の戻り値を指定します
　　　　　　　　*/
            Object result = jp.proceed();

            System.out.println("メソッド終了： " + jp.getSignature());

            return result;

        } catch (Exception e) {
            System.out.println("メソッド異常終了： " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

    // UserDao クラスのログ出力
    @Around("execution(* *..*.*UserDao.*(..))")
    public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("メソッド開始：" + jp.getSignature());
        try {
            Object result = jp.proceed();
            System.out.println("メソッド終了" + jp.getSignature());
            return result;
        } catch (Exception e) {
            System.out.println("メソッド異常終了：" + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}
