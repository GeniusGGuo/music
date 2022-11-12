package com.springbootmusic.music.service.impl;

import com.springbootmusic.music.Mapper.CollectMapper;
import com.springbootmusic.music.domain.Collect;
import com.springbootmusic.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    /**
     * 增加
     *
     * @param collect
     */
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }

    /**
     * 修改
     *
     * @param collect
     */
    @Override
    public boolean update(Collect collect) {
        return collectMapper.update(collect)>0;
    }

    /**
     * 删除
     *
     * @param userId,songId
     */
    @Override
    public boolean delete(Integer userId,Integer songId) {
        return collectMapper.delete(userId,songId)>0;
    }

    /**
     * 查询所有用户的收藏
     */
    @Override
    public List<Collect> allCollect() {
        return collectMapper.allCollect();
    }

    /**
     * 根据用户id查询用户的收藏
     *
     * @param userId
     */
    @Override
    public List<Collect> findCollectOfUserId(Integer userId) {
        return collectMapper.findCollectOfUserId(userId);
    }

    /**
     * 查询弄个用户是否收藏了某个歌曲
     *
     * @param userId
     * @param songId
     */
    @Override
    public boolean existSongIdAndUserId(Integer userId, Integer songId) {
        return collectMapper.existSongIdAndUserId(userId,songId)>0;
    }
}
