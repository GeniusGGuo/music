package com.springbootmusic.music.controller;

import com.springbootmusic.music.domain.User;
import com.springbootmusic.music.service.UserService;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult addUser(HttpServletRequest request) {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phone_num").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        String avator = request.getParameter("avator").trim();
        if (username == null) {
            return new ResponseResult(0, "用户名不能为空");
        }
        User User1 = userService.getByUsername(username);
        if (User1!=null){
            return new ResponseResult(0,"用户名已存在");
        }
        if (password == null) {
            return new ResponseResult(0, "密码不能为空");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(new Byte(sex));
        user.setPhoneNum(phoneNum);
        user.setEmail(email);
        user.setBirth(date);
        user.setIntroduction(introduction);
        user.setLocation(location);
        user.setAvator(avator);
        boolean flag = userService.insert(user);
        if (flag) {
            return new ResponseResult(1, "添加成功");
        }
        return new ResponseResult(0, "添加失败");
    }

    /**
     * 修改用户
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult updateSinger(HttpServletRequest request, HttpSession session) {
        String id = request.getParameter("id").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phone_num").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(new Byte(sex));
        user.setPhoneNum(phoneNum);
        user.setEmail(email);
        user.setBirth(date);
        user.setIntroduction(introduction);
        user.setLocation(location);
        user.setId(Integer.parseInt(id));
        boolean flag = userService.update(user);
        if (flag) {
            return new ResponseResult(1, "修改成功");
        }
        return new ResponseResult(0, "修改失败");
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseResult deleteSinger(HttpServletRequest request, HttpSession session) {
        String id = request.getParameter("id").trim();
        boolean flag = userService.delete(Integer.parseInt(id));
        if (flag) {
            return new ResponseResult(1, "删除成功");
        }
        return new ResponseResult(0, "删除失败");
    }


    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public ResponseResult selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        User user = userService.selectByPrimaryKey(Integer.parseInt(id));
        if (user != null) {
            return new ResponseResult(1, "查询成功", user);
        } else if (user == null) {
            return new ResponseResult(1, "查询失败，数据库里没有" + id + "的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 查询所有用户
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseResult allUser() {
        List<User> users = userService.allUser();
        if (users != null) {
            return new ResponseResult(1, "查询成功", users);
        } else if (users == null) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 更新用户图片
     */
    @RequestMapping(value = "/updateUserAvator", method = RequestMethod.POST)
    public ResponseResult updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        if (avatorFile.isEmpty()) {
            return new ResponseResult(0, "上传失败");
        }
        //文件名                  获取系统时间转换为毫秒                  原文件名称
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //储存路径                                 先获取static的文件路径
        String staticPath = "/music/static/img/userAvator/";
        File file1 = new File(staticPath);
        //判断文件是否存在 如果不存在则创建
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(staticPath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/userAvator/" + fileName;
        try {
            avatorFile.transferTo(dest);
            User user = new User();
            user.setId(id);
            user.setAvator(storeAvatorPath);
            boolean res = userService.update(user);
            if (res) {
                return new ResponseResult(1, "上传成功", storeAvatorPath);
            } else {
                return new ResponseResult(0, "上传失败");
            }
        } catch (IOException e) {
            return new ResponseResult(0, "上传失败");
        }
    }

    /**
     * 用户登录验证
     * */
    @RequestMapping(value = "/login/status",method = RequestMethod.POST)
    public ResponseResult loginIn(HttpServletRequest request){
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if (username==null){
            return new ResponseResult(0,"用户名不能为空");
        }
        if (password==null){
            return new ResponseResult(0,"密码不能为空");
        }
        boolean flag = userService.loginIn(username, password);
        if (flag){
            return new ResponseResult(1,"登录成功",userService.getByUsername(username));
        }
        return new ResponseResult(0,"用户名或密码错误");
    }
}
