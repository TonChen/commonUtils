package com.genaral.image;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.green.model.v20161018.ImageDetectionRequest;
import com.aliyuncs.green.model.v20161018.ImageDetectionResponse;
import com.aliyuncs.green.model.v20161018.ImageDetectionResponse.ImageResult.*;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.genaral.sensitiveword.SensitivewordFilter;
import com.model.JsonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * 同步图片检测结果调用示例，调用会实时返回检测结果
 */
public class SyncImageDetectionSample {

    private IClientProfile profile;
    private IAcsClient client;
    private String accessKeyId;
    private String accessKeySecret;
    protected static final Logger log = LoggerFactory.getLogger(SyncImageDetectionSample.class);

    public JsonBean imageDetection(String imgUrl) {
        log.info("检测图片地址==========" + imgUrl.replace("-cn-hangzhou", ""));
        ImageDetectionRequest imageDetectionRequest = new ImageDetectionRequest();
        /**
         * 是否同步调用
         * false: 同步
         */
        imageDetectionRequest.setAsync(false);
        /**
         * 同步图片检测支持多个场景:
         * porn:  黄图检测
         * ocr:  ocr文字识别
         * illegal: 暴恐敏感检测
         */
        imageDetectionRequest.setScenes(Arrays.asList("porn", "ocr", "illegal"));
//        imageDetectionRequest.setScenes(Arrays.asList("porn"));
        /**
         * 同步图片检测一次只支持单张图片进行检测
         */
        imageDetectionRequest.setImageUrls(Arrays.asList(imgUrl.replace("-cn-hangzhou", "")));
        try {
            ImageDetectionResponse imageDetectionResponse = getClient().getAcsResponse(imageDetectionRequest);
            log.info("image detection res===" + JSON.toJSONString(imageDetectionResponse));
            if ("Success".equals(imageDetectionResponse.getCode())) {
                List<ImageDetectionResponse.ImageResult> imageResults = imageDetectionResponse.getImageResults();
                if (imageResults != null && imageResults.size() > 0) {
                    //同步图片检测只有一个返回的ImageResult
                    ImageDetectionResponse.ImageResult imageResult = imageResults.get(0);
                    //porn场景对应的检测结果放在pornResult字段中
                    //ocr场景对应的检测结果放在ocrResult字段中
                    //illegal场景对应的检测结果放在illegalResult字段中
                    //请按需获取
                    /**
                     * 黄图检测结果
                     */
                    ImageDetectionResponse.ImageResult.PornResult pornResult = imageResult.getPornResult();
                    if (pornResult != null) {
                        /**
                         * 绿网给出的建议值, 0表示正常，1表示色情，2表示需要review
                         */
                        Integer label = pornResult.getLabel();
                        /**
                         * 黄图分值, 0-100
                         */
                        Float rate = pornResult.getRate();
                        log.info("image label===" + label);
                        log.info("image rate===" + rate);
                        JsonBean json = null;
                        if (0 == label) {
                            json = new JsonBean("0", "操作成功", "图片正常", rate);
                        } else {
                            json = new JsonBean("2", "操作失败", "图片不正常", rate);
                        }
                        return json;
                        // 根据业务情况来做处理
                    }
                    //其他的场景的类似向黄图检测结果一样去get结果，进行处理
                    /**
                     * ocr识别结果
                     */
                    OcrResult ocrResult = imageResult.getOcrResult();
                    if (ocrResult != null) {
                        List<String> texts = ocrResult.getText();
                        if (texts != null && texts.size() > 0) {
                            for (String text : texts) {
                                log.info(text);
                                SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();
                                boolean flag = sensitivewordFilter.isContaintSensitiveWord(text, 1);
                                if (flag) {
                                    return new JsonBean("2", "操作失败", "图片不正常", text);
                                }
                            }
                        }
                    }

                    SpamResult spamResult = imageResult.getSpamResult();
                    if (spamResult != null) {
                        /**
                         * 是否命中
                         */
                        Boolean isHit = spamResult.getHit();
                        if (isHit != null && isHit) {
                            /**
                             * 命中的关键词及上下文
                             */
                            spamResult.getKeywordResults();
                        }
                    }

                    IllegalResult illegalResult = imageResult.getIllegalResult();
                    if (illegalResult != null) {
                        /**
                         * 绿网给出的建议值, 0表示正常，1表示色情，2表示需要review
                         */
                        illegalResult.getLabel();
                        /**
                         * 分值, 0-100
                         */
                        illegalResult.getRate();
                    }

                    /**
                     * 图片二维码识别结果
                     */
                    QrcodeResult qrcodeResult = imageResult.getQrcodeResult();
                    if (qrcodeResult != null) {
                        List<String> qrcodelist = qrcodeResult.getQrcodeList();
                        if (qrcodelist != null && qrcodelist.size() > 0) {
                            for (String qrcodeText : qrcodelist) {
                                System.out.println(qrcodeText);
                            }
                        }
                    }

                    /**
                     * 敏感人脸识别结果
                     */
                    SensitiveFaceResult sensitiveFaceResult = imageResult.getSensitiveFaceResult();

                    if (sensitiveFaceResult != null) {
                        List<SensitiveFaceResult.ImageSensitiveFaceHitItem> imageSensitiveFaceHitItems = sensitiveFaceResult.getItems();
                        if (imageSensitiveFaceHitItems != null && imageSensitiveFaceHitItems.size() > 0) {
                            //表示有命中, imageSensitiveFaceHitItems.size()：表示检测出了多少个人脸
                            for (SensitiveFaceResult.ImageSensitiveFaceHitItem imageSensitiveFaceHitItem : imageSensitiveFaceHitItems) {
                                /**
                                 * 人脸的位置
                                 */
                                imageSensitiveFaceHitItem.getX(); //x轴位置
                                imageSensitiveFaceHitItem.getY(); //y轴位置
                                imageSensitiveFaceHitItem.getw(); //宽度
                                imageSensitiveFaceHitItem.geth(); //高度

                                /**
                                 * 命中的敏感人物
                                 */
                                List<SensitiveFaceResult.ImageSensitiveFaceHitItem.ImageSensitiveFaceSimiInfo> imageSensitiveFaceSimiInfos = imageSensitiveFaceHitItem.getSimiInfoList();
                                for (SensitiveFaceResult.ImageSensitiveFaceHitItem.ImageSensitiveFaceSimiInfo imageSensitiveFaceSimiInfo : imageSensitiveFaceSimiInfos) {
                                    //敏感人物姓名
                                    imageSensitiveFaceSimiInfo.getName();
                                    //匹配相似度分值0-100，越高越相似
                                    imageSensitiveFaceSimiInfo.getScore();
                                }
                            }
                        }
                    }
                }
            } else {
                /**
                 * 检测失败
                 */
                if ("ImageDetectFailed".equals(imageDetectionResponse.getCode())) {
                    return new JsonBean("0", "检测失败", "ImageDetectFailed continue", "");
                } else {
                    return new JsonBean("2", "操作失败", "检测失败", "");
                }

            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new JsonBean("2", "操作失败", "操作失败", "");
    }

    private IClientProfile getIClient() {
        if (this.profile == null)
            this.profile = DefaultProfile.getProfile("cn-hangzhou", this.accessKeyId, this.accessKeySecret);
        return this.profile;
    }

    private IAcsClient getClient() {
        if (this.client == null)
            this.client = new DefaultAcsClient(getIClient());
        return this.client;
    }

    public static void main(String[] args) {
        //请替换成你自己的accessKeyId、accessKeySecret

        String accessKeyId = "G8vIOjCPfPDflv5V";
        String accessKeySecret = "uR48kNr0tKr9LEKo8oCLgp4g7aGvgM";
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        ImageDetectionRequest imageDetectionRequest = new ImageDetectionRequest();
        /**
         * 是否同步调用
         * false: 同步
         */
        imageDetectionRequest.setAsync(false);
        /**
         * 同步图片检测支持多个场景:
         * porn:  黄图检测
         * ocr:  ocr文字识别
         * illegal: 暴恐敏感检测
         */
        imageDetectionRequest.setScenes(Arrays.asList("porn", "ocr", "illegal"));
//        imageDetectionRequest.setScenes(Arrays.asList("porn"));
        /**
         * 同步图片检测一次只支持单张图片进行检测
         */
//        imageDetectionRequest.setImageUrls(Arrays.asList("http://daihuo2.oss.aliyuncs.com/2016/12/01/1480575158.jpg"));
        imageDetectionRequest.setImageUrls(Arrays.asList("http://daihuo2.oss.aliyuncs.com/2017/02/23/4611089495818240.jpg"));
        try {
            ImageDetectionResponse imageDetectionResponse = client.getAcsResponse(imageDetectionRequest);
            log.info("image detection res===" + JSON.toJSONString(imageDetectionResponse));
            if ("Success".equals(imageDetectionResponse.getCode())) {
                List<ImageDetectionResponse.ImageResult> imageResults = imageDetectionResponse.getImageResults();
                if (imageResults != null && imageResults.size() > 0) {
                    //同步图片检测只有一个返回的ImageResult
                    ImageDetectionResponse.ImageResult imageResult = imageResults.get(0);

                    //porn场景对应的检测结果放在pornResult字段中
                    //ocr场景对应的检测结果放在ocrResult字段中
                    //illegal场景对应的检测结果放在illegalResult字段中
                    //spam场景对应的检测结果放在spamResult字段中
                    //请按需获取
                    /**
                     * 黄图检测结果
                     */
                    ImageDetectionResponse.ImageResult.PornResult pornResult = imageResult.getPornResult();
                    if (pornResult != null) {
                        /**
                         * 绿网给出的建议值, 0表示正常，1表示色情，2表示需要review
                         */
                        Integer label = pornResult.getLabel();

                        /**
                         * 黄图分值, 0-100
                         */
                        Float rate = pornResult.getRate();

                        // 根据业务情况来做处理
                    }

                    //其他的场景的类似向黄图检测结果一样去get结果，进行处理
                    /**
                     * ocr识别结果
                     */
                    OcrResult ocrResult = imageResult.getOcrResult();
                    if (ocrResult != null) {
                        List<String> texts = ocrResult.getText();
                        if (texts != null && texts.size() > 0) {
                            for (String text : texts) {
                                System.out.println(text);
                            }
                        }
                    }

                    SpamResult spamResult = imageResult.getSpamResult();
                    if (spamResult != null) {
                        /**
                         * 是否命中
                         */
                        Boolean isHit = spamResult.getHit();
                        if (isHit != null && isHit) {
                            /**
                             * 命中的关键词及上下文
                             */
                            spamResult.getKeywordResults();
                        }
                    }

                    IllegalResult illegalResult = imageResult.getIllegalResult();
                    if (illegalResult != null) {
                        /**
                         * 绿网给出的建议值, 0表示正常，1表示色情，2表示需要review
                         */
                        illegalResult.getLabel();
                        /**
                         * 分值, 0-100
                         */
                        illegalResult.getRate();
                    }

                    /**
                     * 图片二维码识别结果
                     */
                    QrcodeResult qrcodeResult = imageResult.getQrcodeResult();
                    if (qrcodeResult != null) {
                        List<String> qrcodelist = qrcodeResult.getQrcodeList();
                        if (qrcodelist != null && qrcodelist.size() > 0) {
                            for (String qrcodeText : qrcodelist) {
                                System.out.println(qrcodeText);
                            }
                        }
                    }

                    /**
                     * 敏感人脸识别结果
                     */
                    SensitiveFaceResult sensitiveFaceResult = imageResult.getSensitiveFaceResult();

                    if (sensitiveFaceResult != null) {
                        List<SensitiveFaceResult.ImageSensitiveFaceHitItem> imageSensitiveFaceHitItems = sensitiveFaceResult.getItems();
                        if (imageSensitiveFaceHitItems != null && imageSensitiveFaceHitItems.size() > 0) {
                            //表示有命中, imageSensitiveFaceHitItems.size()：表示检测出了多少个人脸
                            for (SensitiveFaceResult.ImageSensitiveFaceHitItem imageSensitiveFaceHitItem : imageSensitiveFaceHitItems) {
                                /**
                                 * 人脸的位置
                                 */
                                imageSensitiveFaceHitItem.getX(); //x轴位置
                                imageSensitiveFaceHitItem.getY(); //y轴位置
                                imageSensitiveFaceHitItem.getw(); //宽度
                                imageSensitiveFaceHitItem.geth(); //高度

                                /**
                                 * 命中的敏感人物
                                 */
                                List<SensitiveFaceResult.ImageSensitiveFaceHitItem.ImageSensitiveFaceSimiInfo> imageSensitiveFaceSimiInfos = imageSensitiveFaceHitItem.getSimiInfoList();
                                for (SensitiveFaceResult.ImageSensitiveFaceHitItem.ImageSensitiveFaceSimiInfo imageSensitiveFaceSimiInfo : imageSensitiveFaceSimiInfos) {
                                    //敏感人物姓名
                                    imageSensitiveFaceSimiInfo.getName();
                                    //匹配相似度分值0-100，越高越相似
                                    imageSensitiveFaceSimiInfo.getScore();
                                }

                            }
                        }
                    }
                }
            } else {
                /**
                 * 检测失败
                 */
                log.info("图片检测失败");
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }


}