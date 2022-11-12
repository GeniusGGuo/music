package com.springbootmusic.music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id; //主键
    private Integer userId; //用户id
    private Byte type; //收藏类型 （0，歌曲 1，歌单）
    private Integer songId;//歌曲id
    private Integer songListId; //歌单id
    private Date createTime;//收藏时间
    private String content ;//评论内容
    private Integer up; //点赞数;
}
