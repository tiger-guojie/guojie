package com.micro.util;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.Data;
 
/**
 * @author GJ
 * @date 2018/9/07 17:18
 */
@Data
public class JsonResult implements Serializable{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 3710707483907752976L;
	private int code;   //返回码 非0即失败
    private String msg; //消息提示
    private Map<String, Object> data; //返回的数据
 
    public JsonResult(){};
 
    public JsonResult(int code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
 
    public static String success() {
        return success(new HashMap<>(0));
    }
    public static String success(Map<String, Object> data) {
        return JSON.toJSONString(new JsonResult(0, "解析成功", data));
    }
 
    public static String failed() {
        return failed("解析失败");
    }
    public static String failed(String msg) {
        return failed(-1, msg);
    }
    public static String failed(int code, String msg) {
        return JSON.toJSONString(new JsonResult(code, msg, new HashMap<>(0)));
    }
 
}