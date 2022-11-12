package com.springbootmusic.music.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 歌单管理
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongList {
    private Integer id; //主键
    private String title; //标题
    private String pic; //图片
    private String introduction; // 歌单简介
    private String style; //风格
}
