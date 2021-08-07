package cn.wolfcode.wolf2w.interceptor;

import cn.wolfcode.wolf2w.annotation.RequireLogin;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CheckLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private IRedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {

        if (!(handler instanceof HandlerMethod))
            return true;

        String token = request.getHeader("token");
        UserInfo info = redisService.getUserInfoByToken(token);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.hasMethodAnnotation(RequireLogin.class)) {

            if (info == null)
            {
                response.getWriter().write(JSON.toJSONString(JsonResult.noLogin()));
                return false;
            }
        }

        return true;
    }
}
