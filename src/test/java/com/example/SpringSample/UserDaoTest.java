package com.example.SpringSample;

import com.example.SpringSample.login.domain.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 【12-3-1】
 @RunWith: テストをどのクラスで実行するかを指定する
 @SpringBootTest : Springbootを起動させてから動く
 stati org.junit　のstaticはコードの記述量を減らす・可読性の為に実装されています。
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    // カウントメソッドのテスト1
    @Test
    public void countTest1() {

        // カウントメソッドの結果が2件であることをテスト
        assertEquals(dao.count(), 2);
    }

    // カウントメソッドのテスト2
    // 【12-3-1】@Sql を使用すると、そのSqlを実行した後の状態だけ有効です
    @Test
    @Sql("/testdata.sql")
    public void countTest2() {

        // カウントメソッドの結果が3件であることをテスト
        assertEquals(dao.count(), 3);
    }
}
