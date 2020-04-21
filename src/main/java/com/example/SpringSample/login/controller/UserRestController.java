package com.example.SpringSample.login.controller;

import com.example.SpringSample.login.domain.model.User;
import com.example.SpringSample.login.domain.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
REST用コントローラー
【11-2-3】@RestController
REST用のコントローラーには@RestControllerをつけます。
こうする事で各メソッドの戻り値にhtmlファイル以外を指定可能になります。
【11-3-1】@RestController
【11-3-2】@RequestBody HTTPリクエストのbody部分を引数にマッピンングしてくれる
 */
@RestController
public class UserRestController {

    @Autowired
    RestService service;

    @GetMapping("/rest/get")
    public List<User> getUserMany() {
        // ユーザー全件取得　（json）
        return service.selectMany();
    }
    // ユーザー一件取得
    @GetMapping("/rest/get/{id:.+}")
    public User getUserOne(@PathVariable("id") String userId){

        return service.selectOne(userId);
    }

    @PutMapping("/rest/update")
    public String putUserOne(@RequestBody User user) {

        boolean result = service.update(user);
        String str = "";
        if (result == true) {
            str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"ok\"}";
        }
        return str;
    }


    @DeleteMapping("/rest/delete/{id:.+}")
    public String deleteUserOne(@PathVariable("id") String userId){
        boolean result = service.delete(userId);
        String str = "";
        if (result == true) {
            str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"ok\"}";
        }
        return str;
    }
}

