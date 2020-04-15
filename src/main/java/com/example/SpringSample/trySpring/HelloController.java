package com.example.SpringSample.trySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // コントローラーでの設定コンポーネント
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * コントローラークラス
 * どのサービスを使うか指定してサービス結果を画面に返します
 * MVCで言うとに該当Controllerします。
 *
 */

//【Discription】 ポイント1 :@Controller Springではコントローラークラスにアノテーションを付けることでDI（依存性注入）で利用することができるようになります
@Controller
public class HelloController {
    @Autowired
    public HelloService helloService;
    //  【3−2】ポイント2 : @GetMapping  アノテーションをメソッドに付けるとHTTPリクエストのGETメソッドを処理できるようになります
    @GetMapping("/hello")
    public String getHello(Model model){
        var name = "ゲストさん";
        var style ="display:none;";
        model.addAttribute("name",name);
        model.addAttribute("style",style);

        return "hello";
    }
    //【3-3】 ポイント1　＠PostMapping POSTメソッドで送られてきた場合の処理ができるようになります
    //【3-3】 ポイント2　@RequestParam　アノテーションを付けることによって画面からの入力内容を受け取る事ができるようになります。
    // アノテーションの引数には、htmlのname属性の値を指定します。
    @PostMapping("/hello")
    public String postRequest(@RequestParam("text1")String str, Model model){
        //【3-3】 ポイント3　model.addAttribute
        // model.addAttribute(key, value）画面から指定したkeyに値（th:value）を設定する事ができます。
        // 画面から受け取った文字列をmodelに登録する
        var text = str;
        var style ="display:block;";
        var name = "ゲストさん";
        model.addAttribute("name",name);
        model.addAttribute("sample",text);
        model.addAttribute("style",style);

        //【3-3】補足　returnされたhtmlのテキストファイル名が返却されます
       // return "helloResponse";
        return "hello";
    }
    @PostMapping("/hello/db")
    public String postDbRequest(@RequestParam("text2")String str, Model model){
        //Stringからint型に変換
        var  id = Integer.valueOf(str);
        // 1件検索
        Employee employee = helloService.findOne(id);
        model.addAttribute("id",employee.getEmployeeId());
        model.addAttribute("name",employee.getEmployeeName());
        model.addAttribute("age",employee.getAge());
        return "helloResponseDB";

    }
}
