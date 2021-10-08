package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.redis.utils.RedisKeys;
import cn.wolfcode.wolf2w.redis.vo.VisitorStatisVo;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public UserInfo getUserInfoByToken(String token) {
        //判断token是否为空
        if (!StringUtils.hasText(token))
            return null;
        // 获取key
        String tokenKey = RedisKeys.USER_LONGIN_TOKEN.join(token);

        // 判断key是否过期
        if (redisTemplate.hasKey(tokenKey)) {
            // 获取userInfo
            String userInfoStr = redisTemplate.opsForValue().get(tokenKey);
            redisTemplate.expire(tokenKey, Duration.ofMinutes(RedisKeys.USER_LONGIN_TOKEN.getTime()));
            // 转成对象
            UserInfo userInfo = JSON.parseObject(userInfoStr, UserInfo.class);
            return  userInfo;
        }

        return null;
    }

    @Override
    public String getToken(UserInfo userInfo) {

        //以uuid作为区分
        String tokenElement = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(RedisKeys.USER_LONGIN_TOKEN.join(tokenElement),
                JSON.toJSONString(userInfo), RedisKeys.USER_LONGIN_TOKEN.getTime() * 60L, TimeUnit.SECONDS);
        return tokenElement;
    }


    @Override
    public String getVerify(String phone) {
        String verifyCode = redisTemplate.opsForValue().get(RedisKeys.REGISTER_VERIFY_CODE.join(phone));
        return verifyCode;
    }


    @Override
    public void saveVerify(String phone, String verifyCode) {
        //String verifyKey = Consts.VERIFY_CODE + ":" +phone;
        // 针对key进行优化
        redisTemplate.opsForValue().set(RedisKeys.REGISTER_VERIFY_CODE.join(phone),
                verifyCode, RedisKeys.REGISTER_VERIFY_CODE.getTime() * 60L, TimeUnit.SECONDS);
    }

    @Override
    public void setVisitor(String vToken, Long uId) {

        UserInfo visitor = this.getUserInfoByToken(vToken);
        String userVKey = RedisKeys.USER_VISITORS.join(uId.toString());
        if (visitor.getId() != uId) {
            if (!redisTemplate.hasKey(userVKey)) {
                ArrayList<Long> visitorList = new ArrayList<>();
                visitorList.add(visitor.getId());
                VisitorStatisVo viewnum = new VisitorStatisVo();
                viewnum.setTodayViewnum(viewnum.getTodayViewnum() + 1);
                viewnum.setViewnum(viewnum.getViewnum() + 1);
                redisTemplate.opsForValue().set(RedisKeys.VISITOR_STATIS_VO.join(uId.toString()), JSON.toJSONString(viewnum));
                redisTemplate.opsForValue().set(userVKey, JSON.toJSONString(visitorList));
            } else {
                String listStr = redisTemplate.opsForValue().get(userVKey);
                List<Long> visitorList = JSON.parseArray(listStr, Long.class);
                String viewStr = redisTemplate.opsForValue().get(RedisKeys.VISITOR_STATIS_VO.join(uId.toString()));
                VisitorStatisVo viewnum = JSON.parseObject(viewStr, VisitorStatisVo.class);
                viewnum.setTodayViewnum(viewnum.getTodayViewnum() + 1);
                viewnum.setViewnum(viewnum.getViewnum() + 1);
                redisTemplate.opsForValue().set(RedisKeys.VISITOR_STATIS_VO.join(uId.toString()), JSON.toJSONString(viewnum));
                if (!visitorList.contains(visitor.getId())) {
                    visitorList.add(visitor.getId());
                    redisTemplate.opsForValue().set(userVKey, JSON.toJSONString(visitorList));
                }


            }

        }




    }

    @Override
    public Map<String, Object> getVisitorsByOwnerId(Long id) {

        List<UserInfo> visitors = null;
        String userVKey = RedisKeys.USER_VISITORS.join(id.toString());
        if (!redisTemplate.hasKey(userVKey)) {
            ArrayList<Long> visitorList = new ArrayList<>();
            redisTemplate.opsForValue().set(userVKey, JSON.toJSONString(visitorList));
            visitors = Collections.emptyList();
        } else {
            String listStr = redisTemplate.opsForValue().get(userVKey);
            List<Long> visitorList = JSON.parseArray(listStr, Long.class);
            if ( visitorList.size() != 0) {
                visitors = userInfoService.listByIds(visitorList);
            } else {
                visitors = Collections.emptyList();
            }
        }

        Collections.reverse(visitors);
        String vStatisKey = RedisKeys.VISITOR_STATIS_VO.join(id.toString());
        String vNum = redisTemplate.opsForValue().get(vStatisKey);
        VisitorStatisVo visitorStatisVo = JSON.parseObject(vNum, VisitorStatisVo.class);
        Map<String, Object> result = new HashMap<>();
        result.put("visitors", visitors);
        result.put("totalView", visitorStatisVo.getTodayViewnum());
        result.put("todayView", visitorStatisVo.getViewnum());
        return result;

    }
}
