package com.springbootmusic.music.service;


import com.springbootmusic.music.domain.Evaluate;

/**
 * 评价
 * */
public interface EvaluateService {
    /**
     * 增加
     */
    public boolean insert(Evaluate evaluate);


    /**
     * 查询评价总分
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查询评价总人数
     * */
    public int selectEvaluateSum(Integer songListId);

    /**
     * 查询评价平均分
     * */
    public int selectEvaluateScore(Integer songListId);
}
