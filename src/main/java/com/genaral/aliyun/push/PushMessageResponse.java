package com.genaral.aliyun.push;

/**
 * 阿里云移动推送返回结果
 * Created by rsh on 16/12/28.
 */
public class PushMessageResponse {

    private String requestId;
    private String messageId;

    public PushMessageResponse() {
        super();
    }

    public PushMessageResponse(String requestId, String messageId) {
        super();
        this.requestId = requestId;
        this.messageId = messageId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


}
