package com.springbootmusic.music.Mapper;

import com.springbootmusic.music.domain.Singer;
import com.springbootmusic.music.domain.Song;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SongMapper {
    /**
     * 增加
     */
    public int insert(Song song);

    /**
     * 修改
     */
    public int update(Song song);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     */
    public List<Song> allSong();

    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSingerId(Integer id);

    /**
     * 根据歌名查询列表
     */
    public List<Song> songOfName(String name);

    /**
     * 根据歌名模糊查询列表
     */
    public List<Song> songOfLikeName(String name);

}
