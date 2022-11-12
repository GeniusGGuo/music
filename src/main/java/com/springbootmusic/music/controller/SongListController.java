package com.springbootmusic.music.controller;

import com.springbootmusic.music.domain.SongList;
import com.springbootmusic.music.service.SongListService;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/songList")
public class SongListController {
    @Autowired
    private SongListService songListService;

    /**
     * 查询所有歌单
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseResult allSongList() {
        List<SongList> songLists = songListService.allSongList();
        return new ResponseResult(1, "查询成功", songLists);
    }

    /**
     * 新增歌单
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult addSongList(HttpServletRequest request) {
        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.insert(songList);
        if (flag) {
            return new ResponseResult(1, "添加成功");
        }
        return new ResponseResult(0, "添加失败");
    }

    /**
     * 更新歌单信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult updateSongMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String style = req.getParameter("style").trim();
        String introduction = req.getParameter("introduction").trim();
        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean res = songListService.update(songList);
        if (res) {
            return new ResponseResult(1, "修改成功");
        } else {
            return new ResponseResult(0, "修改失败");
        }
    }

    /**
     * 删除歌单
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseResult deleteSinger(HttpServletRequest request, HttpSession session) {
        String id = request.getParameter("id").trim();
        boolean flag = songListService.delete(Integer.parseInt(id));
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
        SongList songList = songListService.selectByPrimaryKey(Integer.parseInt(id));
        if (songList != null) {
            return new ResponseResult(1, "查询成功", songList);
        } else if (songList == null) {
            return new ResponseResult(1, "查询失败，数据库里没有" + id + "的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 根据标题查询列表
     */
    @RequestMapping(value = "/singerOfName", method = RequestMethod.GET)
    public ResponseResult songListOfTitle(HttpServletRequest request) {
        String title = request.getParameter("title").trim();
        List<SongList> songLists = songListService.songListOfTitle(title);
        if (songLists != null) {
            return new ResponseResult(1, "查询成功", songLists);
        } else if (songLists == null) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 根据标题模糊查询列表
     */
    @RequestMapping(value = "/likeTitle", method = RequestMethod.GET)
    public ResponseResult likeTitle(HttpServletRequest request) {

        String title = request.getParameter("title").trim();
        List<SongList> songLists = songListService.likeTitle("%" + title + "%");
        if (songLists != null) {
            return new ResponseResult(1, "查询成功", songLists);
        } else if (songLists == null) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 根据歌单风格模糊查询列表
     */
    @RequestMapping(value = "/likeStyle", method = RequestMethod.GET)
    public ResponseResult likeStyle(HttpServletRequest request) {
        String style = request.getParameter("style").trim();
        List<SongList> songLists = songListService.likeStyle("%" + style + "%");
        if (songLists != null) {
            return new ResponseResult(1, "查询成功", songLists);
        } else if (songLists == null) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 更新歌单图片
     */
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public ResponseResult updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        if (urlFile.isEmpty()) {
            return new ResponseResult(0, "修改图片失败");
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = "/music/static/img/songList";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/songList/" + fileName;
        try {
            urlFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeUrlPath);
            boolean res = songListService.update(songList);
            if (res) {
                return new ResponseResult(1, "修改图片成功", storeUrlPath);
            } else {
                return new ResponseResult(0, "修改图片失败");
            }
        } catch (IOException e) {
            return new ResponseResult(0, "修改图片失败", e.getMessage());
        }
    }

}
