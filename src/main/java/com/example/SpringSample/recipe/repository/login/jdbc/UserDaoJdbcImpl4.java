package com.example.SpringSample.recipe.repository.login.jdbc;

import com.example.SpringSample.recipe.domain.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDaoJdbcImpl4")
public class UserDaoJdbcImpl4 extends UserDaoJdbcImpl {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<User> selectMany() {

        //M_USERテーブルのデータを全件取得するSQL
        String sql = "SELECT * FROM m_user";

        //ResultSetExtractorの生成
        UserResultSetExtractor extractor = new UserResultSetExtractor();

        //SQL実行
        return jdbc.query(sql, extractor);
    }
}