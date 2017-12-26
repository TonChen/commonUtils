/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2016
 */

package com.genaral.jms.bean;

import com.genaral.base.BaseEntity;
import com.genaral.date.DateConvertUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class MqLog extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "MqLog";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_BUS_CODE = "busCode";
    public static final String ALIAS_BUS_CHANNEL = "busChannel";
    public static final String ALIAS_REQ_PARAM = "reqParam";
    public static final String ALIAS_RES_MSG = "resMsg";
    public static final String ALIAS_MQ_ID = "mqId";
    public static final String ALIAS_MQ_KEY = "mqKey";
    public static final String ALIAS_MQ_BODY = "mqBody";
    public static final String ALIAS_MQ_TOPIC = "mqTopic";
    public static final String ALIAS_MQ_TAG = "mqTag";
    public static final String ALIAS_SEND_NUM = "sendNum";
    public static final String ALIAS_STATUS = "状态，0：成功，1：待处理，2：失败";
    public static final String ALIAS_CREATE_TIME = "创建时间";
    public static final String ALIAS_OP_TIME = "操作时间";

    //date formats
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
    public static final String FORMAT_OP_TIME = DATE_FORMAT;

    //可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
    //columns START
    /**
     * id       db_column: id
     */

    private Long id;
    /**
     * busCode       db_column: bus_code
     */
    private String busCode;
    /**
     * busChannel       db_column: bus_channel
     */
    private String busChannel;
    /**
     * reqParam       db_column: req_param
     */
    private String reqParam;
    /**
     * resMsg       db_column: res_msg
     */
    private String resMsg;
    /**
     * mqId       db_column: mq_id
     */
    private String mqId;
    /**
     * mqKey       db_column: mq_key
     */
    private String mqKey;
    /**
     * mqBody       db_column: mq_body
     */
    private String mqBody;
    /**
     * mqTopic       db_column: mq_topic
     */
    private String mqTopic;
    /**
     * mqTag       db_column: mq_tag
     */
    private String mqTag;
    /**
     * sendNum       db_column: send_num
     */

    private Integer sendNum;
    /**
     * 状态，0：成功，1：待处理，2：失败       db_column: status
     */
    private Integer status;
    /**
     * 创建时间       db_column: create_time
     */

    private Date createTime;
    /**
     * 操作时间       db_column: op_time
     */

    private Date opTime;
    //columns END

    public MqLog() {
    }

    public MqLog(
            Long id
    ) {
        this.id = id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setBusCode(String value) {
        this.busCode = value;
    }

    public String getBusCode() {
        return this.busCode;
    }

    public void setBusChannel(String value) {
        this.busChannel = value;
    }

    public String getBusChannel() {
        return this.busChannel;
    }

    public void setReqParam(String value) {
        this.reqParam = value;
    }

    public String getReqParam() {
        return this.reqParam;
    }

    public void setResMsg(String value) {
        this.resMsg = value;
    }

    public String getResMsg() {
        return this.resMsg;
    }

    public void setMqId(String value) {
        this.mqId = value;
    }

    public String getMqId() {
        return this.mqId;
    }

    public void setMqKey(String value) {
        this.mqKey = value;
    }

    public String getMqKey() {
        return this.mqKey;
    }

    public void setMqBody(String value) {
        this.mqBody = value;
    }

    public String getMqBody() {
        return this.mqBody;
    }

    public void setMqTopic(String value) {
        this.mqTopic = value;
    }

    public String getMqTopic() {
        return this.mqTopic;
    }

    public void setMqTag(String value) {
        this.mqTag = value;
    }

    public String getMqTag() {
        return this.mqTag;
    }

    public void setSendNum(Integer value) {
        this.sendNum = value;
    }

    public Integer getSendNum() {
        return this.sendNum;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getCreateTimeString() {
        return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
    }

    public void setCreateTimeString(String value) {
        setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME, Date.class));
    }

    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getOpTimeString() {
        return DateConvertUtils.format(getOpTime(), FORMAT_OP_TIME);
    }

    public void setOpTimeString(String value) {
        setOpTime(DateConvertUtils.parse(value, FORMAT_OP_TIME, Date.class));
    }

    public void setOpTime(Date value) {
        this.opTime = value;
    }

    public Date getOpTime() {
        return this.opTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("BusCode", getBusCode())
                .append("BusChannel", getBusChannel())
                .append("ReqParam", getReqParam())
                .append("ResMsg", getResMsg())
                .append("MqId", getMqId())
                .append("MqKey", getMqKey())
                .append("MqBody", getMqBody())
                .append("MqTopic", getMqTopic())
                .append("MqTag", getMqTag())
                .append("SendNum", getSendNum())
                .append("Status", getStatus())
                .append("CreateTime", getCreateTime())
                .append("OpTime", getOpTime())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MqLog == false) return false;
        if (this == obj) return true;
        MqLog other = (MqLog) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

