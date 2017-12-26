package com.genaral.base;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.genaral.base64.Base64Image;
import com.genaral.date.DateConvertUtils;
import com.genaral.image.SyncImageDetectionSample;
import com.genaral.json.JsonUtil;
import com.genaral.log.SystemLog;
import com.genaral.oauth.OauthClientDetails;
import com.genaral.oauth.OauthClientDetailsManager;
import com.genaral.object.ObjectUtil;
import com.genaral.page.Page;
import com.genaral.page.PageRequest;
import com.genaral.page.PageRequestFactory;
import com.genaral.security.Des3;
import com.genaral.security.MD5Util;
import com.genaral.sequence.IdWorker;
import com.genaral.sequence.RandomUtil;
import com.genaral.spring.SpringContextUtil;
import com.genaral.string.StringUtil;
import com.model.JsonBean;
import com.model.UploadFileOssResult;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public abstract class BaseAction {
    protected Logger log = LoggerFactory.getLogger(getClass());

    //图片鉴黄开关
    private static final boolean isCheckImg = false;
    public static final String POST = "POST";
    public static final int COOKIE_AGE = 60 * 60 * 24 * 30;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession session;

    @Autowired
    private SyncImageDetectionSample syncImageDetectionSample;

    /**
     * 获取当前登录员工Id
     *
     * @return id
     */
    public String getStaffId() {
        String uid = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Session session = subject.getSession();
            uid = (String) session.getAttribute("uid");
        }
        if (uid == null) {
            Object uo = request.getSession().getAttribute("uid");
            if (uo != null)
                uid = (String) uo;
        }
        return uid;
    }


    public void redirect(String redirectLocation) {
        if (isPathUrl(redirectLocation)) {
            if (!redirectLocation.startsWith("/")) {
                redirectLocation = "/" + redirectLocation;
            }
            if ((request.getContextPath() != null)
                    && (request.getContextPath().length() > 0)) {
                redirectLocation = request.getContextPath() + redirectLocation;
            }
        }
        try {
//			String path = request.getContextPath();
            String schmesSSL = "";
            String refer = request.getHeader("Referer");
            if (refer != null) {
                if (refer.startsWith("https")) {
                    schmesSSL = "s";
                }
            } else if (request.getServerName().contains("dbuy360.com")) {
                schmesSSL = "s";
            }
            String basePath = request.getScheme() + schmesSSL + "://" + request.getServerName();
            if (request.getServerPort() == 80) {
                basePath = basePath + "/";
            } else {
                basePath = basePath + ":" + request.getServerPort() + "/";
            }
            //https://cttoms.dbuy360.com:1443
            //admin/login/main2.jsp
            log.info("redirect>>" + basePath + redirectLocation);
            response.sendRedirect(response.encodeRedirectURL(basePath + redirectLocation));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 此方法会在所有的event handler之前执行。
     */
    public void beforeExecution() {
        response.setContentType("text/html");
    }

    /**
     * 此方法会在所有的event handler之后执行。
     */
    public void afterExecution() throws IOException {
        response.flushBuffer(); // 此调用并非必须，只是用来演示afterExecution方法而已
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    // public void doGetRundata(TurbineRunData rundata){
    // rundata = rundata;
    // }
    //
    // public TurbineRunData getRundata() {
    // return rundata;
    // }

    @SuppressWarnings("rawtypes")
    public void savePage(Page page, PageRequest pageRequest) {
        savePage("", page, pageRequest);
    }

    /**
     * 用于一个页面有多个extremeTable是使用
     *
     * @param tableId 等于extremeTable的tableId属性
     */
    @SuppressWarnings("rawtypes")
    public void savePage(String tableId, Page page, PageRequest pageRequest) {
        Assert.notNull(tableId, "tableId must be not null");
        Assert.notNull(page, "page must be not null");

        getRequest().setAttribute(tableId + "pageList", page);
        getRequest().setAttribute(tableId + "totalRows",
                new Integer(page.getTotalCount()));
        getRequest().setAttribute(tableId + "pageRequest", pageRequest);
        getRequest().setAttribute(tableId + "query", pageRequest);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T extends PageRequest> T newQuery(Class<T> queryClazz,
                                              String defaultSortColumns) {
        PageRequest query = PageRequestFactory.bindPageRequest(
                org.springframework.beans.BeanUtils
                        .instantiateClass(queryClazz), getRequest(),
                defaultSortColumns);
        return (T) query;
    }

    private static boolean isPathUrl(String url) {
        // filter out "http:", "https:", "mailto:", "file:", "ftp:"
        // since the only valid places for : in URL's is before the path
        // specification
        // either before the port, or after the protocol
        return (url.indexOf(':') == -1);
    }

    public boolean isNullOrEmptyString(Object o) {
        return ObjectUtil.isNullOrEmptyString(o);
    }

    public static boolean isMobileNumber(String mobiles) {
        return ObjectUtil.isMobileNumber(mobiles);
    }

    public static boolean isNumber(String num) {
        return ObjectUtil.isNumber(num);
    }

    /**
     * 可以用于判断 Map,Collection,String,Array是否为空
     *
     * @param o
     * @return
     */
    public boolean isEmpty(Object o) {
        return ObjectUtil.isEmpty(o);
    }

    /**
     * @param sendPasswordDate 发送时间
     * @param delay            延时 秒
     * @return
     */
    public boolean checkTimeOut(Date sendPasswordDate, int delay) {
        if (sendPasswordDate == null) {
            return true;
        } else {
            // 10分钟的毫秒数
            long mills = delay * 1000;
            // 发送的毫秒数+10分钟后的毫秒数
            long sendTime = sendPasswordDate.getTime() + mills;
            // 当前时间的毫秒数
            long currentTime = System.currentTimeMillis();
            if (sendTime < currentTime) { // 当前时间大于发送10分钟后的时间返回true
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 是否登录 -1未登录
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public JsonBean checkVip(String back) {
        Object vip_id = getRequest().getSession().getAttribute("vip_id");
        if (isNullOrEmptyString(vip_id)) {
            // String back = "/pages/HuoShop/createGroup.do?opCode=0003";
            // //登录成功后跳转的页面
            back = URLEncoder.encode(back);
            return new JsonBean("-1", "操作失败", "请登录后再操作", "/Vip/login.do?back="
                    + back);
        } else
            return new JsonBean("0", "操作成功", "", vip_id);
    }

    /**
     * @param base64Str base64图片字符串
     * @return
     */
    public String uploadImgOSS(String base64Str, boolean genOther) {
        if (isNullOrEmptyString(base64Str))
            return null;
        return uploadImgOSS(base64Str, null, null, genOther, false, null);
    }

    /**
     * @param base64Str      base64 文件名
     * @param uploadFileName 上传文件名
     * @param watermark      水印
     * @param genOther       生成多规格
     * @param addWhite       白底
     * @param invitationCode 公司编码
     * @return 访问网址 如 http://img.wealthlake.cn/invitationCode/20131211/0012.jpg
     */
    public String uploadImgOSS(String base64Str, String uploadFileName,
                               String watermark, boolean genOther, boolean addWhite,
                               String invitationCode) {
        IdWorker idWorker = (IdWorker) SpringContextUtil.getBean("idWorker");
        File upload = null;
        base64Str = base64Str.replace("data:image/jpeg;base64,", "").replace(
                "data:image/png;base64,", "");
        log.info("BASE64 解析开始");
        try {
            String user_home = System.getProperty("user.home");
            String filePath = "upload";
            String realPath = user_home + File.separator + filePath
                    + File.separator + "nhshc";

            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdir();
            }
            String ts = idWorker.nextIdStr() + ".jpg";
            upload = new File(realPath, ts);
            Base64Image.GenerateImage(base64Str, upload.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("BASE64 解析结束");
        log.info("上传图片开始...");
        OSSClient client = (OSSClient) SpringContextUtil.getBean("oSSClient");
        String bucket = "hyjj-pr-img";
        // boolean exists = client.doesBucketExist(bucket);
        // 输出结果
        // if (exists) {
        // System.out.println("Bucket exists");
        // } else {
        // System.out.println("Bucket not exists");
        // }
        String key = idWorker.nextIdStr();
        String folder = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        if (invitationCode != null) {
            folder = invitationCode + "/" + folder;
        }
        try {
            int width = 800;
            int height = 800;
            int watermark_w = (int) (1.0f * width / 800 * 224);
            int watermark_h = (int) (1.0f * height / 800 * 224);
            // 原图
            BufferedImage sourceBufferImage = Thumbnails.of(upload).scale(1)
                    .asBufferedImage();
            // 目标最大图
            Builder<BufferedImage> bm = Thumbnails.of(sourceBufferImage).size(
                    width, height);
            if (addWhite) {
                if (sourceBufferImage.getWidth() != sourceBufferImage
                        .getHeight()) {
                    BufferedImage w = new BufferedImage(width, height,
                            BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = w.createGraphics();
                    g.setColor(Color.white);
                    g.fillRect(0, 0, width, height);
                    g.dispose();
                    bm = Thumbnails
                            .of(w)
                            .size(width, height)
                            .watermark(Positions.CENTER, bm.asBufferedImage(),
                                    1.0f);
                }
            }

            BufferedImage destBufferImage = null;
            if (watermark != null && (!"".equals(watermark))) {
                // 加水印
                Resource resource = SpringContextUtil.getApplicationContext()
                        .getResource("classpath:" + watermark);
                BufferedImage wm = Thumbnails.of(resource.getInputStream())
                        .size(watermark_w, watermark_h).asBufferedImage();
                destBufferImage = bm
                        .watermark(Positions.BOTTOM_RIGHT, wm, 0.9f)
                        .asBufferedImage();
                wm = null;
            } else {
                // 不加水印
                destBufferImage = bm.asBufferedImage();
            }
            // 30 60 120 220 360 500 800
            int[] size = {0, 800, 500, 360, 220, 120, 60, 30};
            for (int i = 0; i < size.length; i++) {
                if ((!genOther) && (i > 1))
                    continue;
                InputStream content = null;
                width = size[i];
                height = size[i];
                BufferedImage bmgTmp = null;
                String k = key + ".jpg";
                if (width == 0) {
                    FileInputStream fis = new FileInputStream(upload);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(upload.length());
                    meta.setContentType("image/jpeg");
                    PutObjectResult result = client.putObject(bucket, folder
                            + "/" + k, fis, meta);
                } else {
                    // bmgTmp = Thumbnails.of(destBufferImage).size(width,
                    // height).outputQuality(0.9f).asBufferedImage();
                    bmgTmp = Thumbnails.of(destBufferImage).size(width, height)
                            .asBufferedImage();
                    k = k + "_" + width + "x" + height + "q90.jpg";
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bmgTmp, "jpeg", baos);
                    byte[] b = baos.toByteArray();
                    content = new ByteArrayInputStream(b);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(baos.size());
                    meta.setContentType("image/jpeg");
                    PutObjectResult result = client.putObject(bucket, folder
                            + "/" + k, content, meta);
                }
                bmgTmp = null;
            }
            bm = null;
            destBufferImage = null;
            sourceBufferImage = null;
            log.info("上传图片结束...");
            // System.out.println(result.getETag());

            String imgUrl = "http://" + bucket + "."
                    + client.getEndpoint().toString().replace("http://", "")
                    + "/" + folder + "/" + key + ".jpg";

            JsonBean json = syncImageDetectionSample.imageDetection(imgUrl);
            log.info("image detection result=============" + JsonUtil.toJSon(json));
            if (!"0".equals(json.getRetCode())) return null;
            return imgUrl;
            // URL url = client.generatePresignedUrl(bucket,key+".jpg",new Date
            // (new Date().getTime() + 1000*60*60*24));
            // System.out.println(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件到硬盘
     *
     * @param fileItem 文件
     * @return
     */
    public UploadFileOssResult uploadFile(FileItem fileItem) {
        String user_home = System.getProperty("user.home");
        String filePath = "upload";
        String realPath = user_home + File.separator + filePath;
        return uploadFile(fileItem, realPath);
    }

    /**
     * 上传文件到硬盘
     *
     * @param fileItem 文件
     * @param filePath 保存路径
     * @return
     */
    public UploadFileOssResult uploadFile(FileItem fileItem, String filePath) {
        if (isNullOrEmptyString(fileItem))
            return null;
        if (isNullOrEmptyString(filePath))
            return null;

        File dirFilePath = new File(filePath);
        if (!dirFilePath.exists())
            dirFilePath.mkdirs();

        InputStream in = null;
        FileOutputStream out = null;
        UploadFileOssResult uploadFileOssResult = null;
        try {
            String fileType = "txt";
            // 获得文件上传字段中的文件名
            String name = fileItem.getName();
            if (!isNullOrEmptyString(name)) {
                fileType = name.substring(name.lastIndexOf(".") + 1);
            }

            String fileNmae = RandomUtil.randomNum(10) + "." + fileType;
            out = new FileOutputStream(new File(filePath, fileNmae));
            // 获取指定文件的输入流
            in = fileItem.getInputStream();
            byte[] buffer = new byte[2024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            uploadFileOssResult = new UploadFileOssResult();
            uploadFileOssResult.setUrl(filePath + File.separator + fileNmae);
            uploadFileOssResult.setMd5(null);
            uploadFileOssResult.setResult(null);
            uploadFileOssResult.setBucketName(null);
            uploadFileOssResult.setObjectName(fileNmae);
        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return uploadFileOssResult;
    }

    /**
     * 上传文本文件（txt,csv）到硬盘
     *
     * @param fileItem 文件
     * @param filePath 保存路径
     * @param charset  编码 默认UTF-8
     * @return
     */
    public UploadFileOssResult uploadFileText(FileItem fileItem,
                                              String filePath, String charset) {
        if (isNullOrEmptyString(fileItem))
            return null;
        if (isNullOrEmptyString(filePath)) {
            String user_home = System.getProperty("user.home");
            filePath = user_home + File.separator + "upload";
        }
        if (isNullOrEmptyString(charset))
            charset = "UTF-8";

        File dirFilePath = new File(filePath);
        if (!dirFilePath.exists())
            dirFilePath.mkdirs();

        BufferedWriter out = null;
        BufferedReader reader = null;
        UploadFileOssResult uploadFileOssResult = null;
        try {
            String fileType = "txt";
            // 获得文件上传字段中的文件名
            String name = fileItem.getName();
            if (!isNullOrEmptyString(name)) {
                fileType = name.substring(name.lastIndexOf(".") + 1);
            }
            String fileNmae = RandomUtil.randomNum(10) + "." + fileType;
            out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(
                            filePath, fileNmae)), charset));

            // 获取指定文件的输入流
            reader = new BufferedReader(new InputStreamReader(
                    fileItem.getInputStream(), "GBK"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                out.write(line);
                out.newLine();
            }
            out.flush();
            uploadFileOssResult = new UploadFileOssResult();
            uploadFileOssResult.setUrl(filePath + File.separator + fileNmae);
            uploadFileOssResult.setMd5(null);
            uploadFileOssResult.setResult(null);
            uploadFileOssResult.setBucketName(null);
            uploadFileOssResult.setObjectName(fileNmae);
        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return uploadFileOssResult;
    }

    /**
     * 上传文件到oss
     *
     * @param
     */
    public UploadFileOssResult uploadFileOSS(String filePath, String fileType) {
        if (isNullOrEmptyString(filePath))
            return null;
        return uploadFileOSS(new File(filePath), fileType);
    }

    /**
     * 上传文件到oss
     *
     * @param
     */
    public UploadFileOssResult uploadFileOSS(File file, String fileType) {
        if (file == null)
            return null;
        InputStream content = null;
        try {
            if (isNullOrEmptyString(fileType)) {
                String fileName = file.getName();
                fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
            // 获取指定文件的输入流
            content = new FileInputStream(file);
            return uploadFileOSS(content, file.length(), fileType);
        } catch (Exception e) {
            log.error("上传文件到oss异常", e);
            return null;
        } finally {
            if (content != null)
                try {
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 上传文件到oss
     *
     * @param fileItem
     */
    public UploadFileOssResult uploadFileOSS(FileItem fileItem) {
        if (fileItem == null)
            return null;
        InputStream content = null;
        try {
            String fileType = "txt";
            // 获得文件上传字段中的文件名
            String name = fileItem.getName();
            if (!isNullOrEmptyString(name)) {
                fileType = name.substring(name.lastIndexOf(".") + 1);
            }
            // 获取指定文件的输入流
            content = fileItem.getInputStream();
            return uploadFileOSS(content, fileItem.getSize(), fileType);
        } catch (IOException e) {
            log.error("上传文件到oss异常", e);
            return null;
        } finally {
            if (content != null)
                try {
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 上传文件到oss
     *
     * @param content
     * @param length
     * @param fileType
     */
    public UploadFileOssResult uploadFileOSS(InputStream content, Long length,
                                             String fileType) {
        if (content == null)
            return null;
        if (isNullOrEmptyString(fileType))
            fileType = "txt";
        try {
            String bucketName = "cttimg";

            IdWorker idWorker = (IdWorker) SpringContextUtil
                    .getBean("idWorker");
            String key = new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                    + "/" + idWorker.nextIdStr();

            // 初始化OSSClient
            OSSClient client = (OSSClient) SpringContextUtil
                    .getBean("oSSClient");
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 必须设置ContentLength
            meta.setContentLength(length);

            // 这里因为后台要对文件流进行md5编码会进行读取流信息，读取完后指针或指向文件流末端，所有需要再创建一个流对象
            byte[] byteArrayFile = IOUtils.toByteArray(content);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    byteArrayFile);

            String md5 = getContentMD5(byteArrayFile);
            meta.setHeader("Content-MD5", md5);

            // 上传Object.
            PutObjectResult result = client.putObject(bucketName, key + "."
                    + fileType, byteArrayInputStream, meta);
            log.info(result.getETag());
            UploadFileOssResult uploadFileOssResult = new UploadFileOssResult();
            uploadFileOssResult.setUrl("http://" + bucketName + "."
                    + client.getEndpoint().toString().replace("http://", "")
                    + "/" + key + "." + fileType);
            uploadFileOssResult.setMd5(md5);
            uploadFileOssResult.setResult(result.getETag());
            uploadFileOssResult.setBucketName(bucketName);
            uploadFileOssResult.setObjectName(key + "." + fileType);
            return uploadFileOssResult;

        } catch (Exception e) {
            log.error("上传文件到oss异常", e);
            return null;
        } finally {
            if (content != null)
                try {
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 上传图片到oss
     *
     * @param fileItem
     */
    public UploadFileOssResult uploadImgOSS(FileItem fileItem) {
        return uploadImgOSS(fileItem, true, false);
    }


    /**
     * 上传图片到oss
     *
     * @param fileItem 文件
     * @param genOther 是否生成多规格
     * @param addWhite 是否增加白底
     * @return
     */
    public UploadFileOssResult uploadImgOSS(FileItem fileItem, boolean genOther, boolean addWhite) {
        if (fileItem == null)
            return null;
        InputStream content = null;
        File upload = null;
        try {
            String fileType = "jpg";
            // 获得文件上传字段中的文件名
            String name = fileItem.getName();
            if (!isNullOrEmptyString(name)) {
                fileType = name.substring(name.lastIndexOf(".") + 1);
            }
            // 获取指定文件的输入流
            content = fileItem.getInputStream();
            if (content == null)
                return null;
            FileOutputStream out = null;
            try {
                IdWorker idWorker = (IdWorker) SpringContextUtil.getBean("idWorker");
                String user_home = System.getProperty("user.home");
                String filePath = "upload";
                String realPath = user_home + File.separator + filePath
                        + File.separator + "ctt";
                File file = new File(realPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String ts = idWorker.nextIdStr() + "." + fileType;
                upload = new File(realPath, ts);

                fileItem.write(upload);
                /*
                 * out = new FileOutputStream(upload); int len = 0; byte[]
                 * buffer = new byte[4090]; while((len =
                 * content.read(buffer))>0){ out.write(buffer, 0, len);; }
                 */
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null)
                    out.close();
            }
            // 获取指定文件的输入流
            return uploadImgOSS(upload, fileItem.getSize(), fileType, null,
                    genOther, addWhite);

//			return uploadImgOSS(fileItem.getInputStream(), fileItem.getSize(), fileType, null,
//					true, false);
        } catch (IOException e) {
            log.error("上传图片到oss异常", e);
            return null;
        } finally {
            if (content != null)
                try {
                    if (upload.exists()) {
                        upload.delete();
                        log.info("删除图片成功...");
                    }
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    /**
     * 上传图片到oss
     *
     * @param upload
     * @param length
     * @param fileType
     * @param watermark 水印
     * @param genOther  生成多规格
     * @param addWhite  白底
     */
    public UploadFileOssResult uploadImgOSS(InputStream upload, Long length,
                                            String fileType, String watermark, boolean genOther,
                                            boolean addWhite) {
        if (upload == null)
            return null;
        if (isNullOrEmptyString(fileType))
            fileType = "jpg";
        InputStream content = null;
        try {
            String bucketName = "cttimg";
            // 初始化OSSClient
            OSSClient client = (OSSClient) SpringContextUtil
                    .getBean("oSSClient");
            IdWorker idWorker = (IdWorker) SpringContextUtil
                    .getBean("idWorker");
            String key = new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                    + "/" + idWorker.nextIdStr();

            int width = 800;
            int height = 800;
            int watermark_w = (int) (1.0f * width / 800 * 224);
            int watermark_h = (int) (1.0f * height / 800 * 224);

            // 原图
            BufferedImage sourceBufferImage = Thumbnails.of(upload).scale(1)
                    .asBufferedImage();
            // 目标最大图
            Builder<BufferedImage> bm = Thumbnails.of(sourceBufferImage).size(
                    width, height);
            if (addWhite) {
                if (sourceBufferImage.getWidth() != sourceBufferImage
                        .getHeight()) {
                    BufferedImage w = new BufferedImage(width, height,
                            BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = w.createGraphics();
                    g.setColor(Color.white);
                    g.fillRect(0, 0, width, height);
                    g.dispose();
                    bm = Thumbnails
                            .of(w)
                            .size(width, height)
                            .watermark(Positions.CENTER, bm.asBufferedImage(),
                                    1.0f);
                }
            }

            BufferedImage destBufferImage = null;
            if (watermark != null && (!"".equals(watermark))) {
                // 加水印
                Resource resource = SpringContextUtil.getApplicationContext()
                        .getResource("classpath:" + watermark);
                BufferedImage wm = Thumbnails.of(resource.getInputStream())
                        .size(watermark_w, watermark_h).asBufferedImage();
                destBufferImage = bm
                        .watermark(Positions.BOTTOM_RIGHT, wm, 0.9f)
                        .asBufferedImage();
                wm = null;
            } else {
                // 不加水印
                destBufferImage = bm.asBufferedImage();
            }
            String contentType = "image/jpeg";
            if ("png".equals(fileType))
                contentType = "image/png";
            else if ("gif".equals(fileType))
                contentType = "image/gif";

            // 30 60 120 220 360 500 800
            int[] size = {0, 800, 500, 360, 220, 120, 60, 30};
            for (int i = 0; i < size.length; i++) {
                if ((!genOther) && (i > 1))
                    continue;
                width = size[i];
                height = size[i];
                BufferedImage bmgTmp = null;
                String k = key + "." + fileType;
                if (width == 0) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(sourceBufferImage, fileType, baos);
                    byte[] b = baos.toByteArray();
                    content = new ByteArrayInputStream(b);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(length);
                    meta.setContentType(contentType);
                    PutObjectResult result = client.putObject(bucketName, k,
                            content, meta);
                    log.info(result.getETag());
                } else {
                    // bmgTmp = Thumbnails.of(destBufferImage).size(width,
                    // height).outputQuality(0.9f).asBufferedImage();
                    bmgTmp = Thumbnails.of(destBufferImage).size(width, height)
                            .asBufferedImage();
                    k = k + "_" + width + "x" + height + "q90." + fileType;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bmgTmp, fileType, baos);
                    byte[] b = baos.toByteArray();
                    content = new ByteArrayInputStream(b);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(baos.size());
                    meta.setContentType(contentType);
                    PutObjectResult result = client.putObject(bucketName, k,
                            content, meta);
                    log.info(result.getETag());
                }
                bmgTmp = null;
            }
            bm = null;
            destBufferImage = null;
            sourceBufferImage = null;
            log.info("上传图片结束...");
            UploadFileOssResult uploadFileOssResult = new UploadFileOssResult();
            uploadFileOssResult.setUrl("http://" + bucketName + "."
                    + client.getEndpoint().toString().replace("http://", "")
                    + "/" + key + "." + fileType);
            uploadFileOssResult.setMd5(null);
            uploadFileOssResult.setResult(null);
            uploadFileOssResult.setBucketName(bucketName);
            uploadFileOssResult.setObjectName(key);

            JsonBean json = syncImageDetectionSample.imageDetection(uploadFileOssResult.getUrl());
            log.info("image detection result=============" + JsonUtil.toJSon(json));

            uploadFileOssResult.setJson(json);

            return uploadFileOssResult;

        } catch (Exception e) {
            log.error("上传图片到oss异常", e);
            return null;
        } finally {
            if (content != null)
                try {
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public UploadFileOssResult uploadImgOSS(File upload, Long length, String fileType, String watermark, boolean genOther,
                                            boolean addWhite, String key) {
        if (upload == null)
            return null;
        if (isNullOrEmptyString(fileType))
            fileType = "jpg";
        InputStream content = null;
        try {
            String bucketName = "cttimg";
            // 初始化OSSClient
            OSSClient client = (OSSClient) SpringContextUtil
                    .getBean("oSSClient");
//			IdWorker idWorker = (IdWorker) SpringContextUtil
//					.getBean("idWorker");
//			String key = new SimpleDateFormat("yyyy/MM/dd").format(new Date())
//					+ "/" + idWorker.nextIdStr();

            int width = 800;
            int height = 800;
            int watermark_w = (int) (1.0f * width / 800 * 224);
            int watermark_h = (int) (1.0f * height / 800 * 224);
            // 原图
            BufferedImage sourceBufferImage = Thumbnails.of(upload).scale(1)
                    .asBufferedImage();
            // 目标最大图
            Builder<BufferedImage> bm = Thumbnails.of(sourceBufferImage).size(
                    width, height);
            if (addWhite) {
                if (sourceBufferImage.getWidth() != sourceBufferImage
                        .getHeight()) {
                    BufferedImage w = new BufferedImage(width, height,
                            BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = w.createGraphics();
                    g.setColor(Color.white);
                    g.fillRect(0, 0, width, height);
                    g.dispose();
                    bm = Thumbnails
                            .of(w)
                            .size(width, height)
                            .watermark(Positions.CENTER, bm.asBufferedImage(),
                                    1.0f);
                }
            }

            BufferedImage destBufferImage = null;
            if (watermark != null && (!"".equals(watermark))) {
                // 加水印
                Resource resource = SpringContextUtil.getApplicationContext()
                        .getResource("classpath:" + watermark);
                BufferedImage wm = Thumbnails.of(resource.getInputStream())
                        .size(watermark_w, watermark_h).asBufferedImage();
                destBufferImage = bm
                        .watermark(Positions.BOTTOM_RIGHT, wm, 0.9f)
                        .asBufferedImage();
                wm = null;
            } else {
                // 不加水印
                destBufferImage = bm.asBufferedImage();
            }

            String contentType = "image/jpeg";
            if ("png".equals(fileType))
                contentType = "image/png";
            else if ("gif".equals(fileType))
                contentType = "image/gif";
            // 30 60 120 220 360 500 800
            int[] size = {0, 800, 500, 360, 220, 120, 60, 30};
            for (int i = 0; i < size.length; i++) {
                if ((!genOther) && (i > 1))
                    continue;
                width = size[i];
                height = size[i];
                BufferedImage bmgTmp = null;
                String k = key + "." + fileType;
                if (width == 0) {
                    FileInputStream fis = new FileInputStream(upload);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(upload.length());
                    meta.setContentType(contentType);
                    PutObjectResult result = client.putObject(bucketName, k,
                            fis, meta);
                    log.info(result.getETag());
                } else {
                    // bmgTmp = Thumbnails.of(destBufferImage).size(width,
                    // height).outputQuality(0.9f).asBufferedImage();
                    bmgTmp = Thumbnails.of(destBufferImage).size(width, height)
                            .asBufferedImage();
                    k = k + "_" + width + "x" + height + "q90." + fileType;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bmgTmp, fileType, baos);
                    byte[] b = baos.toByteArray();
                    content = new ByteArrayInputStream(b);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(baos.size());
                    meta.setContentType(contentType);
                    PutObjectResult result = client.putObject(bucketName, k,
                            content, meta);
                    log.info(result.getETag());
                }
                bmgTmp = null;
            }
            bm = null;
            destBufferImage = null;
            sourceBufferImage = null;
            log.info("上传图片结束...");
            UploadFileOssResult uploadFileOssResult = new UploadFileOssResult();
            uploadFileOssResult.setUrl("http://" + bucketName + "."
                    + client.getEndpoint().toString().replace("http://", "")
                    + "/" + key + "." + fileType);
            uploadFileOssResult.setMd5(null);
            uploadFileOssResult.setResult(null);
            uploadFileOssResult.setBucketName(bucketName);
            uploadFileOssResult.setObjectName(key);

            JsonBean json = syncImageDetectionSample.imageDetection(uploadFileOssResult.getUrl());
            log.info("image detection result=============" + JsonUtil.toJSon(json));

            uploadFileOssResult.setJson(json);

            return uploadFileOssResult;

        } catch (Exception e) {
            log.error("上传图片到oss异常", e);
            return null;
        } finally {
            if (content != null)
                try {
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static byte[] getImageFromNetByUrl(String strUrl) throws IOException {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据  
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File writeImageToDisk(byte[] img, String fileName) throws IOException {
        String filePath = "upload";
        String user_home = System.getProperty("user.home");
        //保存文件路径
        String savePath = user_home + "\\" + filePath + "\\";//保存路径
        fileName = fileName.replaceAll("/", "_");
        FileOutputStream fops = null;
        File f = null;
        try {
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            f = new File(file.getPath() + "\\" + fileName);
            f.createNewFile();
            fops = new FileOutputStream(f);
            fops.write(img);
            fops.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fops.close();
        }
        return f;
    }

    /**
     * 下载远程图片
     *
     * @throws Exception
     */
    public File downLoadRemoteImage(String path) throws Exception {
        String filename = path.substring(path.indexOf("com") + 4);
        byte[] btImg = getImageFromNetByUrl(path);
        if (null != btImg && btImg.length > 0) {
            return writeImageToDisk(btImg, filename);
        }
        return null;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 上传图片到oss
     *
     * @param upload
     * @param length
     * @param fileType
     * @param watermark 水印
     * @param genOther  生成多规格
     * @param addWhite  白底
     */
    public UploadFileOssResult uploadImgOSS(File upload, Long length,
                                            String fileType, String watermark, boolean genOther,
                                            boolean addWhite) {
        if (upload == null)
            return null;
        if (isNullOrEmptyString(fileType))
            fileType = "jpg";
        InputStream content = null;
        try {
            String bucketName = "cttimg";
            // 初始化OSSClient
            OSSClient client = (OSSClient) SpringContextUtil
                    .getBean("oSSClient");
            IdWorker idWorker = (IdWorker) SpringContextUtil
                    .getBean("idWorker");
            String key = new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                    + "/" + idWorker.nextIdStr();

            int width = 800;
            int height = 800;
            int watermark_w = (int) (1.0f * width / 800 * 224);
            int watermark_h = (int) (1.0f * height / 800 * 224);
            // 原图
            BufferedImage sourceBufferImage = Thumbnails.of(upload).scale(1)
                    .asBufferedImage();
            // 目标最大图
            Builder<BufferedImage> bm = Thumbnails.of(sourceBufferImage).size(
                    width, height);
            if (addWhite) {
                if (sourceBufferImage.getWidth() != sourceBufferImage
                        .getHeight()) {
                    BufferedImage w = new BufferedImage(width, height,
                            BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = w.createGraphics();
                    g.setColor(Color.white);
                    g.fillRect(0, 0, width, height);
                    g.dispose();
                    bm = Thumbnails
                            .of(w)
                            .size(width, height)
                            .watermark(Positions.CENTER, bm.asBufferedImage(),
                                    1.0f);
                }
            }

            BufferedImage destBufferImage = null;
            if (watermark != null && (!"".equals(watermark))) {
                // 加水印
                Resource resource = SpringContextUtil.getApplicationContext()
                        .getResource("classpath:" + watermark);
                BufferedImage wm = Thumbnails.of(resource.getInputStream())
                        .size(watermark_w, watermark_h).asBufferedImage();
                destBufferImage = bm
                        .watermark(Positions.BOTTOM_RIGHT, wm, 0.9f)
                        .asBufferedImage();
                wm = null;
            } else {
                // 不加水印
                destBufferImage = bm.asBufferedImage();
            }
            //文件content_type
            String contentType = "image/jpeg";
            if ("png".equals(fileType))
                contentType = "image/png";
            else if ("gif".equals(fileType))
                contentType = "image/gif";

            // 30 60 120 220 360 500 800
            int[] size = {0, 800, 500, 360, 220, 120, 60, 30};
            for (int i = 0; i < size.length; i++) {
                if ((!genOther) && (i > 0))
                    continue;
                width = size[i];
                height = size[i];
                BufferedImage bmgTmp = null;
                String k = key + "." + fileType;
                if (width == 0) {
                    FileInputStream fis = new FileInputStream(upload);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(upload.length());
                    meta.setContentType(contentType);
                    PutObjectResult result = client.putObject(bucketName, k,
                            fis, meta);
                    log.info(result.getETag());
                } else {
                    // bmgTmp = Thumbnails.of(destBufferImage).size(width,
                    // height).outputQuality(0.9f).asBufferedImage();
                    bmgTmp = Thumbnails.of(destBufferImage).size(width, height)
                            .asBufferedImage();
                    k = k + "_" + width + "x" + height + "q90." + fileType;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bmgTmp, fileType, baos);
                    byte[] b = baos.toByteArray();
                    content = new ByteArrayInputStream(b);
                    ObjectMetadata meta = new ObjectMetadata();
                    meta.setContentLength(baos.size());
                    meta.setContentType(contentType);
                    PutObjectResult result = client.putObject(bucketName, k,
                            content, meta);
                    log.info(result.getETag());
                }
                bmgTmp = null;
            }
            bm = null;
            destBufferImage = null;
            sourceBufferImage = null;
            log.info("上传图片结束...");
            UploadFileOssResult uploadFileOssResult = new UploadFileOssResult();
            uploadFileOssResult.setUrl("http://" + bucketName + "."
                    + client.getEndpoint().toString().replace("http://", "")
                    + "/" + key + "." + fileType);
            uploadFileOssResult.setMd5(null);
            uploadFileOssResult.setResult(null);
            uploadFileOssResult.setBucketName(bucketName);
            uploadFileOssResult.setObjectName(key);
            //图片鉴黄
            if (isCheckImg) {
                JsonBean json = syncImageDetectionSample.imageDetection(uploadFileOssResult.getUrl());
                uploadFileOssResult.setJson(json);
                log.info("image detection result=============" + JsonUtil.toJSon(json));
            } else {
                uploadFileOssResult.setJson(new JsonBean("0", "操作成功", "上传成功", uploadFileOssResult.getUrl()));
                log.info("image detection result=============" + JsonUtil.toJSon(uploadFileOssResult.getJson()));
            }

            return uploadFileOssResult;

        } catch (Exception e) {
            log.error("上传图片到oss异常", e);
            return null;
        } finally {
            if (content != null)
                try {
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 将生成二维码上传至阿里云
     *
     * @throws IOException
     * @throws DataAccessException
     */
    public UploadFileOssResult uploadTwoDimensionCode(BufferedImage encoderQRCodeBybuffer) throws IOException {
        //BufferedImage 转 InputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
        ImageIO.write(encoderQRCodeBybuffer, "png", imageOutput);
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        long length = imageOutput.length();
        UploadFileOssResult uploadFileOssResult = uploadImgOSS(inputStream, length, "png", "", false, false);
        return uploadFileOssResult;
    }

    /**
     * 获取文件MD5
     *
     * @param binaryData
     * @return
     * @throws Exception
     */
    private static String getContentMD5(byte[] binaryData) throws Exception {
        binaryData = BinaryUtil.calculateMd5(binaryData);

        return BinaryUtil.toBase64String(binaryData);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期 以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name,
                                 String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0)
            cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param name    cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 更新cookie
     *
     * @param request
     * @param response
     * @param name
     * @param value
     * @return
     */
    public static void updateCookieByName(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        Cookie cookie = getCookieByName(request, name);
        if (cookie == null) {
            addCookie(response, name, value, COOKIE_AGE);
        } else {
            delCookie(response, name);
            addCookie(response, name, value, COOKIE_AGE);
        }
    }

    public static String getCookieValueByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 清理cookie
     *
     * @param response
     * @param name     cookie名字
     */
    public static void delCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public String getOpCode() {
        return request.getParameter("opCode");
    }

    public String getOpName() {
        return request.getParameter("opName");
    }


    public String getLoginType() {
        Object loginType = SecurityUtils.getSubject().getSession().getAttribute("loginType");
        if (loginType != null) {
            log.info("loginType==========" + loginType);
            return loginType + "";
        }
        return "";
    }


    /**
     * API接口校验
     *
     * @param verFlag 是否进行版本检查
     * @param des3Key
     * @param des3Iv
     * @return JsonBean
     */
    public JsonBean checkApi(boolean verFlag, String des3Key, String des3Iv) {
        // 1.校验client_id
        String client_id = getRequest().getParameter("client_id");
        String sign = getRequest().getParameter("sign");
        String timestamp = getRequest().getParameter("timestamp");
        //String sign_type = getRequest().getParameter("sign_type");
        String data = getRequest().getParameter("data");
        String dev = getRequest().getParameter("dev");
        String versionCode = getRequest().getParameter("versionCode");
        if (verFlag) {
            if (isNullOrEmptyString(versionCode)) {
                return new JsonBean("10005", "版本过低", "版本过低" + versionCode, null);
            }
            if (dev != null && dev.contains("android")) {// 安卓
                if (Integer.parseInt(versionCode) < 256) {
                    return new JsonBean("10005", "版本过低", "版本过低" + versionCode, null);
                }
            } else {//IOS
                if (Integer.parseInt(versionCode) < 50) {
                    return new JsonBean("10005", "版本过低", "版本过低" + versionCode, null);
                }
            }
        }
        OauthClientDetailsManager clientManager = (OauthClientDetailsManager) SpringContextUtil
                .getBean("oauthClientDetailsManager");
        IdWorker idWorker = (IdWorker) SpringContextUtil.getBean("idWorker");
        long reqId = idWorker.nextId();
        SystemLog systemLog = new SystemLog();
        systemLog.setRequestId(reqId + "");
        systemLog.setType(0);//fc
        String uid = getRequest().getParameter("uid");
        if ((uid != null) && (!uid.contains("null"))) {
            systemLog.setUid(uid);
        }
        systemLog.setChannel(0);//fc
        systemLog.setId(reqId);
        systemLog.setYm(DateConvertUtils.convertyyMM());
        systemLog.setProvince(getRequest().getParameter("provinceName"));
        systemLog.setCity(getRequest().getParameter("city"));
        systemLog.setDistrict(getRequest().getParameter("district"));
        systemLog.setOsType(getRequest().getParameter("osType"));
        systemLog.setCreateTime(DateConvertUtils.nowDateTime());
        systemLog.setRequestType("POST");
        systemLog.setIp(getRemoteAddress(getRequest()));
        systemLog.setRequestParam(JsonUtil.toJSon(getRequest().getParameterMap()));
        systemLog.setRequestUri(getRequest().getRequestURI());
        systemLog.setRequestAgent(getRequest().getHeader("user-agent"));
        systemLog.setAppVersion(getRequest().getParameter("version"));
        OauthClientDetails details = clientManager.getById(client_id);
        if (details == null) {
            systemLog.setRequestResult("client_id不存在");
            return new JsonBean("10002", "请求失败", "client_id不存在", null);
        }
        // 2.校验数据md5
        String checksign = MD5Util.MD5Encode(data + details.getClientSecret()
                + timestamp, "utf-8");
        if (!checksign.equals(sign.toLowerCase())) {
            systemLog.setRequestResult("签名校验失败");
            return new JsonBean("10003", "请求失败", "签名校验失败", null);
        }
        data = checkDataPassed(data, des3Key, des3Iv);
        if (data == null) {
            systemLog.setRequestResult("data参数解析失败");
            return new JsonBean("10004", "请求失败", "data参数解析失败", null);
        }
        systemLog.setRequestResult(data);
        return new JsonBean("0", "请求成功", null, "");
    }

    /**
     * 判断data参数是否加密，如果加密则对data参数进行解密并放入request.attribute中,
     * 通过getRequest().getAttribute("data")获取.
     *
     * @param data
     * @return
     */
    public String checkDataPassed(String data, String des3Key, String des3Iv) {
        String passed = getRequest().getParameter("passed");//data参数是否加密，1：加密
        if ("1".equals(passed)) {
            try {
                data = Des3.des3DecodeCBC(des3Key, des3Iv, data);
                log.info("data>" + data);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("data参数解密异常：", e);
                return null;
            }
        }
        getRequest().setAttribute("data", data);
        return data;
    }

    public static void checkDataPassedTest(String data) {
        try {
            data = Des3.des3DecodeCBC("CfthOpenApi@haha#Encrytp", "19283127", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(data);
    }


    /**
     * 请求参数md5值
     *
     * @param data      请求参数json字符串
     * @param timestamp 时间戳
     * @param clientId  客户端ID
     * @return
     */
    public String sign(String data, String timestamp, String clientId) {
        if (clientId == null)
            return null;
        OauthClientDetailsManager clientManager = (OauthClientDetailsManager) SpringContextUtil
                .getBean("oauthClientDetailsManager");
        OauthClientDetails details = clientManager.getById(clientId);
        if (details == null) {
            return null;
        }
        return MD5Util.MD5Encode(data + details.getClientSecret() + timestamp, "utf-8");
    }


    /**
     * 获取请求data json字符串参数
     *
     * @return
     */
    public Map<String, Object> getDataParam(String des3Key, String des3Iv) {
        String data = getRequest().getParameter("data");
        if (data == null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //解密
            data = checkDataPassed(data, des3Key, des3Iv);
            if (data == null)
                return null;
            JSONObject jsonObject = JSONObject.parseObject(data);
            if (jsonObject == null)
                return null;

            for (String key : jsonObject.keySet()) {
                map.put(key, jsonObject.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    /**
     * 获取请求data json字符串参数
     *
     * @return
     */
    public Map<String, String> getDataParamString(String des3Key, String des3Iv) {
        String data = getRequest().getParameter("data");
        if (data == null)
            return null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            //解密
            data = checkDataPassed(data, des3Key, des3Iv);
            if (data == null)
                return null;
            JSONObject jsonObject = JSONObject.parseObject(data);
            if (jsonObject == null)
                return null;

            for (String key : jsonObject.keySet()) {
                map.put(key, jsonObject.get(key) == null ? null : jsonObject.get(key).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    /**
     * 获取cookie中的数据
     *
     * @return
     */
    public Map<String, String> getCookie2Map() {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        try {
            if (cookieMap != null) {
                for (Entry<String, Cookie> e : cookieMap.entrySet()) {
                    map.put(e.getKey(), e.getValue() == null ? null : e.getValue().getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public Map<String, String> getParam() {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> paramMap = request.getParameterMap();
        try {
            if (paramMap != null) {
                for (Entry<String, String[]> e : paramMap.entrySet()) {
                    if (e.getValue() == null || e.getValue().length == 0) {
                        map.put(e.getKey(), null);
                    } else if (e.getValue().length == 1) {
                        map.put(e.getKey(), e.getValue()[0]);
                    } else {
                        String[] values = e.getValue();
                        String value = "";
                        for (int i = 0; i < values.length; i++) {
                            value = values[i] + ",";
                        }
                        value = value.substring(0, value.length() - 1);
                        map.put(e.getKey(), value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * sku定位使用
     *
     * @param map optionId1#optionId2=>standard=><standardId,optionId>
     * @return indexop1#indexop1#indexop1#
     */
    public static String sortMap(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                list.add(entry.getKey());
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(map.get(arrayToSort[i]) + "#");
        }
        return sb.toString();
    }


    /**
     * 请求参数排序并拼接成  KeyAValueAKeyBValueB
     *
     * @param map 请求参数map
     * @return
     */
    public static String sort(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Entry<String, String> entry : map.entrySet()) {
            if ("sign".equals(entry.getKey())) continue;
            if (entry.getValue() != null) {
                list.add(entry.getKey() + entry.getValue());
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            //if(i == 0) {
            sb.append(arrayToSort[i]);
            //} else {
            //	sb.append("&" + arrayToSort[i]);
            //}
        }
        return sb.toString();
    }

    public void doExportCommon() {
        String className = this.getClass().getSimpleName();
        String managerClassName = StringUtil.toLowerCaseFirstOne(className).concat("Manager");
        Object manager = SpringContextUtil.getBean(managerClassName);
        if (manager == null) {

        }
    }

    /**
     * 获取访问地址
     *
     * @return
     */
    public String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 获取访问ip
     *
     * @return
     */
    public String getRequestIp() {
        return getRemoteAddress(getRequest());
    }


    public String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * 回去当前用户id，首先在请求参数取uid，为空再到seesion中取，都为空返回-1游客
     *
     * @return
     */
    public String getUid() {
        String uid = getRequest().getParameter("uid");
        if (uid == null) {
            uid = (String) getRequest().getSession().getAttribute("uid");
        }
        if (uid == null) {
            uid = "-1";
        }
        return uid;
    }


    public JsonBean checkReqMethod() {
        if (!POST.equalsIgnoreCase(getRequest().getMethod())) {
            return new JsonBean("2", "操作失败", "非法的请求", "");
        }
        return new JsonBean("0", "操作成功", "操作成功", "");
    }


    /*
     * convert JsonBean  to Map<String,Object>
     *
     **/
    public Map<String, Object> convertJsonBeanToMap(JsonBean json) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (json != null) {
            map.put("info", (Map<String, Object>) json.getInfo() == null ? "" : (Map<String, Object>) json.getInfo());
            map.put("retCode", json.getRetCode() == null ? "" : json.getRetCode());
            map.put("retMsg", json.getRetMsg() == null ? "" : json.getRetMsg());
            map.put("DetailMsg", json.getDetailMsg() == null ? "" : json.getDetailMsg());

        }
        return map;
    }

}
