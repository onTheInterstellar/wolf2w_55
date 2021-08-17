package cn.wolfcode.wolf2w.redis.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum RedisKeys {

    // 枚举实例

    USER_LONGIN_TOKEN("login_token", 30L),

    // 注册验证码
    REGISTER_VERIFY_CODE("verify_code", 5L),

    // 攻略数据统计, -1表示不设置超时时间
    STRATEGY_STATIS_VO("strategy_statis_vo", -1L);

    // 字段
    // key的前缀
    @Setter                      //枚举的setter要放在这个位置
    private String prefix;
    // key的有效时间, 单位分钟
    @Setter
    private Long time;

    // 重写构造器, 用于构造枚举实例
    RedisKeys(String prefix, Long time) {
        this.prefix = prefix;
        this.time = time;
    }

    // 拼接字符串方法, 使用可变参数以应对key可能传入多个参数
    public String join(String... params) {
        StringBuilder redisKey = new StringBuilder(80);
        redisKey.append(this.prefix);
        for (String param : params) {
            redisKey.append(":").append(param);
        }
        return redisKey.toString();
    }
}
