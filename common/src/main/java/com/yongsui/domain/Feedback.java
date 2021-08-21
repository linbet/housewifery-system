package com.yongsui.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 全局统一返回类
 * @Author: tengmingfa
 * @Date: 2021年08月21日
 */
public class Feedback implements Serializable {

    private static final long serialVersionUID = 8937107302451486568L;

    // 请求是否成功
    private Boolean success;

    // 返回码
    private Integer code;

    // 反馈信息
    private String msg;

    // 返回数据
    private Map<String,Object> data;

    public Feedback() {
    }

    public Feedback(Boolean success, Integer code, String msg, Map<String, Object> data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 设计一个方法，使用枚举设置参数
    public void setFeedback(FeedbackEnum feedbackEnum){
        this.success = feedbackEnum.getSuccess();
        this.code = feedbackEnum.getCode();
        this.msg = feedbackEnum.getMsg();
        this.data = null;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    // 重载一个方法设置data
    public void setData(String key, Object data) {
        this.data = new HashMap<>();
        this.data.put(key,data);
    }
}
