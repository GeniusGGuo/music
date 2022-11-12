package com.springbootmusic.music.controller;


import com.springbootmusic.music.domain.Evaluate;
import com.springbootmusic.music.service.EvaluateService;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Evaluate")
public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;

    /**
     * 添加用户
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult addEvaluate(HttpServletRequest request) {
        String songListId = request.getParameter("songListId").trim();
        String userId = request.getParameter("userId").trim();
        String score = request.getParameter("score").trim();
        Evaluate evaluate = new Evaluate();
        evaluate.setUserId(Integer.parseInt(userId));
        evaluate.setSongListId(Integer.parseInt(songListId));
        evaluate.setScore(Integer.parseInt(score));
        boolean flag = evaluateService.insert(evaluate);
        if (flag) {
            return new ResponseResult(1, "评分成功");
        }
        return new ResponseResult(0, "评分失败");
    }

    /**
     * 查询指定歌单总评价分数
     * */
    @RequestMapping(value = "/findScoreSum",method = RequestMethod.GET)
    public ResponseResult findScoreSum(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        int ScoreNum = evaluateService.selectScoreSum(Integer.parseInt(songListId));
        return new ResponseResult(1,"查询成功",ScoreNum);
    }

    /**
     * 查询指定歌单评分总人数
     * */
    @RequestMapping(value = "/findEvaluateSum",method = RequestMethod.GET)
    public ResponseResult findEvaluateSum(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        int peoples = evaluateService.selectEvaluateSum(Integer.parseInt(songListId));
        return new ResponseResult(1,"查询成功",peoples);
    }

    /**
     * 查询指定歌单的平均评分
     * */
    @RequestMapping(value = "/findEvaluateScore",method = RequestMethod.GET)
    public ResponseResult findEvaluateScore(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        int grade = evaluateService.selectEvaluateScore(Integer.parseInt(songListId));
        return new ResponseResult(1,"查询成功",grade);
    }


}
