package com.study.gulimall.common.exception;

/**
 * @Author: name
 * @Date: 2023-08-03-3:32
 * @Description: 错误码枚举类，错误码为5位数字，前2位代表业务，后3位表示错误码
 */
public enum ErrorCodeEnum {

    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式校验失败");

    private int code;
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
