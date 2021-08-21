package com.yongsui.domain;

public enum FeedbackEnum {

    SUCCESS(true,20000,"成功"),
    FAIL(false,20001,"失败");

    // 请求是否成功
    private Boolean success;

    // 返回码
    private Integer code;

    // 反馈信息
    private String msg;

    FeedbackEnum(Boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
