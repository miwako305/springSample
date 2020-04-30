package com.example.SpringSample.recipe.service.login;

import com.example.SpringSample.recipe.domain.login.model.User;
import com.example.SpringSample.recipe.repository.login.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserService {

    // 複数ある場合はどのクラスを使用するか指定しないと、エラーになる
    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    /**
     * insert用メソッド.
     */
    public boolean insert(User user) {

        // insert実行
        int rowNumber = dao.insertOne(user);

        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // insert成功
            result = true;
        }

        return result;
    }

    /**
     * カウント用メソッド.
     */
    public int count() {
        return dao.count();
    }

    /**
     * 全件取得用メソッド.
     */
    public List<User> selectMany() {
        // 全件取得
        return dao.selectMany();
    }

    /**
     * 一件取得用メソッド.
     */
    public User selectOne(String userId) {
        // 全件取得
        return dao.selectOne(userId);
    }

    /**
     * １件更新用メソッド.
     */
    public boolean updateOne(User user) {

        // 判定用変数
        boolean result = false;

        // １件更新
        int rowNumber = dao.updateOne(user);

        if (rowNumber > 0) {
            // update成功
            result = true;
        }

        return result;
    }
    /**
     * １件削除用メソッド.
     */
    public boolean deleteOne(String userId) {

        // １件削除
        int rowNumber = dao.deleteOne(userId);

        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // delete成功
            result = true;
        }
        return result;
    }

    // ユーザー一覧をCSV出力する.
    /**
     * @throws DataAccessException
     */
    public void userCsvOut() throws DataAccessException {
        // CSV出力
        dao.userCsvOut();
    }

    /**
     * サーバーに保存されているファイルを取得して、byte配列に変換する.
     */
    public byte[] getFile(String fileName) throws IOException {

        // ファイルシステム（デフォルト）の取得
        FileSystem fs = FileSystems.getDefault();

        // ファイル取得
        Path p = fs.getPath(fileName);

        // ファイルをbyte配列に変換
        byte[] bytes = Files.readAllBytes(p);

        return bytes;
    }


}