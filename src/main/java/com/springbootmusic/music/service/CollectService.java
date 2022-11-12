package com.springbootmusic.music.service;

import com.springbootmusic.music.domain.Collect;

import java.util.List;

public interface CollectService {
    /**
     * 增加
     */
    public boolean insert(Collect collect);

    /**
     * 修改
     */
    public boolean update(Collect collect);

    /**
     * 删除
     */
    public boolean delete(Integer userId,Integer songId);


    /**
     * 查询所有用户的收藏
     */
    public List<Collect> allCollect();

    /**
     * 根据用户id查询用户的收藏
     */
    public List<Collect> findCollectOfUserId(Integer userId);

    /**
     * 查询弄个用户是否收藏了某个歌曲
     */
    public boolean existSongIdAndUserId(Integer userId,Integer songId);
}
