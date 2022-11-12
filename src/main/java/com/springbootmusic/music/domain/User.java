package com.springbootmusic.music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id; //主键
    private String username; //账号
    private String password; //密码
    private Byte sex;   //性别
    private String phoneNum; //电话号码
    private String email; //电子邮箱
    private Date birth; //生日
    private String introduction; //签名
    private String location;    //地区
    private String avator;      //头像
    private Date createTime;    //创建时间
    private Date updateTime;     //更新时间
}
