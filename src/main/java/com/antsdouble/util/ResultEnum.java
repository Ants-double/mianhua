package com.antsdouble.util;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/23
 */
public enum  ResultEnum {
    /**
     * // 成功
     */
    SUCCESS("666666","success"),



    /**
     *  // 失败
     */
    ERROR("000000","error"),



    /**
     *  // 处理异常
     */
    WAIT("111111","in-process");

    /**
     * 功能描述 错误码
     *
     * @author lyy
     * @date 2019/7/12
     * @param * @param null
     * @return
     */
    private String code;
    /**
     * 功能描述 错误信息
     *
     * @author lyy
     * @date 2019/7/12
     * @param * @param null
     * @return
     */
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
