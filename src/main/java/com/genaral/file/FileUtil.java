package com.genaral.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;

/**
 * 文件工具类,获取文件编码格式等
 *
 * @author rsh 2016-09-12
 */
public class FileUtil {


    /**
     * 获取文本编码格式<br>
     * java 读取txt如果编码格式不对就会出现乱码格式，通过下边方法获取文本文件编码格式，然后以指定的编码读取文件，就不会出现乱码(简单测试了一下，但是也不保证100%)
     *
     * @param sourceFile
     * @return
     */
    public static String getFilecharset(File sourceFile) {
        try {
            return getFilecharset(new FileInputStream(sourceFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文本编码格式<br>
     * java 读取txt如果编码格式不对就会出现乱码格式，通过下边方法获取文本文件编码格式，然后以指定的编码读取文件，就不会出现乱码(简单测试了一下，但是也不保证100%)
     *
     * @param fileItem
     * @return
     */
    public static String getFilecharset(FileItem fileItem) {
        try {
            return getFilecharset(fileItem.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文本编码格式<br>
     * java 读取txt如果编码格式不对就会出现乱码格式，通过下边方法获取文本文件编码格式，然后以指定的编码读取文件，就不会出现乱码(简单测试了一下，但是也不保证100%)
     *
     * @return
     */
    public static String getFilecharset(InputStream inputStream) {
        if (inputStream == null) return null;
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bis.mark(100);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                charset = "GBK"; // 文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; // 文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; // 文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; // 文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                while ((read = bis.read()) != -1) {
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80 - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }

    /*
     * Java文件操作 获取文件扩展名
     *
     *  Created on: 2011-8-2
     *      Author: blueeagle
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /*
     * Java文件操作 获取不带扩展名的文件名
     *
     *  Created on: 2011-8-2
     *      Author: blueeagle
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }




    public static void main(String[] args) {
        // TODO Auto-generated method stub
        File file = new File("E:\\bach_pushCoupon.csv");
        System.out.println(FileUtil.getFilecharset(file));
    }

}
