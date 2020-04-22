package com.example.SpringSample.login.domain.repository.mybatis;

import com.example.SpringSample.login.domain.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
// 【13-3】@Mapper　myBtiesdでSQLを実行するクラスには＠Mapperアノテーションをつけます
@Mapper
public interface UserMapper {
    /*
     【13-3】: 変数の指定　
     SQLを実行するクラスには@Insertやアノテーションをつけます。
     そしてその引数にSQL文をセットします。
     #{<変数名>}と指定する事でSQL文にメソッドの引数をセットできるようになります。
    */

    // 登録用メソッド
    @Insert("INSERT INTO m_user ("
            + " user_id,"
            + " password,"
            + " user_name,"
            + " birthday,"
            + " age,"
            + " marriage,"
            + " role)"
            + " VALUES ("
            + " #{userId},"
            + " #{password},"
            + " #{userName},"
            + " #{birthday},"
            + " #{age},"
            + " #{marriage},"
            + " #{role})")
    public boolean insert(User user);

    // １件検索用メソッド
    @Select("SELECT user_id AS userId,"
            + "password,"
            + "user_name AS userName,"
            + "birthday,"
            + "age,"
            + "marriage,"
            + "role"
            + " FROM m_user"
            + " WHERE user_id = #{userId}")
    public User selectOne(String userId);

    /*
    【13-3】: カラム名
     Select文を実行してその戻り値をUserなどの参照型にしているメソッドには注意が必要です。
     それは、テーンブルのカラム名とクラスのフィールド名を一致させないといけないからです。
     ユーザーIDの例でいうと↓のようになります。
     　m_user テーブルのカラム名： user_id
       User クラスのカラム名 : userId
     このように、テーブルのカラムめいとクラスのフィールド名が一致していない場合はSQL文にAS句を使って
     カラム名を変更します。そうする事でカラム名とフィールド名が一致してSelect結果をUserクラスにセットされます。
    */
    // 全件検索用メソッド
    @Select("SELECT user_id AS userId,"
            + "password,"
            + "user_name AS userName,"
            + "birthday,"
            + "age,"
            + "marriage,"
            + "role"
            + " FROM m_user")
    public List<User> selectMany();

    // １件更新用メソッド
    @Update("UPDATE m_user SET"
            + " password = #{password},"
            + " user_name = #{userName},"
            + " birthday = #{birthday},"
            + " age = #{age},"
            + " marriage = #{marriage}"
            + " WHERE user_id = #{userId}")
    public boolean updateOne(User user);

    // １件削除用メソッド
    @Delete("DELETE FROM m_user WHERE user_id = #{userId}")
    public boolean deleteOne(String userId);
}