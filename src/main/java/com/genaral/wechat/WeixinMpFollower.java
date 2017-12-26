/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2015
 */

package com.genaral.wechat;

import com.genaral.base.BaseEntity;
import com.genaral.date.DateConvertUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.HashSet;
import java.util.Set;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class WeixinMpFollower extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "微信关注者";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_WEIXIN_MP_ID = "weixinMpId";
    public static final String ALIAS_OPEN_UID = "微信公开ID";
    public static final String ALIAS_OP_TIME = "操作时间";
    public static final String ALIAS_IS_DEL = "isDel";
    public static final String ALIAS_REMARK = "备注";
    public static final String ALIAS_SYSTEM_TYPE = "系统类型";
    public static final String ALIAS_LAST_CONTACT_TIME = "lastContactTime";
    public static final String ALIAS_NICKNAME = "昵称";
    public static final String ALIAS_SEX = "性别";
    public static final String ALIAS_LANGUAGE = "language";
    public static final String ALIAS_CITY = "城市";
    public static final String ALIAS_PROVINCE = "省份";
    public static final String ALIAS_COUNTRY = "country";
    public static final String ALIAS_HEADIMGURL = "头像";
    public static final String ALIAS_SUBSCRIBE_TIME = "subscribeTime";
    public static final String ALIAS_UNIONID = "unionid";
    public static final String ALIAS_NAME = "名称";
    public static final String ALIAS_GROUPID = "groupid";
    public static final String ALIAS_ACCESS_TOKEN = "accessToken";
    public static final String ALIAS_EXPIRES_IN = "expiresIn";
    public static final String ALIAS_SCOPE = "scope";
    public static final String ALIAS_REFRESH_TOKEN = "refreshToken";
    public static final String ALIAS_ACCESS_TOKEN_BEGIN_TIME = "accessTokenBeginTime";
    public static final String ALIAS_ACCESS_TOKEN_END_TIME = "accessTokenEndTime";
    public static final String ALIAS_JSAPI_TICKET = "jsapiTicket";
    public static final String ALIAS_JSAPI_TICKET_EXPIRES_IN = "jsapiTicketExpiresIn";
    public static final String ALIAS_JSAPI_TICKET_BEGIN_TIME = "jsapiTicketBeginTime";
    public static final String ALIAS_JSAPI_TICKET_END_TIME = "jsapiTicketEndTime";

    //date formats
    public static final String FORMAT_OP_TIME = DATE_FORMAT;
    public static final String FORMAT_LAST_CONTACT_TIME = DATE_FORMAT;
    public static final String FORMAT_SUBSCRIBE_TIME = DATE_FORMAT;
    public static final String FORMAT_ACCESS_TOKEN_BEGIN_TIME = DATE_FORMAT;
    public static final String FORMAT_ACCESS_TOKEN_END_TIME = DATE_FORMAT;
    public static final String FORMAT_JSAPI_TICKET_BEGIN_TIME = DATE_FORMAT;
    public static final String FORMAT_JSAPI_TICKET_END_TIME = DATE_FORMAT;

    //可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
    //columns START
    /**
     * id       db_column: id
     */
    private String id;
    /**
     * weixinMpId       db_column: weixin_mp_id
     */
    private String weixinMpId;
    /**
     * openUid       db_column: open_uid
     */
    private String openUid;
    /**
     * opTime       db_column: op_time
     */

    private java.util.Date opTime;
    /**
     * isDel       db_column: is_del
     */
    private Boolean isDel;
    /**
     * remark       db_column: remark
     */
    private String remark;
    /**
     * systemType       db_column: system_type
     */

    private Integer systemType;
    /**
     * lastContactTime       db_column: last_contact_time
     */

    private java.util.Date lastContactTime;
    /**
     * nickname       db_column: nickname
     */
    private String nickname;
    /**
     * sex       db_column: sex
     */

    private Integer sex;
    /**
     * language       db_column: language
     */
    private String language;
    /**
     * city       db_column: city
     */
    private String city;
    /**
     * province       db_column: province
     */
    private String province;
    /**
     * country       db_column: country
     */
    private String country;
    /**
     * headimgurl       db_column: headimgurl
     */
    private String headimgurl;
    /**
     * subscribeTime       db_column: subscribe_time
     */

    private java.util.Date subscribeTime;
    /**
     * unionid       db_column: unionid
     */
    private String unionid;
    /**
     * name       db_column: name
     */
    private String name;
    /**
     * groupid       db_column: groupid
     */

    private Integer groupid;
    /**
     * accessToken       db_column: access_token
     */
    private String accessToken;
    /**
     * expiresIn       db_column: expires_in
     */

    private Long expiresIn;
    /**
     * scope       db_column: scope
     */
    private String scope;
    /**
     * refreshToken       db_column: refresh_token
     */
    private String refreshToken;
    /**
     * accessTokenBeginTime       db_column: access_token_begin_time
     */

    private java.util.Date accessTokenBeginTime;
    /**
     * accessTokenEndTime       db_column: access_token_end_time
     */

    private java.util.Date accessTokenEndTime;
    /**
     * jsapiTicket       db_column: jsapi_ticket
     */
    private String jsapiTicket;
    /**
     * jsapiTicketExpiresIn       db_column: jsapi_ticket_expires_in
     */

    private Long jsapiTicketExpiresIn;
    /**
     * jsapiTicketBeginTime       db_column: jsapi_ticket_begin_time
     */

    private java.util.Date jsapiTicketBeginTime;
    /**
     * jsapiTicketEndTime       db_column: jsapi_ticket_end_time
     */

    private java.util.Date jsapiTicketEndTime;
    //columns END

    public WeixinMpFollower() {
    }

    public WeixinMpFollower(
            String id
    ) {
        this.id = id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getId() {
        return this.id;
    }

    public void setWeixinMpId(String value) {
        this.weixinMpId = value;
    }

    public String getWeixinMpId() {
        return this.weixinMpId;
    }

    public void setOpenUid(String value) {
        this.openUid = value;
    }

    public String getOpenUid() {
        return this.openUid;
    }

    public String getOpTimeString() {
        return DateConvertUtils.format(getOpTime(), FORMAT_OP_TIME);
    }

    public void setOpTimeString(String value) {
        setOpTime(DateConvertUtils.parse(value, FORMAT_OP_TIME, java.util.Date.class));
    }

    public void setOpTime(java.util.Date value) {
        this.opTime = value;
    }

    public java.util.Date getOpTime() {
        return this.opTime;
    }

    public void setIsDel(Boolean value) {
        this.isDel = value;
    }

    public Boolean getIsDel() {
        return this.isDel;
    }

    public void setRemark(String value) {
        this.remark = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setSystemType(Integer value) {
        this.systemType = value;
    }

    public Integer getSystemType() {
        return this.systemType;
    }

    public String getLastContactTimeString() {
        return DateConvertUtils.format(getLastContactTime(), FORMAT_LAST_CONTACT_TIME);
    }

    public void setLastContactTimeString(String value) {
        setLastContactTime(DateConvertUtils.parse(value, FORMAT_LAST_CONTACT_TIME, java.util.Date.class));
    }

    public void setLastContactTime(java.util.Date value) {
        this.lastContactTime = value;
    }

    public java.util.Date getLastContactTime() {
        return this.lastContactTime;
    }

    public void setNickname(String value) {
        this.nickname = value;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setSex(Integer value) {
        this.sex = value;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setLanguage(String value) {
        this.language = value;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getCity() {
        return this.city;
    }

    public void setProvince(String value) {
        this.province = value;
    }

    public String getProvince() {
        return this.province;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public String getCountry() {
        return this.country;
    }

    public void setHeadimgurl(String value) {
        this.headimgurl = value;
    }

    public String getHeadimgurl() {
        return this.headimgurl;
    }

    public String getSubscribeTimeString() {
        return DateConvertUtils.format(getSubscribeTime(), FORMAT_SUBSCRIBE_TIME);
    }

    public void setSubscribeTimeString(String value) {
        setSubscribeTime(DateConvertUtils.parse(value, FORMAT_SUBSCRIBE_TIME, java.util.Date.class));
    }

    public void setSubscribeTime(java.util.Date value) {
        this.subscribeTime = value;
    }

    public java.util.Date getSubscribeTime() {
        return this.subscribeTime;
    }

    public void setUnionid(String value) {
        this.unionid = value;
    }

    public String getUnionid() {
        return this.unionid;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setGroupid(Integer value) {
        this.groupid = value;
    }

    public Integer getGroupid() {
        return this.groupid;
    }

    public void setAccessToken(String value) {
        this.accessToken = value;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setExpiresIn(Long value) {
        this.expiresIn = value;
    }

    public Long getExpiresIn() {
        return this.expiresIn;
    }

    public void setScope(String value) {
        this.scope = value;
    }

    public String getScope() {
        return this.scope;
    }

    public void setRefreshToken(String value) {
        this.refreshToken = value;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getAccessTokenBeginTimeString() {
        return DateConvertUtils.format(getAccessTokenBeginTime(), FORMAT_ACCESS_TOKEN_BEGIN_TIME);
    }

    public void setAccessTokenBeginTimeString(String value) {
        setAccessTokenBeginTime(DateConvertUtils.parse(value, FORMAT_ACCESS_TOKEN_BEGIN_TIME, java.util.Date.class));
    }

    public void setAccessTokenBeginTime(java.util.Date value) {
        this.accessTokenBeginTime = value;
    }

    public java.util.Date getAccessTokenBeginTime() {
        return this.accessTokenBeginTime;
    }

    public String getAccessTokenEndTimeString() {
        return DateConvertUtils.format(getAccessTokenEndTime(), FORMAT_ACCESS_TOKEN_END_TIME);
    }

    public void setAccessTokenEndTimeString(String value) {
        setAccessTokenEndTime(DateConvertUtils.parse(value, FORMAT_ACCESS_TOKEN_END_TIME, java.util.Date.class));
    }

    public void setAccessTokenEndTime(java.util.Date value) {
        this.accessTokenEndTime = value;
    }

    public java.util.Date getAccessTokenEndTime() {
        return this.accessTokenEndTime;
    }

    public void setJsapiTicket(String value) {
        this.jsapiTicket = value;
    }

    public String getJsapiTicket() {
        return this.jsapiTicket;
    }

    public void setJsapiTicketExpiresIn(Long value) {
        this.jsapiTicketExpiresIn = value;
    }

    public Long getJsapiTicketExpiresIn() {
        return this.jsapiTicketExpiresIn;
    }

    public String getJsapiTicketBeginTimeString() {
        return DateConvertUtils.format(getJsapiTicketBeginTime(), FORMAT_JSAPI_TICKET_BEGIN_TIME);
    }

    public void setJsapiTicketBeginTimeString(String value) {
        setJsapiTicketBeginTime(DateConvertUtils.parse(value, FORMAT_JSAPI_TICKET_BEGIN_TIME, java.util.Date.class));
    }

    public void setJsapiTicketBeginTime(java.util.Date value) {
        this.jsapiTicketBeginTime = value;
    }

    public java.util.Date getJsapiTicketBeginTime() {
        return this.jsapiTicketBeginTime;
    }

    public String getJsapiTicketEndTimeString() {
        return DateConvertUtils.format(getJsapiTicketEndTime(), FORMAT_JSAPI_TICKET_END_TIME);
    }

    public void setJsapiTicketEndTimeString(String value) {
        setJsapiTicketEndTime(DateConvertUtils.parse(value, FORMAT_JSAPI_TICKET_END_TIME, java.util.Date.class));
    }

    public void setJsapiTicketEndTime(java.util.Date value) {
        this.jsapiTicketEndTime = value;
    }

    public java.util.Date getJsapiTicketEndTime() {
        return this.jsapiTicketEndTime;
    }

    private Set vipConsumers = new HashSet(0);



    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("WeixinMpId", getWeixinMpId())
                .append("OpenUid", getOpenUid())
                .append("OpTime", getOpTime())
                .append("IsDel", getIsDel())
                .append("Remark", getRemark())
                .append("SystemType", getSystemType())
                .append("LastContactTime", getLastContactTime())
                .append("Nickname", getNickname())
                .append("Sex", getSex())
                .append("Language", getLanguage())
                .append("City", getCity())
                .append("Province", getProvince())
                .append("Country", getCountry())
                .append("Headimgurl", getHeadimgurl())
                .append("SubscribeTime", getSubscribeTime())
                .append("Unionid", getUnionid())
                .append("Name", getName())
                .append("Groupid", getGroupid())
                .append("AccessToken", getAccessToken())
                .append("ExpiresIn", getExpiresIn())
                .append("Scope", getScope())
                .append("RefreshToken", getRefreshToken())
                .append("AccessTokenBeginTime", getAccessTokenBeginTime())
                .append("AccessTokenEndTime", getAccessTokenEndTime())
                .append("JsapiTicket", getJsapiTicket())
                .append("JsapiTicketExpiresIn", getJsapiTicketExpiresIn())
                .append("JsapiTicketBeginTime", getJsapiTicketBeginTime())
                .append("JsapiTicketEndTime", getJsapiTicketEndTime())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof WeixinMpFollower == false) return false;
        if (this == obj) return true;
        WeixinMpFollower other = (WeixinMpFollower) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

