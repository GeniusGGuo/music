package com.springbootmusic.music.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 歌单中的歌曲
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongListSong {
    private Integer id; //主键
    private Integer songListId;  //歌单id
    private Integer songId; //歌曲id
}
