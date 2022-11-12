package com.springbootmusic.music.service;

import com.springbootmusic.music.domain.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 增加
     */
    public boolean insert(Comment comment);

    /**
     * 修改
     */
    public boolean update(Comment comment);

    /**
     * 删除
     */
    public boolean delete(Integer id);

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
