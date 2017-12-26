package com.genaral.sequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RandomUtil {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
            "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public static Logger log = LoggerFactory.getLogger(RandomUtil.class);

    public static String generateShortUuid(IdWorker idWorker) {
        StringBuilder sb = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Random random = new Random();
        int a = random.nextInt(8);
        String temp = idWorker.nextId() + "";
        int o = Integer.parseInt(temp.substring(12, 15));
        int p = Integer.parseInt(temp.substring(15, 18));
        sb.append(chars[random.nextInt(50)]);
        for (int i = 0; i < 8; i++) {
            if (i == a) {
                sb.append(chars[o % 0x3A]);
                sb.append(chars[p % 0x3A]);
            }
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            sb.append(chars[x % 0x3A]);
        }
        sb.append(chars[random.nextInt(50)]);
        return sb.toString();
    }

    /**
     * 生成指定位随机数字
     *
     * @param length 最大10位
     * @return
     */
    public static String randomNum(int length) {
        String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        length = length > 10 ? 10 : length;
        return afterShuffle.substring(0, length);
    }

    /**
     * 生成指定位数数字加字母随机字符串
     *
     * @param length 最大62位
     * @return
     */
    public static String randomCode(int length) {
        String[] beforeShuffle = new String[]{"A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "0"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        length = length > 36 ? 36 : length;
        return afterShuffle.substring(0, length);
    }
}
