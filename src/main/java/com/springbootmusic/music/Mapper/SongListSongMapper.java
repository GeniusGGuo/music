package com.springbootmusic.music.Mapper;


import com.springbootmusic.music.domain.SongListSong;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SongListSongMapper {
    /**
     * 增加
     */
    public int insert(SongListSong songListSong);

    /**
     * 修改
     */
    public int update(SongListSong songListSong);

    /**
     * 删除
     */
    public int delete(Integer songId);

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
