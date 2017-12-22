package com.genaral.string;

import com.genaral.object.ObjectUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串的常用操作
 */
public class StringUtil {

    /**
     * utf8decode
     *
     * @param str
     * @return
     */
    public static String UTF8Decode(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        String xmString = "";
        String xmlUTF8 = "";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLDecoder.decode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // return to String Formed
        return xmlUTF8;
    }


    /**
     * utf8encode
     *
     * @param str
     * @return
     */
    public static String UTF8Encode(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        String xmString = "";
        String xmlUTF8 = "";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return xmlUTF8;
    }

    /**
     * encode base64
     *
     * @param mingwen
     * @return
     */
    public static String encodeBase64(String mingwen) {
        String code = "";
        if (mingwen == null || mingwen.equals("")) {

        } else {
            BASE64Encoder encoder = new BASE64Encoder();
            try {
                code = encoder.encode(mingwen.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return code;
    }

    /**
     * decode base64
     *
     * @param mi
     * @return
     */
    public static String decodeBase64(String mi) {
        String mingwen = "";
        if (mi == null || mi.equals("")) {

        } else {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                byte[] by = decoder.decodeBuffer(mi);
                mingwen = new String(by);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mingwen;
    }


    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstLetter(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    /**
     * 是否包含特殊字符
     *
     * @param string
     * @return
     */
    public static boolean isConSpeCharacters(String string) {
        if (string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
            return false;
        }
        return true;
    }


    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }

    }


    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (ObjectUtil.isEmpty(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }


    /**
     * 去除字符串中的表情符号
     *
     * @param nick
     * @return
     */
    public static String removeEmojiCharacter(String nick) {
        int l = nick.length();
        StringBuffer nicksb = new StringBuffer();
        for (int i = 0; i < l; i++) {
            char _s = nick.charAt(i);
            if (!isEmojiCharacter(_s)) {
                nicksb.append(_s);
            }
        }
        return nicksb.toString();
    }

    /**
     * 校验电话号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNumber(String mobiles) {
        Pattern p = Pattern.compile("(^0?[1][34758][0-9]{9}$)");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 校验多种电话
     *
     * @param mobiles
     * @return
     */
    public static boolean isMultPhone(String mobiles) {
        Pattern p = Pattern.compile("^((0[1-9]{3})?(0[12][0-9])?[-])?\\d{6,8}$|(^0?[1][34578][0-9]{9}$)");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 检测邮箱地址是否合法
     *
     * @param email
     * @return true合法 false不合法
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 判断是否表情符号
     *
     * @param codePoint
     * @return
     */
    public static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }


    /**
     * 判断是否是正确的数字,长度不能超过11位
     */
    public static boolean isNumber(String num, int length) {
        if (ObjectUtil.isNullOrEmptyString(num)) return false;
        Pattern p = Pattern.compile("^-?[0-9]{1," + length + "}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }


    /**
     * JSON字符串特殊字符处理，比如：“\A1;1300”
     *
     * @param s
     * @return String
     */
    public static String handleJsonStr(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
