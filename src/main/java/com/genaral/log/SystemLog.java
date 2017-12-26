/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2016
 */

package com.genaral.log;

import com.genaral.base.BaseEntity;
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


public class SystemLog extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "SystemLog";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_CHANNEL = "channel";
    public static final String ALIAS_TYPE = "type";
    public static final String ALIAS_UID = "uid";
    public static final String ALIAS_SID = "sid";
    public static final String ALIAS_CLIENT_ID = "clientId";
    public static final String ALIAS_CREATE_TIME = "createTime";
    public static final String ALIAS_ACCESS_IP = "accessIp";
    public static final String ALIAS_COUNTRY = "country";
    public static final String ALIAS_PROVINCE = "province";
    public static final String ALIAS_CITY = "city";
    public static final String ALIAS_DISTRICT = "district";
    public static final String ALIAS_ISP = "isp";
    public static final String ALIAS_REQUEST_ID = "requestId";
    public static final String ALIAS_REQUEST_URI = "requestUri";
    public static final String ALIAS_REQUEST_PARAM = "requestParam";
    public static final String ALIAS_REQUEST_TYPE = "requestType";
    public static final String ALIAS_REQUEST_AGENT = "requestAgent";
    public static final String ALIAS_REQUEST_RESULT = "requestResult";
    public static final String ALIAS_PUSH_UID = "pushUid";
    public static final String ALIAS_MOBILE_UID = "mobileUid";
    public static final String ALIAS_MOBILE_USERNAME = "mobileUsername";
    public static final String ALIAS_MOBILE_BRAND = "mobileBrand";
    public static final String ALIAS_MOBILE_STANDARD = "mobileStandard";
    public static final String ALIAS_MOBILE_WIDTH = "mobileWidth";
    public static final String ALIAS_MOBILE_HEIGHT = "mobileHeight";
    public static final String ALIAS_OS_TYPE = "osType";
    public static final String ALIAS_OS_VERSION = "osVersion";
    public static final String ALIAS_APP_VERSION = "appVersion";
    public static final String ALIAS_LNG = "lng";
    public static final String ALIAS_LAT = "lat";

    //date formats

    //可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
    //columns START
    /**
     * id       db_column: id
     */

    private Long id;
    /**
     * channel       db_column: channel
     */

    private Integer channel;
    /**
     * type       db_column: type
     */
    private Integer type;
    /**
     * uid       db_column: uid
     */
    private String uid;
    /**
     * sid       db_column: sid
     */
    private String sid;
    /**
     * clientId       db_column: client_id
     */
    private String clientId;
    /**
     * createTime       db_column: create_time
     */

    private Integer createTime;
    /**
     * accessIp       db_column: access_ip
     */

    private Long accessIp;
    /**
     * country       db_column: country
     */
    private String country;
    /**
     * province       db_column: province
     */
    private String province;
    /**
     * city       db_column: city
     */
    private String city;
    /**
     * district       db_column: district
     */
    private String district;
    /**
     * isp       db_column: isp
     */
    private String isp;
    /**
     * requestId       db_column: request_id
     */
    private String requestId;
    /**
     * requestUri       db_column: request_uri
     */
    private String requestUri;
    /**
     * requestParam       db_column: request_param
     */
    private String requestParam;
    /**
     * requestType       db_column: request_type
     */
    private String requestType;
    /**
     * requestAgent       db_column: request_agent
     */
    private String requestAgent;
    /**
     * requestResult       db_column: request_result
     */
    private String requestResult;
    /**
     * pushUid       db_column: push_uid
     */
    private String pushUid;
    /**
     * mobileUid       db_column: mobile_uid
     */
    private String mobileUid;
    /**
     * mobileUsername       db_column: mobile_username
     */
    private String mobileUsername;
    /**
     * mobileBrand       db_column: mobile_brand
     */
    private String mobileBrand;
    /**
     * mobileStandard       db_column: mobile_standard
     */
    private String mobileStandard;
    /**
     * mobileWidth       db_column: mobile_width
     */

    private Integer mobileWidth;
    /**
     * mobileHeight       db_column: mobile_height
     */

    private Integer mobileHeight;
    /**
     * osType       db_column: os_type
     */
    private String osType;
    /**
     * osVersion       db_column: os_version
     */
    private String osVersion;
    /**
     * appVersion       db_column: app_version
     */
    private String appVersion;
    /**
     * lng       db_column: lng
     */

    private Double lng;
    /**
     * lat       db_column: lat
     */

    private Double lat;
    /**
     * requestStatus       db_column: request_status
     */

    private Integer requestStatus;
    /**
     * requestLast       db_column: request_last
     */

    private Date requestLast;

    //columns END
    private String requestHeader;

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    //年月后缀
    private String ym;
    private String ymd;

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public SystemLog() {
    }

    public SystemLog(
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

    public void setChannel(Integer value) {
        this.channel = value;
    }

    public Integer getChannel() {
        return this.channel;
    }

    public void setType(Integer value) {
        this.type = value;
    }

    public Integer getType() {
        return this.type;
    }

    public void setUid(String value) {
        this.uid = value;
    }

    public String getUid() {
        return this.uid;
    }

    public void setSid(String value) {
        this.sid = value;
    }

    public String getSid() {
        return this.sid;
    }

    public void setClientId(String value) {
        this.clientId = value;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setCreateTime(Integer value) {
        this.createTime = value;
    }

    public Integer getCreateTime() {
        return this.createTime;
    }

    public void setAccessIp(Long value) {
        this.accessIp = value;
    }

    public Long getAccessIp() {
        return this.accessIp;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public String getCountry() {
        return this.country;
    }

    public void setProvince(String value) {
        this.province = value;
    }

    public String getProvince() {
        return this.province;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getCity() {
        return this.city;
    }

    public void setDistrict(String value) {
        this.district = value;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setIsp(String value) {
        this.isp = value;
    }

    public String getIsp() {
        return this.isp;
    }

    public void setRequestId(String value) {
        this.requestId = value;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestUri(String value) {
        this.requestUri = value;
    }

    public String getRequestUri() {
        return this.requestUri;
    }

    public void setRequestParam(String value) {
        this.requestParam = value;
    }

    public String getRequestParam() {
        return this.requestParam;
    }

    public void setRequestType(String value) {
        this.requestType = value;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestAgent(String value) {
        this.requestAgent = value;
    }

    public String getRequestAgent() {
        return this.requestAgent;
    }

    public void setRequestResult(String value) {
        this.requestResult = value;
    }

    public String getRequestResult() {
        return this.requestResult;
    }

    public void setPushUid(String value) {
        this.pushUid = value;
    }

    public String getPushUid() {
        return this.pushUid;
    }

    public void setMobileUid(String value) {
        this.mobileUid = value;
    }

    public String getMobileUid() {
        return this.mobileUid;
    }

    public void setMobileUsername(String value) {
        this.mobileUsername = value;
    }

    public String getMobileUsername() {
        return this.mobileUsername;
    }

    public void setMobileBrand(String value) {
        this.mobileBrand = value;
    }

    public String getMobileBrand() {
        return this.mobileBrand;
    }

    public void setMobileStandard(String value) {
        this.mobileStandard = value;
    }

    public String getMobileStandard() {
        return this.mobileStandard;
    }

    public void setMobileWidth(Integer value) {
        this.mobileWidth = value;
    }

    public Integer getMobileWidth() {
        return this.mobileWidth;
    }

    public void setMobileHeight(Integer value) {
        this.mobileHeight = value;
    }

    public Integer getMobileHeight() {
        return this.mobileHeight;
    }

    public void setOsType(String value) {
        this.osType = value;
    }

    public String getOsType() {
        return this.osType;
    }

    public void setOsVersion(String value) {
        this.osVersion = value;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setAppVersion(String value) {
        this.appVersion = value;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setLng(Double value) {
        this.lng = value;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLat(Double value) {
        this.lat = value;
    }

    public Double getLat() {
        return this.lat;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getRequestLast() {
        return requestLast;
    }

    public void setRequestLast(Date requestLast) {
        this.requestLast = requestLast;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("Channel", getChannel())
                .append("Type", getType())
                .append("Uid", getUid())
                .append("Sid", getSid())
                .append("ClientId", getClientId())
                .append("CreateTime", getCreateTime())
                .append("AccessIp", getAccessIp())
                .append("Country", getCountry())
                .append("Province", getProvince())
                .append("City", getCity())
                .append("District", getDistrict())
                .append("Isp", getIsp())
                .append("RequestId", getRequestId())
                .append("RequestUri", getRequestUri())
                .append("RequestParam", getRequestParam())
                .append("RequestType", getRequestType())
                .append("RequestAgent", getRequestAgent())
                .append("RequestResult", getRequestResult())
                .append("PushUid", getPushUid())
                .append("MobileUid", getMobileUid())
                .append("MobileUsername", getMobileUsername())
                .append("MobileBrand", getMobileBrand())
                .append("MobileStandard", getMobileStandard())
                .append("MobileWidth", getMobileWidth())
                .append("MobileHeight", getMobileHeight())
                .append("OsType", getOsType())
                .append("OsVersion", getOsVersion())
                .append("AppVersion", getAppVersion())
                .append("Lng", getLng())
                .append("Lat", getLat())
                .append("ip", getIp())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof SystemLog == false) return false;
        if (this == obj) return true;
        SystemLog other = (SystemLog) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

