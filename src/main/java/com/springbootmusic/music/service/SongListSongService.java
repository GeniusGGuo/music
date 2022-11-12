package com.springbootmusic.music.service;


import com.springbootmusic.music.domain.SongListSong;


import java.util.List;


public interface SongListSongService {
    /**
     * 增加
     */
    public boolean insert(SongListSong songListSong);

    /**
     * 修改
     */
    public boolean update(SongListSong songListSong);

    /**
     * 删除
     */
    public boolean delete(Integer songId);

    /**
     * 根据主键查询整个歌单
     */
    public SongListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<SongListSong> allSongListSong();


    /**
     * 根据歌单id查询所有的歌曲
     */
    public List<SongListSong> songListSongOfId(Integer songListId);
}
