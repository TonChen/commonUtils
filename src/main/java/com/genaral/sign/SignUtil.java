package com.genaral.sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class SignUtil {
    /**
     * 请求参数排序并拼接成  key1=123&key2=456，并排除名为sign参数
     *
     * @param map 请求参数map
     * @return
     */
    public static String sortMapToStringSp(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ("sign".equals(entry.getKey())) continue;
            if (entry.getValue() != null) {
                list.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                sb.append(arrayToSort[i]);
            } else {
                sb.append("&" + arrayToSort[i]);
            }
        }
        return sb.toString();
    }


    /**
     * 请求参数排序并拼接成  KeyAValueAKeyBValueB
     *
     * @param map 请求参数map
     * @return
     */
    public static String sortMapToString(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
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
            sb.append(arrayToSort[i]);
        }
        return sb.toString();
    }
}
