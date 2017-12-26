/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2015
 */

package com.genaral.wechat;

import com.genaral.base.BaseEntity;
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


public class WeixinMenu extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "WeixinMenu";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_PARENT_ID = "parentId";
    public static final String ALIAS_WEIXIN_MP_ID = "weixinMpId";
    public static final String ALIAS_BUS_CODE = "busCode";
    public static final String ALIAS_KEY_CODE = "keyCode";
    public static final String ALIAS_NAME = "name";
    public static final String ALIAS_TYPE = "type";
    public static final String ALIAS_SEQ_INDEX = "seqIndex";

    //date formats

    //可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
    //columns START
    /**
     * id       db_column: id
     */
    private String id;
    /**
     * parentId       db_column: parent_id
     */
    private String parentId;
    /**
     * weixinMpId       db_column: weixin_mp_id
     */
    private String weixinMpId;
    /**
     * busCode       db_column: bus_code
     */
    private String busCode;
    /**
     * keyCode       db_column: key_code
     */
    private String keyCode;
    /**
     * name       db_column: name
     */
    private String name;
    /**
     * type       db_column: type
     */
    private String type;
    /**
     * seqIndex       db_column: seq_index
     */

    private Integer seqIndex;
    //columns END

    public WeixinMenu() {
    }

    public WeixinMenu(
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

    public void setParentId(String value) {
        this.parentId = value;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setWeixinMpId(String value) {
        this.weixinMpId = value;
    }

    public String getWeixinMpId() {
        return this.weixinMpId;
    }

    public void setBusCode(String value) {
        this.busCode = value;
    }

    public String getBusCode() {
        return this.busCode;
    }

    public void setKeyCode(String value) {
        this.keyCode = value;
    }

    public String getKeyCode() {
        return this.keyCode;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getType() {
        return this.type;
    }

    public void setSeqIndex(Integer value) {
        this.seqIndex = value;
    }

    public Integer getSeqIndex() {
        return this.seqIndex;
    }

    private Set weixinMenus = new HashSet(0);

    public void setWeixinMenus(Set<WeixinMenu> weixinMenu) {
        this.weixinMenus = weixinMenu;
    }

    public Set<WeixinMenu> getWeixinMenus() {
        return weixinMenus;
    }

    private WeixinMp weixinMp;

    public void setWeixinMp(WeixinMp weixinMp) {
        this.weixinMp = weixinMp;
    }

    public WeixinMp getWeixinMp() {
        return weixinMp;
    }

    private WeixinMenu weixinMenu;

    public void setWeixinMenu(WeixinMenu weixinMenu) {
        this.weixinMenu = weixinMenu;
    }

    public WeixinMenu getWeixinMenu() {
        return weixinMenu;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("ParentId", getParentId())
                .append("WeixinMpId", getWeixinMpId())
                .append("BusCode", getBusCode())
                .append("KeyCode", getKeyCode())
                .append("Name", getName())
                .append("Type", getType())
                .append("SeqIndex", getSeqIndex())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof WeixinMenu == false) return false;
        if (this == obj) return true;
        WeixinMenu other = (WeixinMenu) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

