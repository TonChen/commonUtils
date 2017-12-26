/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2015
 */

package com.genaral.oauth;

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


public class OauthClientDetails extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "OauthClientDetails";
    public static final String ALIAS_CLIENT_ID = "clientId";
    public static final String ALIAS_CLIENT_SECRET = "clientSecret";
    public static final String ALIAS_CLIENT_NAME = "clientName";
    public static final String ALIAS_CLIENT_URI = "clientUri";
    public static final String ALIAS_CLIENT_ICON_URI = "clientIconUri";
    public static final String ALIAS_RESOURCE_IDS = "resourceIds";
    public static final String ALIAS_SCOPE = "scope";
    public static final String ALIAS_GRANT_TYPES = "grantTypes";
    public static final String ALIAS_REDIRECT_URI = "redirectUri";
    public static final String ALIAS_ROLES = "roles";
    public static final String ALIAS_ACCESS_TOKEN_VALIDITY = "accessTokenValidity";
    public static final String ALIAS_REFRESH_TOKEN_VALIDITY = "refreshTokenValidity";
    public static final String ALIAS_DESCRIPTION = "description";
    public static final String ALIAS_CREATE_TIME = "createTime";
    public static final String ALIAS_ARCHIVED = "archived";
    public static final String ALIAS_TRUSTED = "trusted";

    //date formats
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;

    //可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
    //columns START
    /**
     * clientId       db_column: client_id
     */
    private String clientId;
    /**
     * clientSecret       db_column: client_secret
     */
    private String clientSecret;
    /**
     * clientName       db_column: client_name
     */
    private String clientName;
    /**
     * clientUri       db_column: client_uri
     */
    private String clientUri;
    /**
     * clientIconUri       db_column: client_icon_uri
     */
    private String clientIconUri;
    /**
     * resourceIds       db_column: resource_ids
     */
    private String resourceIds;
    /**
     * scope       db_column: scope
     */
    private String scope;
    /**
     * grantTypes       db_column: grant_types
     */
    private String grantTypes;
    /**
     * redirectUri       db_column: redirect_uri
     */
    private String redirectUri;
    /**
     * roles       db_column: roles
     */
    private String roles;
    /**
     * accessTokenValidity       db_column: access_token_validity
     */

    private Integer accessTokenValidity;
    /**
     * refreshTokenValidity       db_column: refresh_token_validity
     */

    private Integer refreshTokenValidity;
    /**
     * description       db_column: description
     */
    private String description;
    /**
     * createTime       db_column: create_time
     */
    private Date createTime;
    /**
     * archived       db_column: archived
     */

    private Boolean archived;
    /**
     * trusted       db_column: trusted
     */

    private Boolean trusted;
    //columns END

    public OauthClientDetails() {
    }

    public OauthClientDetails(
            String clientId
    ) {
        this.clientId = clientId;
    }

    public void setClientId(String value) {
        this.clientId = value;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientSecret(String value) {
        this.clientSecret = value;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientName(String value) {
        this.clientName = value;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientUri(String value) {
        this.clientUri = value;
    }

    public String getClientUri() {
        return this.clientUri;
    }

    public void setClientIconUri(String value) {
        this.clientIconUri = value;
    }

    public String getClientIconUri() {
        return this.clientIconUri;
    }

    public void setResourceIds(String value) {
        this.resourceIds = value;
    }

    public String getResourceIds() {
        return this.resourceIds;
    }

    public void setScope(String value) {
        this.scope = value;
    }

    public String getScope() {
        return this.scope;
    }

    public void setGrantTypes(String value) {
        this.grantTypes = value;
    }

    public String getGrantTypes() {
        return this.grantTypes;
    }

    public void setRedirectUri(String value) {
        this.redirectUri = value;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public void setRoles(String value) {
        this.roles = value;
    }

    public String getRoles() {
        return this.roles;
    }

    public void setAccessTokenValidity(Integer value) {
        this.accessTokenValidity = value;
    }

    public Integer getAccessTokenValidity() {
        return this.accessTokenValidity;
    }

    public void setRefreshTokenValidity(Integer value) {
        this.refreshTokenValidity = value;
    }

    public Integer getRefreshTokenValidity() {
        return this.refreshTokenValidity;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
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

    public void setArchived(Boolean value) {
        this.archived = value;
    }

    public Boolean getArchived() {
        return this.archived;
    }

    public void setTrusted(Boolean value) {
        this.trusted = value;
    }

    public Boolean getTrusted() {
        return this.trusted;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ClientId", getClientId())
                .append("ClientSecret", getClientSecret())
                .append("ClientName", getClientName())
                .append("ClientUri", getClientUri())
                .append("ClientIconUri", getClientIconUri())
                .append("ResourceIds", getResourceIds())
                .append("Scope", getScope())
                .append("GrantTypes", getGrantTypes())
                .append("RedirectUri", getRedirectUri())
                .append("Roles", getRoles())
                .append("AccessTokenValidity", getAccessTokenValidity())
                .append("RefreshTokenValidity", getRefreshTokenValidity())
                .append("Description", getDescription())
                .append("CreateTime", getCreateTime())
                .append("Archived", getArchived())
                .append("Trusted", getTrusted())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getClientId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof OauthClientDetails == false) return false;
        if (this == obj) return true;
        OauthClientDetails other = (OauthClientDetails) obj;
        return new EqualsBuilder()
                .append(getClientId(), other.getClientId())
                .isEquals();
    }
}

