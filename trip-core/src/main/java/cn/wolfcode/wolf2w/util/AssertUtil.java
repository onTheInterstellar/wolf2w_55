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
}
