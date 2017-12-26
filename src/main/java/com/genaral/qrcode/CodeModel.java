package com.genaral.qrcode;

import java.io.File;

public class CodeModel {
    private String contents;
    private int width;
    private int height;
    private String format = "png";  //图片格式
    private String character_set = "utf-8";
    private int fontSize;  //字体大小
    private File logoFile; //中间的图片 （logo）
    private float logoRatio = 0.20f;  //logo比例
    private String desc;  //底部文字
    private Integer whiteWidth;//白边的宽度  
    private int[] bottomStart;//二维码最下边的开始坐标  
    private int[] bottomEnd;//二维码最下边的结束坐标  

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharacter_set() {
        return character_set;
    }

    public void setCharacter_set(String character_set) {
        this.character_set = character_set;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public File getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(File logoFile) {
        this.logoFile = logoFile;
    }

    public float getLogoRatio() {
        return logoRatio;
    }

    public void setLogoRatio(float logoRatio) {
        this.logoRatio = logoRatio;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getWhiteWidth() {
        return whiteWidth;
    }

    public void setWhiteWidth(Integer whiteWidth) {
        this.whiteWidth = whiteWidth;
    }

    public int[] getBottomStart() {
        return bottomStart;
    }

    public void setBottomStart(int[] bottomStart) {
        this.bottomStart = bottomStart;
    }

    public int[] getBottomEnd() {
        return bottomEnd;
    }

    public void setBottomEnd(int[] bottomEnd) {
        this.bottomEnd = bottomEnd;
    }
}  
