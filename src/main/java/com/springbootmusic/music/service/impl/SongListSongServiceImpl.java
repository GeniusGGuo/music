package com.springbootmusic.music.service.impl;

import com.springbootmusic.music.Mapper.SongListSongMapper;
import com.springbootmusic.music.domain.SongListSong;
import com.springbootmusic.music.service.SongListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListSongServiceImpl implements SongListSongService {
    @Autowired
    private SongListSongMapper songListSongMapper;

    /**
     * @param songListSong
     * @return
     */
    @Override
    public boolean insert(SongListSong songListSong) {
        return songListSongMapper.insert(songListSong) > 0;
    }

    /**
     * @param songListSong
     * @return
     */
    @Override
    public boolean update(SongListSong songListSong) {
        return songListSongMapper.update(songListSong) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songListSongMapper.delete(id) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SongListSong selectByPrimaryKey(Integer id) {
        return songListSongMapper.selectByPrimaryKey(id);
    }

    /**
     * @return
     */
    @Override
    public List<SongListSong> allSongListSong() {
        return songListSongMapper.allSongListSong();
    }

    /**
     * @param songListId
     * @return
     */
    @Override
    public List<SongListSong> songListSongOfId(Integer songListId) {
        return songListSongMapper.songListSongOfId(songListId);
    }
}

