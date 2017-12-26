package com.genaral.base;

import com.genaral.wechat.WeixinMenu;
import com.genaral.wechat.WeixinMp;
import com.genaral.wechat.receive.ReceiveMessage;
import com.genaral.wechat.reply.BaseReplyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Host	tieba.baidu.com
 * Referer	http://tieba.baidu.com/p/2005518355
 *
 * @author xiaokeheng
 */
@Transactional
public abstract class BaseWxBus {

    protected Logger log = LoggerFactory.getLogger(getClass());

    public BaseReplyMessage service(ReceiveMessage receive, WeixinMenu weixinMenu, WeixinMp weixinMp) {
        return null;
    }
}
