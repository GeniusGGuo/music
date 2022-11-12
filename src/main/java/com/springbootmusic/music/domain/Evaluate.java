package com.springbootmusic.music.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评价
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluate {
    private Integer id; //主键
    private Integer songListId; // 歌单id
    private Integer userId; //用户id
    private Integer score; //评分
}
