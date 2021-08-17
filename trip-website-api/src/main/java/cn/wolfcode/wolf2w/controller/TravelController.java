package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.annotation.CustomizeParameterResolver;
import cn.wolfcode.wolf2w.annotation.RequireLogin;
import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.mongo.domain.TravelComment;
import cn.wolfcode.wolf2w.mongo.service.ITravelCommentService;
import cn.wolfcode.wolf2w.query.TravelQuery;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.service.ITravelService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("travels")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ITravelCommentService commentService;



    @GetMapping("/query")
    private Object query(TravelQuery qo) {
        IPage<Travel> travelIPage = travelService.queryPage(qo);
        return JsonResult.success(travelIPage);
    }

    @GetMapping("/detail")
    private Object detail(Long id) {
        return JsonResult.success(travelService.detail(id));
    }

    @RequireLogin
    @PostMapping("/commentAdd")
    private Object commentAdd(TravelComment comment, HttpServletRequest request, @CustomizeParameterResolver UserInfo userInfo) {

        BeanUtils.copyProperties(userInfo, comment);
        comment.setUserId(userInfo.getId());

        commentService.saveComment(comment);

        return JsonResult.success();
    }

/*    //测试参数解析器
    @GetMapping("/test")
    private Object test(UserInfo userInfo) {
        return JsonResult.success(userInfo);
    }
    //测试参数解析器
    @GetMapping("/test1")
    private Object test1(@CustomizeParameterResolver UserInfo userInfo) {
        return JsonResult.success(userInfo);
    }*/

    @GetMapping("/comments")
    private Object comments(Long travelId) {
        return JsonResult.success(commentService.findByTravelId(travelId));
    }



}
