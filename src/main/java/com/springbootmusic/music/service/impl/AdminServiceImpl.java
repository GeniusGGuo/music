package com.springbootmusic.music.service.impl;

import com.springbootmusic.music.Mapper.AdminMapper;
import com.springbootmusic.music.domain.Admin;
import com.springbootmusic.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 管理员service实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Admin verifyPassword(String username, String password) {
        return adminMapper.verifyPassword(username, password);
    }
}
