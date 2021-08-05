package cn.wolfcode.wolf2w.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogicException extends RuntimeException {
    public LogicException(String msg) {
        super(msg);
    }
}
