package com.genaral.aliyun.push;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.push.model.v20160801.*;
import com.aliyuncs.utils.ParameterHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 阿里云移动推送服务
 * Created by rsh on 16/12/28.
 */
public class PushMessageClient extends AliyunAcsClient {

    /**
     * 推送消息给android
     *
     * @param target      推送目标 ，DEVICE:根据设备推送，ACCOUNT:根据账号推送，ALIAS:根据别名推送，TAG:根据标签推送，ALL:推送给全部设备，默认ALL
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。<br>
     *                    Target=DEVICE，值如deviceid111,deviceid1111<br>
     *                    Target=ACCOUNT，值如account111,account222<br>
     *                    Target=ALIAS，值如alias111,alias222<br>
     *                    Target=TAG，支持单Tag和多Tag，格式请参考标签格式<br>
     *                    Target=ALL，值为ALL<br>
     * @param title       消息标题
     * @param body        消息内容
     *                    <p>
     *                    参见文档 https://help.aliyun.com/document_detail/48085.html
     */
    @Deprecated
    public PushMessageToAndroidResponse pushMessageToAndroid(String target, String targetValue, String title, String body) throws ClientException {
        if (target == null || "".equals(target)) {
            target = "ALL";
            targetValue = "ALL";
        }
        if (targetValue == null) {
            log.error("targetValue 不能为空!");
            throw new ClientException("targetValue not null!");
        }
        PushMessageToAndroidRequest androidRequest = new PushMessageToAndroidRequest();
        //安全性比较高的内容建议使用HTTPS
        androidRequest.setProtocol(ProtocolType.HTTPS);
        //内容较大的请求，使用POST请求
        androidRequest.setMethod(MethodType.POST);
        androidRequest.setAppKey(aliyunPushAppKey);
        androidRequest.setTarget(target);
        androidRequest.setTargetValue(targetValue);
        androidRequest.setTitle(title);
        androidRequest.setBody(body);
        return client.getAcsResponse(androidRequest);
    }

    /**
     * 推送通知给android
     *
     * @param target      推送目标 ，DEVICE:根据设备推送，ACCOUNT:根据账号推送，ALIAS:根据别名推送，TAG:根据标签推送，ALL:推送给全部设备，默认ALL
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。<br>
     *                    Target=DEVICE，值如deviceid111,deviceid1111<br>
     *                    Target=ACCOUNT，值如account111,account222<br>
     *                    Target=ALIAS，值如alias111,alias222<br>
     *                    Target=TAG，支持单Tag和多Tag，格式请参考标签格式<br>
     *                    Target=ALL，值为ALL<br>
     * @param title       消息标题
     * @param body        消息内容
     *                    <p>
     *                    参见文档 https://help.aliyun.com/document_detail/48087.html
     */
    @Deprecated
    public PushNoticeToAndroidResponse pushNoticeToAndroid(String target, String targetValue, String title, String body, Map<String, Object> custom) throws ClientException {
        if (target == null || "".equals(target)) {
            target = "ALL";
            targetValue = "ALL";
        }
        if (targetValue == null) {
            log.error("targetValue 不能为空!");
            throw new ClientException("targetValue not null!");
        }
        PushNoticeToAndroidRequest androidRequest = new PushNoticeToAndroidRequest();
        //安全性比较高的内容建议使用HTTPS
        androidRequest.setProtocol(ProtocolType.HTTPS);
        //内容较大的请求，使用POST请求
        androidRequest.setMethod(MethodType.POST);
        androidRequest.setAppKey(aliyunPushAppKey);
        androidRequest.setTarget(target);
        androidRequest.setTargetValue(targetValue);
        androidRequest.setTitle(title);
        androidRequest.setBody(body);
        if (custom != null) {
            androidRequest.setExtParameters(JSON.toJSONString(custom));
        }
        return client.getAcsResponse(androidRequest);
    }


    /**
     * 推送消息给iOS
     *
     * @param target      推送目标 ，DEVICE:根据设备推送，ACCOUNT:根据账号推送，ALIAS:根据别名推送，TAG:根据标签推送，ALL:推送给全部设备，默认ALL
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。<br>
     *                    Target=DEVICE，值如deviceid111,deviceid1111<br>
     *                    Target=ACCOUNT，值如account111,account222<br>
     *                    Target=ALIAS，值如alias111,alias222<br>
     *                    Target=TAG，支持单Tag和多Tag，格式请参考标签格式<br>
     *                    Target=ALL，值为ALL<br>
     * @param title       消息标题
     * @param body        消息内容
     *                    <p>
     *                    参见文档 https://help.aliyun.com/document_detail/48086.html
     */
    @Deprecated
    public PushMessageToiOSResponse pushMessageToIOS(String target, String targetValue, String title, String body) throws ClientException {
        if (target == null || "".equals(target)) {
            target = "ALL";
            targetValue = "ALL";
        }
        if (targetValue == null) {
            log.error("targetValue 不能为空!");
            throw new ClientException("targetValue not null!");
        }
        PushMessageToiOSRequest iOSRequest = new PushMessageToiOSRequest();
        //安全性比较高的内容建议使用HTTPS
        iOSRequest.setProtocol(ProtocolType.HTTPS);
        //内容较大的请求，使用POST请求
        iOSRequest.setMethod(MethodType.POST);
        iOSRequest.setAppKey(aliyunPushAppKey);
        iOSRequest.setTarget(target);
        iOSRequest.setTargetValue(targetValue);
        iOSRequest.setTitle(title);
        iOSRequest.setBody(body);
        return client.getAcsResponse(iOSRequest);
    }

