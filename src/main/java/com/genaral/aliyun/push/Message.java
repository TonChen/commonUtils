package com.genaral.aliyun.push;

/**
 * 阿里云移动推送消息
 * Created by rsh on 16/12/28.
 */
public class Message {

    //推送目标
    public enum Target {
        DEVICE("设备", "DEVICE"),
        ACCOUNT("账户", "ACCOUNT"),
        ALIAS("别名", "ALIAS"),
        TAG("标签", "TAG"),
        ALL("全部", "ALL");

        // 成员变量
        private String name;
        private String value;

        // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
        private Target(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    //设备类型
    public enum DeviceType {
        ALL("全部", "ALL"),
        ANDROID("安卓", "ANDROID"),
        iOS("苹果", "iOS");

        // 成员变量
        private String name;
        private String value;

        // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
        private DeviceType(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
