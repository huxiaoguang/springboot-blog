package main.blog.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    public static final int DEFAULT_SUCCESS_CODE = 200;
    public static final int DEFAULT_WARN_CODE = 400;
    public static final int DEFAULT_FAIL_CODE = 500;

    protected T data;
    protected int code;
    protected String msg;

    public static <T> Result<T> success(T data) {
        return success(data, DEFAULT_SUCCESS_CODE, "操作成功");
    }

    public static <T> Result<T> success() {
        return success("操作成功");
    }

    public static <T> Result<T> success(String msg) {
        return success(null, DEFAULT_SUCCESS_CODE, msg);
    }

    public static <T> Result<T> success(T data, int code, String msg) {
        return new Result<>(data, code, msg);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(data, DEFAULT_SUCCESS_CODE, msg);
    }

    public static <T> Result<T> failed(T data, int code, String msg) {
        return new Result<>(data, code, msg);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return failed(data, DEFAULT_FAIL_CODE, msg);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return failed(null, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return failed(null, DEFAULT_FAIL_CODE, msg);
    }

    public static <T> Result<T> failed() {
        return failed(null, DEFAULT_WARN_CODE, "操作失败");
    }
}
