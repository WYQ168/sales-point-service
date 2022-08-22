package com.sales.common.core.web.response;


import com.sales.common.core.constant.TraceConstant;
import com.sales.common.core.web.result.Code;
import com.sales.common.core.web.result.ICode;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 *@Title:BaseResponse
 *@Desc: 报文回复基类
 *@Author: wuyingqiang
 *@Date: 2019-3-20 21:25
 */
public class BaseResponse<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2782041967377789945L;

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 报文
     */
    private T data;

    /**
     * 当前时间
     */
    private String timestamp;

    /**
     * 追踪ID
     */
    private String traceId = "";

    public static BaseResponse fail() {
        return fail(Code.ERR_OPTIONS_FAILED);
    }

    public static BaseResponse fail(Integer code, String Msg) {
        return build(code, Msg);
    }

    public static BaseResponse fail(ICode result) {
        return fail(result.getCode(), result.getMsg());
    }

    public static <E> BaseResponse<E> fail(ICode result, E data) {
        return fail(result).setData(data);
    }

    public static <E> BaseResponse<E> fail(E data) {
        return fail(Code.ERR_OPTIONS_FAILED, data);
    }

    public static BaseResponse success() {
        return success(Code.SUCCESS);
    }

    public static BaseResponse success(Integer code, String Msg) {
        return build(code, Msg);
    }

    public static BaseResponse success(ICode result) {
        return success(result.getCode(), result.getMsg());
    }

    public static <E> BaseResponse<E> success(ICode result, E data) {
        return success(result).setData(data);
    }

    public static <E> BaseResponse<E> success(E data) {
        return success(Code.SUCCESS, data);
    }

    public String getTimestamp() {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentTime = formatter.format(new Date());
//        this.timestamp = currentTime;
        this.timestamp=""+(int)(System.currentTimeMillis()/1000);
        return timestamp;
    }

    public Integer getCode() {

        if (this.code==null) {
            return Code.SUCCESS.getCode();
        }
        return this.code;
    }

    public BaseResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {

        if (StringUtils.isEmpty(this.msg)) {
            return "";
        }
        return this.msg;
    }

    public String getTraceId() {
        traceId = ThreadContext.get(TraceConstant.LOG_B3_TRACEID);
        return traceId;
    }

    public BaseResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    private BaseResponse() {
    }

    private BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private BaseResponse(Integer code, String msg, T data) {
        this(code, msg);
        if (data != null) {
            this.data = data;
        }
    }

    private static <E> BaseResponse<E> build(Integer code, String Msg) {

        return new BaseResponse(code, Msg, null);
    }

    private static <E> BaseResponse<E> build(Integer code, String Msg, E data) {

        return new BaseResponse(code, Msg, data);
    }
}
