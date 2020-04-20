package com.example.SpringSample.login.domain.repository.jdbc;

import com.example.SpringSample.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDaoJdbcImpl2")
public class UserDaoJdbcImpl2 extends UserDaoJdbcImpl {

    @Autowired
    private JdbcTemplate jdbc;

    //ユーザー１件取得
    @Override
    public User selectOne(String userId) {

        //１件取得用SQL
        String sql = "SELECT * FROM m_user WHERE user_id = ?";

        //RowMapperの生成
        UserRowMapper rowMapper = new UserRowMapper();

        //SQL実行
        return jdbc.queryForObject(sql, rowMapper, userId);
    }

    //ユーザー全件取得
    @Override
    public List<User> selectMany() {

        //M_USERテーブルのデータを全件取得するSQL
        String sql = "SELECT * FROM m_user";

        //RowMapperの生成
        RowMapper<User> rowMapper = new UserRowMapper();

        //SQL実行
        return jdbc.query(sql, rowMapper);
    }
}
