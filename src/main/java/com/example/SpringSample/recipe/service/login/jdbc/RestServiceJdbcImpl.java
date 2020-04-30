package com.example.SpringSample.recipe.service.login.jdbc;

import com.example.SpringSample.recipe.domain.login.model.User;
import com.example.SpringSample.recipe.repository.login.UserDao;
import com.example.SpringSample.recipe.service.login.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RestServiceJdbcImpl implements RestService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    //１件登録用メソッド
    @Override
    public boolean insert(User user) {
        // 登録に成功していれば　true。
        int result = dao.insertOne(user);
        if( result == 0 ) {
            return false;
        }else {
            return true;
        }
    }

    //１件検索用メソッド
    @Override
    public User selectOne(String userId) {

        return dao.selectOne(userId);
    }

    //全件検索用メソッド
    @Override
    public List<User> selectMany() {

        return dao.selectMany();
    }

    //１件更新用メソッド
    @Override
    public boolean update(User user) {
        int result = dao.updateOne(user);

        if( result == 0 ) {
            return false;
        }else {
            return true;
        }
    }

    //１件削除用メソッド
    @Override
    public boolean delete(String userId) {
        int result = dao.deleteOne(userId);

        if( result == 0 ) {
            return false;
        }else {
            return true;
        }
    }

}
