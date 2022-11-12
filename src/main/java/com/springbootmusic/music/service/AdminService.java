package com.springbootmusic.music.service;

import com.springbootmusic.music.domain.Admin;
import org.springframework.stereotype.Service;

/**
 * 管理员service接口
 */

public interface AdminService {

    Admin verifyPassword(String username, String password);
}
