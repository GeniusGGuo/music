package com.springbootmusic.music.controller;


import com.springbootmusic.music.domain.Comment;
import com.springbootmusic.music.service.CommentService;
import com.springbootmusic.music.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult addComment(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        String songId = request.getParameter("songId");
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String content = request.getParameter("content").trim();
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if (new Byte(type)==0){
            comment.setSongId(Integer.parseInt(songId));
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if (flag) {
            return new ResponseResult(1, "评论成功");
        }
        return new ResponseResult(0, "评论失败");
    }

    /**
     * 修改评论
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult updateComment(HttpServletRequest request) {
        String id = request.getParameter("id");
        String songListId = request.getParameter("songListId");
        String songId = request.getParameter("songId");
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String content = request.getParameter("content").trim();
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(songId!=null&&songId.equals("")){
            songId=null;
        }else{
            comment.setSongId(Integer.parseInt(songId));
        }
        if(songListId!=null&&songListId.equals("")){
            songListId=null;
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.update(comment);
        if (flag) {
            return new ResponseResult(1, "修改成功");
        }
        return new ResponseResult(0, "修改失败");
    }

    /**
     * 删除评论
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseResult deleteSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        boolean flag = commentService.delete(Integer.parseInt(id));
        if (flag) {
            return new ResponseResult(1, "删除成功");
        }
        return new ResponseResult(0, "删除失败");
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
    public ResponseResult selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        Comment comment = commentService.selectByPrimaryKey(Integer.parseInt(id));
        if (comment != null) {
            return new ResponseResult(1, "查询成功", comment);
        } else if (comment == null) {
            return new ResponseResult(1, "查询失败，数据库里没有" + id + "的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 查询所有评论
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseResult allSinger() {
        List<Comment> contentAll = commentService.findContentAll();
        if (contentAll.size()!=0) {
            return new ResponseResult(1, "查询成功", contentAll);
        } else if (contentAll.size()==0) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @RequestMapping(value = "/findSongCommentAll", method = RequestMethod.GET)
    public ResponseResult findSongCommentAll(HttpServletRequest request) {
        String songId = request.getParameter("songId");
        List<Comment> songCommentAll = commentService.findSongCommentAll(Integer.parseInt(songId));
        if (songCommentAll.size()!=0) {
            return new ResponseResult(1, "查询成功", songCommentAll);
        } else if (songCommentAll.size()==0) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @RequestMapping(value = "/findSongListCommentAll", method = RequestMethod.GET)
    public ResponseResult findSongListCommentAll(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        List<Comment> songListCommentAll = commentService.findSongListCommentAll(Integer.parseInt(songListId));
        if (songListCommentAll.size()!=0) {
            return new ResponseResult(1, "查询成功", songListCommentAll);
        } else if (songListCommentAll.size()==0) {
            return new ResponseResult(1, "查询失败，数据库里没有的数据");
        }
        return new ResponseResult(0, "查询失败");
    }

    /**
     * 给某个歌单或歌曲点赞
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseResult like(HttpServletRequest request) {
        String id = request.getParameter("id");
        String up = request.getParameter("up").trim();
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        boolean flag = commentService.update(comment);
        if (flag) {
            return new ResponseResult(1, "点赞成功");
        }
        return new ResponseResult(0, "点赞失败");
    }

}
