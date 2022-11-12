package com.springbootmusic.music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 歌手
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Singer {
    private Integer id; //主键
    private String name;  //名字
    private Byte sex; //性别
    private String pic; //头像
    private Date birth; //生日
    private String location; //地址
    private String introduction; //简介

}
