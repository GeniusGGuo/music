package com.springbootmusic.music.controller;

import com.springbootmusic.music.domain.Admin;
import com.springbootmusic.music.service.AdminService;
import com.springbootmusic.music.utils.Consts;
import com.springbootmusic.music.utils.JwtUtil;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录成功
     */
    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public ResponseResult loginStatus(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Admin login = adminService.verifyPassword(name, password);

        if (login!=null) {
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(login.getId()), null);
            return new ResponseResult(1, "登录成功",token);
        }
        return new ResponseResult(0, "用户名或密码错误");
    }
}
