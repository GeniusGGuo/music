package com.springbootmusic.music.service.impl;

import com.springbootmusic.music.Mapper.UserMapper;
import com.springbootmusic.music.domain.User;
import com.springbootmusic.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @param user
     * @return
     */
    @Override
    public boolean insert(User user) {
        return userMapper.insert(user) > 0;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean update(User user) {
        return userMapper.update(user) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return userMapper.delete(id) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * @return
     */
    @Override
    public List<User> allUser() {
        return userMapper.allUser();
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return userMapper.verifyPassword(username, password) > 0;
    }

    /**
     * @param username
     * @return
     */
    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    /**
     * 登录验证
     *
     * @param username
     * @param password
     */
    @Override
    public boolean loginIn(String username, String password) {
        return userMapper.loginIn(username,password)>0;
    }
}