    /**
     * 推送通知给iOS
     *
     * @param target      推送目标 ，DEVICE:根据设备推送，ACCOUNT:根据账号推送，ALIAS:根据别名推送，TAG:根据标签推送，ALL:推送给全部设备，默认ALL
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。<br>
     *                    Target=DEVICE，值如deviceid111,deviceid1111<br>
     *                    Target=ACCOUNT，值如account111,account222<br>
     *                    Target=ALIAS，值如alias111,alias222<br>
     *                    Target=TAG，支持单Tag和多Tag，格式请参考标签格式<br>
     *                    Target=ALL，值为ALL<br>
     * @param body        消息内容
     *                    <p>
     *                    参见文档 https://help.aliyun.com/document_detail/48088.html
     */
    @Deprecated
    public PushNoticeToiOSResponse pushNoticeToIOS(String target, String targetValue, String body, Map<String, Object> custom) throws ClientException {
        if (target == null || "".equals(target)) {
            target = "ALL";
            targetValue = "ALL";
        }
        if (targetValue == null) {
            log.error("targetValue 不能为空!");
            throw new ClientException("targetValue not null!");
        }
        PushNoticeToiOSRequest iOSRequest = new PushNoticeToiOSRequest();
        //安全性比较高的内容建议使用HTTPS
        iOSRequest.setProtocol(ProtocolType.HTTPS);
        //内容较大的请求，使用POST请求
        iOSRequest.setMethod(MethodType.POST);
        iOSRequest.setAppKey(aliyunPushAppKey);
        //iOS的通知是通过APNS中心来发送的，需要填写对应的环境信息. DEV :表示开发环境, PRODUCT: 表示生产环境
        iOSRequest.setApnsEnv(aliyunPushIosEnv);
        iOSRequest.setTarget(target);
        iOSRequest.setTargetValue(targetValue);
        //iOSRequest.setTitle("eewwewe");
        iOSRequest.setBody(body);
        if (custom != null) {
            iOSRequest.setExtParameters(JSON.toJSONString(custom));
        }
        return client.getAcsResponse(iOSRequest);
    }


