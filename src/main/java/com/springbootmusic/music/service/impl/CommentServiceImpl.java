package com.springbootmusic.music.service.impl;

import com.springbootmusic.music.Mapper.CommentMapper;
import com.springbootmusic.music.domain.Comment;
import com.springbootmusic.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    /**
     * 增加
     *
     * @param comment
     */
    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment)>0;
    }

    /**
     * 修改
     *
     * @param comment
     */
    @Override
    public boolean update(Comment comment) {
        return commentMapper.update(comment)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return commentMapper.delete(id)>0;
    }

    /**
     * 查询所有评论
     */
    @Override
    public List<Comment> findContentAll() {
        return commentMapper.findContentAll();
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @Override
    public List<Comment> findSongCommentAll(Integer songId) {
        return commentMapper.findSongCommentAll(songId);
    }

    /**
     * 查询某个歌单下的所有评论
     *
     * @param
     */
    @Override
    public List<Comment> findSongListCommentAll(Integer songListId) {
        return commentMapper.findSongListCommentAll(songListId);
    }
}
