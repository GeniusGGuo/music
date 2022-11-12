package com.springbootmusic.music.Mapper;

import com.springbootmusic.music.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/*
 * 管理员Dao
 * */
@Repository
@Mapper
public interface AdminMapper {
    //       验证密码是否正确
    Admin verifyPassword(String name, String password);


}
