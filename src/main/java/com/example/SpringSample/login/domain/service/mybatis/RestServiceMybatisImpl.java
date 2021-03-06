package com.example.SpringSample.login.domain.service.mybatis;

import com.example.SpringSample.login.domain.model.User;
import com.example.SpringSample.login.domain.repository.mybatis.UserMapper2;
import com.example.SpringSample.login.domain.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("RestServiceMybatisImpl")
public class RestServiceMybatisImpl implements RestService {

    @Autowired
    UserMapper2 userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean insert(User user) {//パスワード暗号化
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        //insert実行
        return userMapper.insert(user);
    }

    @Override
    public User selectOne(String userId) {
        //select実行
        return userMapper.selectOne(userId);
    }

    @Override
    public List<User> selectMany() {
        //select実行
        return userMapper.selectMany();
    }

    @Override
    public boolean update(User user) {
        //パスワード暗号化
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        //update実行
        return userMapper.updateOne(user);
    }

    @Override
    public boolean delete(String userId) {
        //delete実行
        return userMapper.deleteOne(userId);
    }
}
