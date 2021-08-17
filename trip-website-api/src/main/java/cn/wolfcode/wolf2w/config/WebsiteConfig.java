package cn.wolfcode.wolf2w.config;

import cn.wolfcode.wolf2w.interceptor.CheckLoginInterceptor;
import cn.wolfcode.wolf2w.resolver.UserInfoArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebsiteConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor checkLoginInterceptor() {
        return new CheckLoginInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/users/login")
                .excludePathPatterns("/users/register")
                .excludePathPatterns("/users/sendVerifyCode")
                .excludePathPatterns("/users/checkPhone");

    }

    // 创建参数解析器
    @Bean
    public UserInfoArgumentResolver userInfoArgumentResolver() {
        return new UserInfoArgumentResolver();
    }

    //配置参数解析器
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userInfoArgumentResolver());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            @Override
            //重写父类提供的跨域请求处理的接口
            public void addCorsMappings(CorsRegistry registry) {
                //添加映射路径
                registry.addMapping("/**")
                        //放行哪些原始域
                        //.allowedOrigins("*")   // 所有人都可以访问, 这种方法在spring boot 2.2才能使用, 之后版本使用下面一个方法
                        .allowedOriginPatterns("*")
                        //是否发送Cookie信息
                        .allowCredentials(true)
                        //放行哪些原始域(请求方式)
                        .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
                        //放行哪些原始域(头部信息)
                        .allowedHeaders("*")
                        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息） 有些服务器默认限制某些请求头
                        .exposedHeaders("Header1", "Header2");
            }
        };
    }

}
