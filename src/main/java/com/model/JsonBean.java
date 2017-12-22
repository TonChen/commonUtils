package com.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author xiaokeheng
 * ajax调用返回信息封装的JSON对象
 */
public class JsonBean implements Serializable {

    private static final long serialVersionUID = 6433633730670204381L;


    /**
     * 返回码
     */
    private String retCode;
    /**
     * 返回信息
     */
    private String retMsg;
    /**
     * 错误详细信息
     */
    private String detailMsg;
    /**
     * 返回内容
     */
    private Object info;

    public JsonBean() {
        super();
    }

    /**
     * ajax调用，返回信息封装
     *
     * @param retCode   返回码  0 成功 1 警告 2 失败
     * @param retMsg    返回码对应信息
     * @param detailMsg 失败时详细信息
     * @param info      返回对象内容
     */
    public JsonBean(String retCode, String retMsg, String detailMsg, Object info) {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.detailMsg = detailMsg;
        this.info = info;
    }

    public static String initJsonBean(String retCode, String retMsg, String detailMsg, String info) {
        if (info != null) {
            if (info.startsWith("#")) info = "\"" + info.replaceAll("#", "") + "\"";
        }
        if ((info == null) || ((!info.startsWith("{")) && (!info.startsWith("[")))) {
            info = "\"" + info + "\"";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        if ((detailMsg == null) || (!detailMsg.startsWith("{"))) {
            detailMsg = "\"" + detailMsg + "\"";
        }
        sb.append("\"retCode\":\"" + retCode + "\",\"retMsg\":\"" + retMsg + "\",\"detailMsg\":" + detailMsg + ",\"info\":" + info);
        sb.append("}");
        return sb.toString();
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
