package com.example.SpringSample.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/*
* レポジトリークラス
* DBへのCROUD 操作を行い、その結果を返します。
* MVCで言うとModelに該当します。
*
 */

// 【3-4】ポイント1　＠Repository レポジトリークラスにも＠repositoryをつけます。
@Repository
public class HelloRepository {
    // 【3-4】ポイント2　jdbcTemplate Springが用意しているJDBC接続用のクラスを(JdbcTemplate)を使ってSELECT文を実行しています
    //　JdbcTemplateを使う時は@Autowirdを付けます。　この@Autowirdでインスタンスを生成指定ます。
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Map<String, Object> findOne(int id){
        // SELECT 文
        String query = "SELECT"
                +" employee_id,"
                +" employee_name,"
                +" age "
                +"FROM employee "
                +"WHERE employee_id=?";
        var employee = jdbcTemplate.queryForMap(query,id);
        return employee;
    }
}