package com.antsdouble.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/23
 */
public class Result<T> implements Serializable {
    /**
     * 错误码
     */
    @Getter
    @Setter
    private String code;


    /**
     * 消息内容
     */
    @Getter
    @Setter
    private String msg;

    /**
     * 具体内容
     */
    @Getter
    @Setter
    private T data;


    /**
     * 功能描述 成功时调用
     *
     * @param [data]
     * @return <T>
     * @author lyy
     * @date 2019/7/12
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    /**
     * 功能描述 只有状态时返回结果
     * @author lyy
     * @date 2019/7/12
     * @param [resultEnum]
     * @return <T>
     */
    public static <T> Result<T> build(ResultEnum resultEnum) {
        return new Result<>(resultEnum);
    }

    /**
     * 功能描述 根据code和msg构建返回结果
     * @author lyy
     * @date 2019/7/12
     * @param [code, msg]
     * @return <T>
     */
    public static <T> Result<T> build(String code, String msg) {
        return new Result<>(code, msg);
    }

    /**
     * 功能描述 code ,msg,data构建返回结果
     * @author lyy
     * @date 2019/7/12
     * @param [code, msg, data]
     * @return <T>
     */
    public static <T> Result<T> build(String code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    /**
     * 功能描述 错误的结果
     * @author lyy
     * @date 2019/7/12
     * @param [codeMsg]
     * @return <T>
     */
    public static <T> Result<T> error(String codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 功能描述 错误的结果并附加错误数据
     * @author lyy
     * @date 2019/7/12
     * @param [data]
     * @return <T>
     */
    public static <T> Result<T> error(T data) {
        return new Result<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), data);
    }

    private Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private Result(String msg) {
        this.code = ResultEnum.ERROR.getCode();
        this.msg = msg;
        this.data = null;
    }

    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
