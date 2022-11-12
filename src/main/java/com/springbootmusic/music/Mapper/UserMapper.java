package com.springbootmusic.music.Mapper;


import com.springbootmusic.music.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 增加
     */
    public int insert(User user);

    /**
     * 修改
     */
    public int update(User user);

    /**
     * 删除
     */
    public int delete(Integer id);

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
    public int verifyPassword(String username, String password);

    /**
     * 根据账号查询
     */
    public User getByUsername(String username);

    /**
     * 登录验证
     * */
    public int loginIn(String username,String password);
}
