/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2015
 */

package com.genaral.oauth;

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


public class OauthClientDetailsQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;


    /**
     * clientId
     */
    private String clientId;
    /**
     * clientSecret
     */
    private String clientSecret;
    /**
     * clientName
     */
    private String clientName;
    /**
     * clientUri
     */
    private String clientUri;
    /**
     * clientIconUri
     */
    private String clientIconUri;
    /**
     * resourceIds
     */
    private String resourceIds;
    /**
     * scope
     */
    private String scope;
    /**
     * grantTypes
     */
    private String grantTypes;
    /**
     * redirectUri
     */
    private String redirectUri;
    /**
     * roles
     */
    private String roles;
    /**
     * accessTokenValidity
     */
    private Integer accessTokenValidity;
    /**
     * refreshTokenValidity
     */
    private Integer refreshTokenValidity;
    /**
     * description
     */
    private String description;
    /**
     * createTime
     */
    private Date createTimeBegin;
    private Date createTimeEnd;
    /**
     * archived
     */
    private Boolean archived;
    /**
     * trusted
     */
    private Boolean trusted;

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String value) {
        this.clientId = value;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String value) {
        this.clientSecret = value;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String value) {
        this.clientName = value;
    }

    public String getClientUri() {
        return this.clientUri;
    }

    public void setClientUri(String value) {
        this.clientUri = value;
    }

    public String getClientIconUri() {
        return this.clientIconUri;
    }

    public void setClientIconUri(String value) {
        this.clientIconUri = value;
    }

    public String getResourceIds() {
        return this.resourceIds;
    }

    public void setResourceIds(String value) {
        this.resourceIds = value;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String value) {
        this.scope = value;
    }

    public String getGrantTypes() {
        return this.grantTypes;
    }

    public void setGrantTypes(String value) {
        this.grantTypes = value;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public void setRedirectUri(String value) {
        this.redirectUri = value;
    }

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String value) {
        this.roles = value;
    }

    public Integer getAccessTokenValidity() {
        return this.accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer value) {
        this.accessTokenValidity = value;
    }

    public Integer getRefreshTokenValidity() {
        return this.refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer value) {
        this.refreshTokenValidity = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
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

    public Boolean getArchived() {
        return this.archived;
    }

    public void setArchived(Boolean value) {
        this.archived = value;
    }

    public Boolean getTrusted() {
        return this.trusted;
    }

    public void setTrusted(Boolean value) {
        this.trusted = value;
    }


    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

