package com.springbootmusic.music.Mapper;


import com.springbootmusic.music.domain.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectMapper {
    /**
     * 增加
     */
    public int insert(Collect collect);

    /**
     * 修改
     */
    public int update(Collect collect);

    /**
     * 删除
     */
    public int delete(Integer songId,Integer userId);


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
    public int existSongIdAndUserId(@Param("userId") Integer userId, @Param("songId") Integer songId);
}
