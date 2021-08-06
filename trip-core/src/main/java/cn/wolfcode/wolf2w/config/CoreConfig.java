package cn.wolfcode.wolf2w.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@MapperScan(basePackages = "cn.wolfcode.wolf2w.mapper")
@PropertySource(value = {"classpath:core.properties", "classpath:smsInfo.properties"})
public class CoreConfig {

}
