package com.springbootmusic.music.Mapper;

import com.springbootmusic.music.domain.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EvaluateMapper {
    /**
     * 增加
     */
    public int insert(Evaluate evaluate);


    /**
     * 查询总评价分
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查询评价总人数
     * */
    public int selectEvaluateSum(Integer songListId);
}
