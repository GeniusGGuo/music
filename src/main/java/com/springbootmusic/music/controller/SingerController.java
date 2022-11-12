package com.springbootmusic.music.controller;


import com.springbootmusic.music.domain.Singer;
import com.springbootmusic.music.service.SingerService;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 歌手控制类
 */
@RestController

public class SingerController {
    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     */

    @RequestMapping(value = "/singer/add", method = RequestMethod.POST)
    public ResponseResult addSinger(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(date);
        boolean flag = singerService.insert(singer);
        if (flag) {
            return new ResponseResult(1, "添加成功");
        }
        return new ResponseResult(0, "添加失败");
    }

    /**
     * 修改歌手
     */
    @RequestMapping(value = "/singer/update", method = RequestMethod.POST)
    public ResponseResult updateSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(date);
        singer.setId(Integer.parseInt(id));
        boolean flag = singerService.update(singer);
        if (flag) {
            return new ResponseResult(1, "修改成功");
        }
        return new ResponseResult(0, "修改失败");
    }

    /**
     * 删除歌手
     */
    @RequestMapping(value = "/singer/delete", method = RequestMethod.GET)
    public ResponseResult deleteSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        boolean flag = singerService.delete(Integer.parseInt(id));
        if (flag) {
            return new ResponseResult(1, "删除成功");
        }
        return new ResponseResult(0, "删除失败");
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/singer/selectByPrimaryKey", method = RequestMethod.GET)
    public ResponseResult selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        Singer singer = singerService.selectByPrimaryKey(Integer.parseInt(id));
        if (singer != null) {
            return new ResponseResult(1, "查询成功", singer);
        } else if (singer == null) {
            return new ResponseResult(1, "查询失败，数据库里没有" + id + "的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 查询所有歌手
     */
    @RequestMapping(value = "/singer", method = RequestMethod.GET)
    public ResponseResult allSinger() {
        List<Singer> singers = singerService.allSinger();
        if (singers != null) {
            return new ResponseResult(1, "查询成功", singers);
        } else if (singers == null) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 根据歌手名字模糊查询列表
     */
    @RequestMapping(value = "/singer/singerOfName", method = RequestMethod.GET)
    public ResponseResult singerOfName(HttpServletRequest request) {
        String singerName = request.getParameter("name").trim();
        List<Singer> singers = singerService.singerOfName("%" + singerName + "%");
        if (singers != null) {
            return new ResponseResult(1, "查询成功", singers);
        } else if (singers == null) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 根据歌手性别查询列表
     */
    @RequestMapping(value = "/singer/singerOfSex", method = RequestMethod.GET)
    public ResponseResult singerOfSex(HttpServletRequest request) {
        String sex = request.getParameter("sex").trim();
        List<Singer> singers = singerService.singerOfSex(Integer.parseInt(sex));
        if (singers != null) {
            return new ResponseResult(1, "查询成功", singers);
        } else if (singers == null) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 更新歌手图片
     */
    @RequestMapping(value = "/singer/updateSingerPic", method = RequestMethod.POST)
    public ResponseResult updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        if (avatorFile.isEmpty()) {
            return new ResponseResult(0,"文件流为空");
        }
        //文件名                  获取系统时间转换为毫秒                  原文件名称
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //储存路径                                 先获取static的文件路径
        /*String staticPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                System.getProperty("file.separator") + "main" + System.getProperty("file.separator") + "resources" +
                System.getProperty("file.separator") + "static" + */
        /*String staticPath= ClassUtils.getDefaultClassLoader().getResource("static").getPath()+
                System.getProperty("file.separator") + "img" +
                System.getProperty("file.separator") + "singerPic";
        File file1 = new File(staticPath);
        //判断文件是否存在 如果不存在则创建
        if (!file1.exists()) {
            file1.mkdir();
        }*/
        File dest = new File("/music/static/img/singerPic" + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean res = singerService.update(singer);
            if (res) {
                return new ResponseResult(1,"图片更改成功",storeAvatorPath);
            } else {
                return new ResponseResult(0,"更改失败");
            }
        } catch (IOException e) {
            return new ResponseResult(0,"图片更改失败",e.getMessage());
        }
    }

}
