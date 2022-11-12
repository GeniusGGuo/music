package com.springbootmusic.music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * 管理员
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
    private Integer id; //主键
    private String name;  //账号
    private String password; //密码

}
