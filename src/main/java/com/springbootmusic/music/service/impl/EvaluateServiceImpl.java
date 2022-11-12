package com.springbootmusic.music.service.impl;


import com.springbootmusic.music.Mapper.EvaluateMapper;
import com.springbootmusic.music.domain.Evaluate;
import com.springbootmusic.music.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateServiceImpl implements EvaluateService {
    @Autowired
    private EvaluateMapper evaluateMapper;
    /**
     * 增加
     *
     * @param evaluate
     */
    @Override
    public boolean insert(Evaluate evaluate) {
        return evaluateMapper.insert(evaluate)>0;
    }

    /**
     * 查询评价总分
     *
     * @param songListId
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return evaluateMapper.selectScoreSum(songListId);
    }

    /**
     * 查询评价总人数
     *
     * @param songListId
     */
    @Override
    public int selectEvaluateSum(Integer songListId) {
        return evaluateMapper.selectEvaluateSum(songListId);
    }

    /**
     * 查询评价平均分
     *
     * @param songListId
     */
    @Override
    public int selectEvaluateScore(Integer songListId) {
        int peoples = evaluateMapper.selectEvaluateSum(songListId);
        if (peoples==0){
            return 5;
        }
        int num = evaluateMapper.selectScoreSum(songListId);
        return num/peoples;
    }
}
