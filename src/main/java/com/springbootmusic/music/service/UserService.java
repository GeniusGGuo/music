package com.springbootmusic.music.service;

import com.springbootmusic.music.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 增加
     */
    public boolean insert(User user);

    /**
     * 修改
     */
    public boolean update(User user);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public User selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     */
    public List<User> allUser();

    /**
     * 验证密码
     */
    public boolean verifyPassword(String username, String password);

    /**
     * 根据账号查询
     */
    public User getByUsername(String username);
    /**
     * 登录验证
     * */
    public boolean loginIn(String username,String password);
}
