package com.genaral.wechat.bestpay;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;

/**
 * 简洁的微信支付
 * 注：暂支持微信公众支付
 */
public class WxBestPay {
    private static Logger log = LoggerFactory.getLogger(WxBestPay.class);

    /**
     * 发起支付
     *
     * @param accountConfig
     * @param bestPayService
     * @param orderNO
     * @param openid
     * @param amount
     * @return payResponse
     */
    public PayResponse pay(WechatAccountConfig accountConfig, BestPayServiceImpl bestPayService, String orderNO, String openid, Double amount) {
        PayRequest request = new PayRequest();
        //支付请求参数
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        request.setOrderId(orderNO);
        request.setOrderAmount(amount);
        request.setOrderName("最好的支付sdk");
        request.setOpenid(openid);
        log.info("【发起支付】request={}", JsonUtil.toJson(request));

        bestPayService.setWxPayH5Config(initConfig(accountConfig));
        PayResponse payResponse = bestPayService.pay(request);
        log.info("【发起支付】response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    /**
     * 异步回调
     * @param accountConfig
     * @param bestPayService
     * @param notifyData
     * @return response
     */
    public PayResponse notify(WechatAccountConfig accountConfig, BestPayServiceImpl bestPayService, String notifyData) {
        log.info("【异步回调】request={}", notifyData);
        bestPayService.setWxPayH5Config(initConfig(accountConfig));
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("【异步回调】response={}", JsonUtil.toJson(response));

        return response;
    }

    /**
     * 初始化配置
     *
     * @param accountConfig
     * @return wxPayH5Config
     */
    public WxPayH5Config initConfig(WechatAccountConfig accountConfig) {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(accountConfig.getMpAppId());
        wxPayH5Config.setMchId(accountConfig.getMchId());
        wxPayH5Config.setMchKey(accountConfig.getMchKey());
        wxPayH5Config.setKeyPath(accountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(accountConfig.getNotifyUrl());
        return wxPayH5Config;
    }


}
