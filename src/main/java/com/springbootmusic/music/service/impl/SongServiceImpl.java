package com.springbootmusic.music.service.impl;

import com.springbootmusic.music.Mapper.SongMapper;
import com.springbootmusic.music.domain.Song;
import com.springbootmusic.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;

    /**
     * 增加
     *
     * @param song
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song) > 0;
    }

    /**
     * 修改
     *
     * @param song
     */
    @Override
    public boolean update(Song song) {
        return songMapper.update(song) > 0;
    }

    /**
     * 删除o
     *
     * @param songId
     */
    @Override
    public boolean delete(Integer songId) {
        return songMapper.delete(songId) > 0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌手
     */
    @Override
    public List<Song> allSong() {
        return songMapper.allSong();
    }

    /**
     * 根据歌名精确查询列表
     */
    @Override
    public List<Song> songOfName(String name) {
        return songMapper.songOfName(name);
    }

    /**
     * 根据歌手id查询
     *
     * @param id
     */
    @Override
    public List<Song> songOfSingerId(Integer id) {
        return songMapper.songOfSingerId(id);
    }


    /**
     * 根据歌名模糊查询列表
     *
     * @param name
     */
    @Override
    public List<Song> songOfLikeName(String name) {
        return songMapper.songOfLikeName("%"+name+"%");
    }
}
