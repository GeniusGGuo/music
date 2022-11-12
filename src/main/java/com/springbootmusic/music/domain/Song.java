package com.springbootmusic.music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 歌曲
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    //主键
    private Integer id;
    //歌手id
    private Integer singerId;
    //歌曲名字
    private String name;
    //歌曲简介
    private String introduction;
    //创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
    //歌曲图片
    private String pic;
    //歌词
    private String lyric;
    //歌曲地址
    private String url;
}
