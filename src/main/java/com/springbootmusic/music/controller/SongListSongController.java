package com.springbootmusic.music.controller;

import com.springbootmusic.music.domain.SongListSong;
import com.springbootmusic.music.service.SongListSongService;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/SongListSong")
public class SongListSongController {

    @Autowired
    private SongListSongService songListSongService;

    /**
     * 查询所有歌单里的歌曲
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseResult allSongList() {
        List<SongListSong> songListSongs = songListSongService.allSongListSong();
        return new ResponseResult(1,"查询成功",songListSongs);
    }

    /**
     * 给歌单新增歌曲
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult addSongList(HttpServletRequest request) {
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        SongListSong songListSong = new SongListSong();
        songListSong.setSongId(Integer.parseInt(songId));
        songListSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = songListSongService.insert(songListSong);
        if (flag) {
            return new ResponseResult(1, "添加成功");
        }
        return new ResponseResult(0, "添加失败");
    }


    /**
     * 更新歌单列表里歌曲的信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult updateSongMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String songId = req.getParameter("songId").trim();
        String songListId = req.getParameter("songListId").trim();
        SongListSong songListSong = new SongListSong();
        songListSong.setSongId(Integer.parseInt(songId));
        songListSong.setId(Integer.parseInt(id));
        songListSong.setSongListId(Integer.parseInt(songListId));
        boolean res = songListSongService.update(songListSong);
        if (res) {
            return new ResponseResult(1, "修改成功");
        } else {
            return new ResponseResult(0, "修改失败");
        }
    }

    /**
     * 删除歌单里的歌曲
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseResult deleteSinger(HttpServletRequest request) {
        String songId = request.getParameter("songId").trim();
        boolean flag = songListSongService.delete(Integer.parseInt(songId));
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
        SongListSong songListSong = songListSongService.selectByPrimaryKey(Integer.parseInt(id));
        if (songListSong != null) {
            return new ResponseResult(1, "查询成功", songListSong);
        } else if (songListSong == null) {
            return new ResponseResult(1, "查询成功但数据库里没有" + id + "的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 根据歌单id查询所有的歌曲
     */
    @RequestMapping(value = "/songListSongOfId", method = RequestMethod.GET)
    public ResponseResult songListSongOfId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId").trim();
        List<SongListSong> songListSongs = songListSongService.songListSongOfId(Integer.parseInt(songListId));
        if (songListSongs != null) {
            return new ResponseResult(1, "查询成功", songListSongs);
        } else if (songListSongs == null) {
            return new ResponseResult(1, "查询成功，但数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }
}
