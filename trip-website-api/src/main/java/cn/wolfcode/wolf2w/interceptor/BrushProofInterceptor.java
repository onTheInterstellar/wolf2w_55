package cn.wolfcode.wolf2w.interceptor;

import cn.wolfcode.wolf2w.redis.utils.RedisKeys;
import cn.wolfcode.wolf2w.util.RequestUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防刷拦截器
 */
public class BrushProofInterceptor implements HandlerInterceptor {
//    @Autowired
//    private ISecurityRedisService securityRedisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return  true;
        }

        //防刷验证
        //   /users/get
        String url = request.getRequestURI().substring(1);
        String ip = RequestUtil.getIPAddress();

        String key = RedisKeys.BRUSH_PROOF.join(url, ip);

  /*      if(!securityRedisService.isAllowBrush(key)){
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(JsonResult.error(500, "请勿频繁访问","谢谢咯")));
            return false;
        }*/

        return true;
    }
}