    /**
     * 推送高级接口，推荐使用
     *
     * @param target      推送目标 ，DEVICE:根据设备推送，ACCOUNT:根据账号推送，ALIAS:根据别名推送，TAG:根据标签推送，ALL:推送给全部设备，默认ALL
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。<br>
     *                    Target=DEVICE，值如deviceid111,deviceid1111<br>
     *                    Target=ACCOUNT，值如account111,account222<br>
     *                    Target=ALIAS，值如alias111,alias222<br>
     *                    Target=TAG，支持单Tag和多Tag，格式请参考标签格式<br>
     *                    Target=ALL，值为ALL<br>
     * @param pushType    消息类型, MESSAGE：消息， NOTICE：通知， 默认MESSAGE
     * @param deviceType  设备类型, ANDROID:安卓， iOS：苹果 ，ALL：所有，默认ALL
     * @param title       消息标题
     * @param body        消息内容
     * @param custom      自定义消息
     * @param activity    安卓通知打开指定页面包名
     * @param sendTime    推送时间，格式为yyyy-MM-dd HH:mm:ss 默认立即推送,时间格式解析出错将立即推送
     *                    <p>
     *                    参见文档 https://help.aliyun.com/document_detail/48089.html
     */
    public PushResponse advancedPush(String target, String targetValue, String pushType, String deviceType, String title, String body,
                                     Map<String, Object> custom, String activity, String sendTime) throws ClientException {
        if (target == null || "".equals(target)) {
            target = Message.Target.ALL.getValue();
            targetValue = "ALL";
        }
        if (targetValue == null) {
            log.error("targetValue 不能为空!");
            throw new ClientException("targetValue not null!");
        }
        if (pushType == null || "".equals(pushType)) {
            pushType = "MESSAGE";
        }
        if (deviceType == null || "".equals(deviceType)) {
            deviceType = Message.DeviceType.ALL.getValue();
        }
        PushRequest pushRequest = new PushRequest();
        //安全性比较高的内容建议使用HTTPS
        pushRequest.setProtocol(ProtocolType.HTTPS);
        //内容较大的请求，使用POST请求
        pushRequest.setMethod(MethodType.POST);
        // 推送目标
        pushRequest.setAppKey(aliyunPushAppKey);
        pushRequest.setTarget(target); //推送目标: DEVICE:按设备推送 ALIAS : 按别名推送 ACCOUNT:按帐号推送  TAG:按标签推送; ALL: 广播推送
        pushRequest.setTargetValue(targetValue); //根据Target来设定，如Target=DEVICE, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
//      pushRequest.setTarget("ALL"); //推送目标: device:推送给设备; account:推送给指定帐号,tag:推送给自定义标签; all: 推送给全部
//      pushRequest.setTargetValue("ALL"); //根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
        pushRequest.setPushType(pushType); // 消息类型, MESSAGE NOTICE
        pushRequest.setDeviceType(deviceType); // 设备类型 ANDROID iOS ALL.

        // 推送配置
        pushRequest.setTitle(title); // 消息的标题
        pushRequest.setBody(body); // 消息的内容

        // 推送配置: iOS
        if ("ALL".equals(deviceType) || "iOS".equals(deviceType)) {
            pushRequest.setIOSBadge(1); // iOS应用图标右上角角标
            pushRequest.setIOSSilentNotification(false);//开启静默通知
            pushRequest.setIOSMusic("default"); // iOS通知声音
            pushRequest.setIOSSubtitle(null);//iOS10通知副标题的内容
            //pushRequest.setiOSNotificationCategory("iOS10 Notification Category");//指定iOS10通知Category
            pushRequest.setIOSNotificationCategory("");//指定iOS10通知Category
            pushRequest.setIOSMutableContent(true);//是否允许扩展iOS通知内容
            pushRequest.setIOSApnsEnv(aliyunPushIosEnv);//iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" : 表示生产环境
            pushRequest.setIOSRemind(true); // 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：离线消息转通知仅适用于生产环境
            pushRequest.setIOSRemindBody(body);//iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT && iOSRemind为true时有效
            if (custom != null) {
                pushRequest.setIOSExtParameters(JSON.toJSONString(custom)); //通知的扩展属性(注意 : 该参数要以json map的格式传入,否则会解析出错)
            }
        }

        // 推送配置: Android
        if ("ALL".equals(deviceType) || "ANDROID".equals(deviceType)) {
            pushRequest.setAndroidNotifyType("NONE");//通知的提醒方式 "VIBRATE" : 震动 "SOUND" : 声音 "BOTH" : 声音和震动 NONE : 静音
            pushRequest.setAndroidNotificationBarType(1);//通知栏自定义样式0-100
            pushRequest.setAndroidNotificationBarPriority(1);//通知栏自定义样式0-100
            pushRequest.setAndroidOpenType("ACTIVITY"); //点击通知后动作 "APPLICATION" : 打开应用, "ACTIVITY" : 打开AndroidActivity, "URL" : 打开URL, "NONE" : 无跳转
            pushRequest.setAndroidOpenUrl(""); //Android收到推送后打开对应的url,仅当AndroidOpenType="URL"有效
            pushRequest.setAndroidActivity(activity); // 设定通知打开的activity，仅当AndroidOpenType="Activity"有效
            pushRequest.setAndroidMusic("default"); // Android通知音乐
            //pushRequest.setAndroidXiaoMiActivity(activity);//设置该参数后启动小米托管弹窗功能, 此处指定通知点击后跳转的Activity（托管弹窗的前提条件：1. 集成小米辅助通道；2. StoreOffline参数设为true）
            //pushRequest.setAndroidXiaoMiNotifyTitle(title);
            //pushRequest.setAndroidXiaoMiNotifyBody(body);
            if (custom != null) {
                pushRequest.setAndroidExtParameters(JSON.toJSONString(custom)); //设定通知的扩展属性。(注意 : 该参数要以 json map 的格式传入,否则会解析出错)
            }
        }

        // 推送控制
        Date pushDate = new Date();
        if (sendTime != null && !"".equals(sendTime)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pushDate = sdf.parse(sendTime);
            } catch (ParseException e) {
            }
        }
        String pushTime = ParameterHelper.getISO8601Time(pushDate);
        pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
        String expireTime = ParameterHelper.getISO8601Time(new Date(pushDate.getTime() + 12 * 3600 * 1000)); // 12小时后消息失效, 不会再发送
        pushRequest.setExpireTime(expireTime);
        pushRequest.setStoreOffline(true); // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到

        return client.getAcsResponse(pushRequest);
    }


    /**
     * 取消定时推送
     *
     * @param messageId 消息ID
     *                  <p>
     */
    public CancelPushResponse testCancelPush(String messageId) throws ClientException {
        CancelPushRequest request = new CancelPushRequest();
        request.setAppKey(aliyunPushAppKey);
        request.setMessageId(messageId);
        return client.getAcsResponse(request);
    }

    public static void main(String[] args) {

    }

}
