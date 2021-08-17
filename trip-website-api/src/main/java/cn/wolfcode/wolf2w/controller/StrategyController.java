package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.annotation.CustomizeParameterResolver;
import cn.wolfcode.wolf2w.annotation.RequireLogin;
import cn.wolfcode.wolf2w.domain.*;
import cn.wolfcode.wolf2w.mapper.StrategyContentMapper;
import cn.wolfcode.wolf2w.mongo.domain.StrategyComment;
import cn.wolfcode.wolf2w.mongo.query.StrategyCommentQuery;
import cn.wolfcode.wolf2w.mongo.service.IStrategyCommentService;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisService;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVo;
import cn.wolfcode.wolf2w.service.*;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* 用户控制层
*/
@RestController
@RequestMapping("strategies")
public class StrategyController {

    @Autowired
    private StrategyContentMapper strategyContentMapper;

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IStrategyThemeService themeService;

    @Autowired
    private IStrategyRankService strategyRankService;

    @Autowired
    private IStrategyConditionService conditionService;

    @Autowired
    private IStrategyRecommendService recommendService;

    @Autowired
    private IStrategyCommentService commentService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private IStrategyStatisService statisService;

    @GetMapping("/content")
    private Object content(Long id) {

        StrategyContent strategyContent = strategyContentMapper.selectById(id);
        return JsonResult.success(strategyContent);

    }

    @GetMapping("/detail")
    private Object detail(Long id) {

        Strategy strategy = strategyService.getById(id);
        //在攻略详情刷新的时候, 将redis数据更新
        statisService.updateVo(id);
        strategy.setContent(strategyContentMapper.selectById(id));
        return JsonResult.success(strategy);

    }

    @GetMapping("/themes")
    private Object themes(Long destId) {

        List<StrategyTheme> list = themeService.list();
        return JsonResult.success(list);
    }

    @GetMapping("/page")
    private Object page(StrategyQuery qo) {

        IPage<Strategy> page = strategyService.queryPage(qo);

        return JsonResult.success(page);
    }

    @GetMapping("/query")
    private Object query(StrategyQuery qo) {

        IPage<Strategy> page = strategyService.queryPage(qo);

        return JsonResult.success(page);
    }

    @GetMapping("/rank")
    private Object rank(int type) {

        List<StrategyRank> ranks = strategyRankService.queryRank(type);

        return JsonResult.success(ranks);
    }

    @GetMapping("/condition")
    private Object condition(int type) {

        List<StrategyCondition> conditions = conditionService.queryStrategyCondition(type);

        return JsonResult.success(conditions);
    }

    @GetMapping("/themeCds")
    private Object themeCds() {

        List<StrategyRecommend> recommends = recommendService.queryStrategyRecommend();

        return JsonResult.success(recommends);
    }

    @RequireLogin
    @PostMapping("/commentAdd")
    private Object commentAdd(StrategyComment comment, HttpServletRequest request, @CustomizeParameterResolver UserInfo userInfo) {

        BeanUtils.copyProperties(userInfo, comment);
        comment.setUserId(userInfo.getId());
        commentService.saveComment(comment);

        return JsonResult.success();
    }

    @GetMapping("/comments")
    private Object comments(StrategyCommentQuery qo) {

        Page<StrategyComment> comments = commentService.queryPage(qo);
        return JsonResult.success(comments);

    }

    @GetMapping("/statisVo")
    private Object statisVo(Long sid) {
        StrategyStatisVo statisVo = statisService.getStatisVo(sid);
        return JsonResult.success(statisVo);

    }


}
