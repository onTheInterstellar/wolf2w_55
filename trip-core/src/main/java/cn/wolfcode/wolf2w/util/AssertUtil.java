package cn.wolfcode.wolf2w.util;

import cn.wolfcode.wolf2w.exception.LogicException;
import org.springframework.util.StringUtils;

public abstract class AssertUtil {

    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new LogicException(message);
        }
    }

    public static void checkPhone(Object checkPhone, String message) {

        if (checkPhone != null) {
            throw new LogicException(message);
        }
    }

    public static void checkPhoneFormat(String phone, String message) {

        if (!phone.matches("^1[3456789]\\d{9}$")) {
            throw new LogicException(message);
        }
    }

    public static void comparedPhone(String password, String rpassword, String message) {

        if ( password == null || rpassword == null ) {
            throw new LogicException("密码不能为空");
        }
        if (!password.equals(rpassword)) {
            throw new LogicException(message);
        }

    }
}
