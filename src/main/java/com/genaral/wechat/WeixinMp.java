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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class WeixinMp extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "WeixinMp";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_REG_COMPANY_ID = "regCompanyId";
    public static final String ALIAS_ACCOUNT_TYPE_ID = "accountTypeId";
    public static final String ALIAS_CODE = "code";
    public static final String ALIAS_WEIXIN_NO = "weixinNo";
    public static final String ALIAS_APPID = "appid";
    public static final String ALIAS_SECRET = "secret";
    public static final String ALIAS_URL = "url";
    public static final String ALIAS_TOKEN = "token";
    public static final String ALIAS_OP_TIME = "opTime";
    public static final String ALIAS_REMARK = "remark";
    public static final String ALIAS_ACCESS_TOKEN_BEGIN_TIME = "accessTokenBeginTime";
    public static final String ALIAS_ACCESS_TOKEN_END_TIME = "accessTokenEndTime";
    public static final String ALIAS_ACCESS_TOKEN = "accessToken";
    public static final String ALIAS_EXPIRES_IN = "expiresIn";
    public static final String ALIAS_ONE_KEY = "oneKey";
    public static final String ALIAS_PAY_KEY = "payKey";
    public static final String ALIAS_MCH_ID = "mchId";
    public static final String ALIAS_SUB_MCH_ID = "subMchId";
    public static final String ALIAS_CERT_LOCAL_PATH = "certLocalPath";
    public static final String ALIAS_CERT_PASSWORD = "certPassword";
    public static final String ALIAS_JSAPI_TICKET = "jsapiTicket";
    public static final String ALIAS_JSAPI_TICKET_EXPIRES_IN = "jsapiTicketExpiresIn";
    public static final String ALIAS_JSAPI_TICKET_BEGIN_TIME = "jsapiTicketBeginTime";
    public static final String ALIAS_JSAPI_TICKET_END_TIME = "jsapiTicketEndTime";

    //date formats
    public static final String FORMAT_OP_TIME = DATE_FORMAT;
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
     * regCompanyId       db_column: reg_company_id
     */
    private String regCompanyId;
    /**
     * accountTypeId       db_column: account_type_id
     */
    private String accountTypeId;
    /**
     * code       db_column: code
     */

    private Long code;
    /**
     * weixinNo       db_column: weixin_no
     */
    private String weixinNo;
    /**
     * appid       db_column: appid
     */
    private String appid;
    /**
     * secret       db_column: secret
     */
    private String secret;
    /**
     * url       db_column: URL
     */
    private String url;
    /**
     * token       db_column: Token
     */
    private String token;
    /**
     * opTime       db_column: op_time
     */

    private Date opTime;
    /**
     * remark       db_column: remark
     */
    private String remark;
    /**
     * accessTokenBeginTime       db_column: access_token_begin_time
     */

    private Date accessTokenBeginTime;
    /**
     * accessTokenEndTime       db_column: access_token_end_time
     */

    private Date accessTokenEndTime;
    /**
     * accessToken       db_column: access_token
     */
    private String accessToken;
    /**
     * expiresIn       db_column: expires_in
     */

    private Long expiresIn;
    /**
     * oneKey       db_column: one_key
     */
    private String oneKey;
    /**
     * payKey       db_column: pay_key
     */
    private String payKey;
    /**
     * mchId       db_column: mch_id
     */

    private Integer mchId;
    /**
     * subMchId       db_column: sub_mch_id
     */

    private Integer subMchId;
    /**
     * certLocalPath       db_column: cert_local_path
     */
    private String certLocalPath;
    /**
     * certPassword       db_column: certPassword
     */
    private String certPassword;
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

    private Date jsapiTicketBeginTime;
    /**
     * jsapiTicketEndTime       db_column: jsapi_ticket_end_time
     */

    private Date jsapiTicketEndTime;
    //columns END

    public WeixinMp() {
    }

    public WeixinMp(
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

    public void setRegCompanyId(String value) {
        this.regCompanyId = value;
    }

    public String getRegCompanyId() {
        return this.regCompanyId;
    }

    public void setAccountTypeId(String value) {
        this.accountTypeId = value;
    }

    public String getAccountTypeId() {
        return this.accountTypeId;
    }

    public void setCode(Long value) {
        this.code = value;
    }

    public Long getCode() {
        return this.code;
    }

    public void setWeixinNo(String value) {
        this.weixinNo = value;
    }

    public String getWeixinNo() {
        return this.weixinNo;
    }

    public void setAppid(String value) {
        this.appid = value;
    }

    public String getAppid() {
        return this.appid;
    }

    public void setSecret(String value) {
        this.secret = value;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setUrl(String value) {
        this.url = value;
    }

    public String getUrl() {
        return this.url;
    }

    public void setToken(String value) {
        this.token = value;
    }

    public String getToken() {
        return this.token;
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

    public void setRemark(String value) {
        this.remark = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getAccessTokenBeginTimeString() {
        return DateConvertUtils.format(getAccessTokenBeginTime(), FORMAT_ACCESS_TOKEN_BEGIN_TIME);
    }

    public void setAccessTokenBeginTimeString(String value) {
        setAccessTokenBeginTime(DateConvertUtils.parse(value, FORMAT_ACCESS_TOKEN_BEGIN_TIME, Date.class));
    }

    public void setAccessTokenBeginTime(Date value) {
        this.accessTokenBeginTime = value;
    }

    public Date getAccessTokenBeginTime() {
        return this.accessTokenBeginTime;
    }

    public String getAccessTokenEndTimeString() {
        return DateConvertUtils.format(getAccessTokenEndTime(), FORMAT_ACCESS_TOKEN_END_TIME);
    }

    public void setAccessTokenEndTimeString(String value) {
        setAccessTokenEndTime(DateConvertUtils.parse(value, FORMAT_ACCESS_TOKEN_END_TIME, Date.class));
    }

    public void setAccessTokenEndTime(Date value) {
        this.accessTokenEndTime = value;
    }

    public Date getAccessTokenEndTime() {
        return this.accessTokenEndTime;
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

    public void setOneKey(String value) {
        this.oneKey = value;
    }

    public String getOneKey() {
        return this.oneKey;
    }

    public void setPayKey(String value) {
        this.payKey = value;
    }

    public String getPayKey() {
        return this.payKey;
    }

    public void setMchId(Integer value) {
        this.mchId = value;
    }

    public Integer getMchId() {
        return this.mchId;
    }

    public void setSubMchId(Integer value) {
        this.subMchId = value;
    }

    public Integer getSubMchId() {
        return this.subMchId;
    }

    public void setCertLocalPath(String value) {
        this.certLocalPath = value;
    }

    public String getCertLocalPath() {
        return this.certLocalPath;
    }

    public void setCertPassword(String value) {
        this.certPassword = value;
    }

    public String getCertPassword() {
        return this.certPassword;
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
        setJsapiTicketBeginTime(DateConvertUtils.parse(value, FORMAT_JSAPI_TICKET_BEGIN_TIME, Date.class));
    }

    public void setJsapiTicketBeginTime(Date value) {
        this.jsapiTicketBeginTime = value;
    }

    public Date getJsapiTicketBeginTime() {
        return this.jsapiTicketBeginTime;
    }

    public String getJsapiTicketEndTimeString() {
        return DateConvertUtils.format(getJsapiTicketEndTime(), FORMAT_JSAPI_TICKET_END_TIME);
    }

    public void setJsapiTicketEndTimeString(String value) {
        setJsapiTicketEndTime(DateConvertUtils.parse(value, FORMAT_JSAPI_TICKET_END_TIME, Date.class));
    }

    public void setJsapiTicketEndTime(Date value) {
        this.jsapiTicketEndTime = value;
    }

    public Date getJsapiTicketEndTime() {
        return this.jsapiTicketEndTime;
    }


    private Set weixinMpFollowers = new HashSet(0);

    public void setWeixinMpFollowers(Set<WeixinMpFollower> weixinMpFollower) {
        this.weixinMpFollowers = weixinMpFollower;
    }

    public Set<WeixinMpFollower> getWeixinMpFollowers() {
        return weixinMpFollowers;
    }


    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("RegCompanyId", getRegCompanyId())
                .append("AccountTypeId", getAccountTypeId())
                .append("Code", getCode())
                .append("WeixinNo", getWeixinNo())
                .append("Appid", getAppid())
                .append("Secret", getSecret())
                .append("Url", getUrl())
                .append("Token", getToken())
                .append("OpTime", getOpTime())
                .append("Remark", getRemark())
                .append("AccessTokenBeginTime", getAccessTokenBeginTime())
                .append("AccessTokenEndTime", getAccessTokenEndTime())
                .append("AccessToken", getAccessToken())
                .append("ExpiresIn", getExpiresIn())
                .append("OneKey", getOneKey())
                .append("PayKey", getPayKey())
                .append("MchId", getMchId())
                .append("SubMchId", getSubMchId())
                .append("CertLocalPath", getCertLocalPath())
                .append("CertPassword", getCertPassword())
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
        if (obj instanceof WeixinMp == false) return false;
        if (this == obj) return true;
        WeixinMp other = (WeixinMp) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

