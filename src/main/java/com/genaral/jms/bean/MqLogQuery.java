/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2016
 */

package com.genaral.jms.bean;

import com.genaral.base.BaseQuery;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class MqLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;


    /**
     * id
     */
    private Long id;
    /**
     * busCode
     */
    private String busCode;
    /**
     * busChannel
     */
    private String busChannel;
    /**
     * reqParam
     */
    private String reqParam;
    /**
     * resMsg
     */
    private String resMsg;
    /**
     * mqId
     */
    private String mqId;
    /**
     * mqKey
     */
    private String mqKey;
    /**
     * mqBody
     */
    private String mqBody;
    /**
     * mqTopic
     */
    private String mqTopic;
    /**
     * mqTag
     */
    private String mqTag;
    /**
     * sendNum
     */
    private Integer sendNum;
    /**
     * 状态，0：成功，1：待处理，2：失败
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTimeBegin;
    private Date createTimeEnd;
    /**
     * 操作时间
     */
    private Date opTimeBegin;
    private Date opTimeEnd;

    public Long getId() {
        return this.id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getBusCode() {
        return this.busCode;
    }

    public void setBusCode(String value) {
        this.busCode = value;
    }

    public String getBusChannel() {
        return this.busChannel;
    }

    public void setBusChannel(String value) {
        this.busChannel = value;
    }

    public String getReqParam() {
        return this.reqParam;
    }

    public void setReqParam(String value) {
        this.reqParam = value;
    }

    public String getResMsg() {
        return this.resMsg;
    }

    public void setResMsg(String value) {
        this.resMsg = value;
    }

    public String getMqId() {
        return this.mqId;
    }

    public void setMqId(String value) {
        this.mqId = value;
    }

    public String getMqKey() {
        return this.mqKey;
    }

    public void setMqKey(String value) {
        this.mqKey = value;
    }

    public String getMqBody() {
        return this.mqBody;
    }

    public void setMqBody(String value) {
        this.mqBody = value;
    }

    public String getMqTopic() {
        return this.mqTopic;
    }

    public void setMqTopic(String value) {
        this.mqTopic = value;
    }

    public String getMqTag() {
        return this.mqTag;
    }

    public void setMqTag(String value) {
        this.mqTag = value;
    }

    public Integer getSendNum() {
        return this.sendNum;
    }

    public void setSendNum(Integer value) {
        this.sendNum = value;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public Date getCreateTimeBegin() {
        return this.createTimeBegin;
    }

    public void setCreateTimeBegin(Date value) {
        this.createTimeBegin = value;
    }

    public Date getCreateTimeEnd() {
        return this.createTimeEnd;
    }

    public void setCreateTimeEnd(Date value) {
        this.createTimeEnd = value;
    }

    public Date getOpTimeBegin() {
        return this.opTimeBegin;
    }

    public void setOpTimeBegin(Date value) {
        this.opTimeBegin = value;
    }

    public Date getOpTimeEnd() {
        return this.opTimeEnd;
    }

    public void setOpTimeEnd(Date value) {
        this.opTimeEnd = value;
    }


    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

