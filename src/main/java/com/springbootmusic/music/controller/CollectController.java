package com.springbootmusic.music.controller;


import com.springbootmusic.music.domain.Collect;
import com.springbootmusic.music.service.CollectService;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;


    /**
     * 添加收藏
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult addSinger(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        if (songId==null||songId.equals("")){
            return new ResponseResult(0,"收藏的歌曲为空");
        }
        if(collectService.existSongIdAndUserId(Integer.parseInt(userId),Integer.parseInt(songId))){
            return new ResponseResult(2,"歌曲已经收藏");
        }
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(songId));
        boolean flag = collectService.insert(collect);
        if (flag) {
            return new ResponseResult(1, "添加成功");
        }
        return new ResponseResult(0, "添加失败");
    }


    /**
     * 删除收藏
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseResult deleteSinger(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String songId = request.getParameter("songId");
        boolean flag = collectService.delete(Integer.parseInt(songId),Integer.parseInt(userId));
        if (flag) {
            return new ResponseResult(1, "删除成功");
        }
        return new ResponseResult(0, "删除失败");
    }

    /**
     * 查询所有收藏
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseResult allCollect() {
        List<Collect> collects = collectService.allCollect();
        if (collects.size()!=0) {
            return new ResponseResult(1, "查询成功", collects);
        } else if (collects.size()==0) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 根据用户id查询用户的收藏
     */
    @RequestMapping(value = "/findCollectOfUserId",method = RequestMethod.GET)
    public ResponseResult findCollectOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");
        List<Collect> collectOfUserId = collectService.findCollectOfUserId(Integer.parseInt(userId));
        if (collectOfUserId.size()!=0) {
            return new ResponseResult(1, "查询成功", collectOfUserId);
        } else if (collectOfUserId.size()==0) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }


}
