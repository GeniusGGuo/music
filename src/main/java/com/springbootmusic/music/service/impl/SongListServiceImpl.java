package com.springbootmusic.music.service.impl;

import com.springbootmusic.music.Mapper.SongListMapper;
import com.springbootmusic.music.domain.SongList;
import com.springbootmusic.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    /**
     * @param songList
     * @return
     */
    @Override
    public boolean insert(SongList songList) {
        return songListMapper.insert(songList) > 0;
    }

    /**
     * @param songList
     * @return
     */
    @Override
    public boolean update(SongList songList) {
        return songListMapper.update(songList) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songListMapper.delete(id) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * @return
     */
    @Override
    public List<SongList> allSongList() {
        return songListMapper.allSongList();
    }

    /**
     * @param title
     * @return
     */
    @Override
    public List<SongList> songListOfTitle(String title) {
        return songListMapper.songListOfTitle(title);
    }

    /**
     * @param title
     * @return
     */
    @Override
    public List<SongList> likeTitle(String title) {
        return songListMapper.likeTitle(title);
    }

    /**
     * @param style
     * @return
     */
    @Override
    public List<SongList> likeStyle(String style) {
        return songListMapper.likeStyle(style);
    }
}
