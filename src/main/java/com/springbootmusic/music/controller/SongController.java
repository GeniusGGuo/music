package com.springbootmusic.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.springbootmusic.music.domain.Song;
import com.springbootmusic.music.service.SongService;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    //    添加歌曲
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult addSong(HttpServletRequest req, @RequestParam("file") MultipartFile mpfile) {
        JSONObject jsonObject = new JSONObject();
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/songPic/yue.jpeg";
        String lyric = req.getParameter("lyric").trim();
        if (mpfile.isEmpty()) {
            return new ResponseResult(0, "文件流为空");
        }
        //文件名                  获取系统时间转换为毫秒                  原文件名称
        String fileName = System.currentTimeMillis() + mpfile.getOriginalFilename();
        /*String staticPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                System.getProperty("file.separator") + "main" + System.getProperty("file.separator") + "resources" +
                System.getProperty("file.separator") + "static" + System.getProperty("file.separator") + "song";*/
        String staticPath= "/music/static/song";
        File file1 = new File(staticPath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(file1+System.getProperty("file.separator")+fileName);
        String storeUrlPath = "//song/" + fileName;
        try {
            mpfile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean res = songService.insert(song);
            if (res) {
                return new ResponseResult(1, "上传成功",storeUrlPath);
            } else {
                return new ResponseResult(0, "上传失败");
            }
        } catch (IOException e) {
            return new ResponseResult(0, "上传失败了失败",e.getMessage());
        }
    }

    //    返回所有歌曲
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseResult allSong() {
        List<Song> songs = songService.allSong();
        return new ResponseResult(1, "查询成功", songs);
    }

    //    根据歌手ID查询歌曲
    @RequestMapping(value = "/singer/detail", method = RequestMethod.GET)
    public ResponseResult songOfSingerId(HttpServletRequest req) {
        String singerId = req.getParameter("singerId");
        List<Song> songs = songService.songOfSingerId(Integer.parseInt(singerId));
        return new ResponseResult(1, "查询成功", songs);
    }

    //    根据歌手名查询歌曲
    @RequestMapping(value = "/singerName/detail", method = RequestMethod.GET)
    public ResponseResult songOfSingerName(HttpServletRequest req) {
        String singerName = req.getParameter("name");
        List<Song> songs = songService.songOfName(singerName);
        return new ResponseResult(1, "查询成功", songs);
    }

    //    根据歌曲名查询歌曲
    @RequestMapping(value = "/name/detail", method = RequestMethod.GET)
    public ResponseResult songOfName(HttpServletRequest req) {
        String name = req.getParameter("name").trim();
        List<Song> songs = songService.songOfName(name);
        return new ResponseResult(1, "查询成功", songs);
    }

    //    删除歌曲
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseResult deleteSong(HttpServletRequest req) {
        String id = req.getParameter("id");
        boolean flag = songService.delete(Integer.parseInt(id));
        if (flag) {
            return new ResponseResult(1, "删除成功");
        }
        return new ResponseResult(0, "删除成功");
    }


    //    更新歌曲信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult updateSongMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String lyric = req.getParameter("lyric").trim();
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setSingerId(Integer.parseInt(singer_id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setUpdateTime(new Date());
        song.setLyric(lyric);
        boolean res = songService.update(song);
        if (res) {
            return new ResponseResult(1, "修改成功");
        } else {
            return new ResponseResult(1, "修改失败");
        }
    }

    //    更新歌曲图片
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public ResponseResult updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        if (urlFile.isEmpty()) {
            return new ResponseResult(0, "音乐修改失败");
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = "/music/static/img/songPic/";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/songPic/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeUrlPath);
            boolean res = songService.update(song);
            if (res) {
                return new ResponseResult(1, "图片修改成功");
            } else {
                return new ResponseResult(0, "图片修改失败");
            }
        } catch (IOException e) {
            return new ResponseResult(0, "图片修改失败", e.getMessage());
        }
    }

    //    更新歌曲URL
    @RequestMapping(value = "/updateUrl", method = RequestMethod.POST)
    public ResponseResult updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        if (urlFile.isEmpty()) {
            return new ResponseResult(0, "音乐修改失败");
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = "/music/static/song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "//song/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean res = songService.update(song);
            if (res) {
                return new ResponseResult(1, "音乐修改成功");
            } else {
                return new ResponseResult(0, "音乐修改失败");
            }
        } catch (IOException e) {
            return new ResponseResult(0, "音乐修改失败", e.getMessage());
        }
    }

    //根据歌曲ID查询歌曲
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseResult songOfSongId(HttpServletRequest req) {
        String id = req.getParameter("id");
        Song song = songService.selectByPrimaryKey(Integer.parseInt(id));
        return new ResponseResult(1, "查询成功", song);
    }

    //根据歌名模糊查找歌曲
    @RequestMapping(value = "/likeName/detail",method = RequestMethod.GET)
    public ResponseResult songOfLikeName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        List<Song> songs = songService.songOfLikeName(name);
        return new ResponseResult(1,"查询成功",songs);
    }
}
