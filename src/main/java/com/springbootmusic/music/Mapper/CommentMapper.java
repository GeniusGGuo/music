package com.springbootmusic.music.Mapper;

import com.springbootmusic.music.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    /**
     * 增加
     */
    public int insert(Comment comment);

    /**
     * 修改
     */
    public int update(Comment comment);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 查询所有评论
     * */
    public List<Comment> findContentAll();

    /**
     * 根据主键查询整个对象
     */
    public Comment selectByPrimaryKey(Integer id);

    /**
     * 查询某个歌曲下的所有评论
     */
    public List<Comment> findSongCommentAll(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     */
    public List<Comment> findSongListCommentAll(Integer songListId);

}
