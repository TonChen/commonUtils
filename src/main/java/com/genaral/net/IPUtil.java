package com.genaral.net;


/**
 * 根据IP获取地址
 *
 * @author ThinkPad User
 */
public class IPUtil {

    /**
     * ip地址转成整数.
     *
     * @param ip
     * @return
     */
    public static long ip2long(String ip) {
        if (ip == null) return 0;
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "192.168.1.1";
        }
        String[] ips = ip.split("[.]");
        long num = 16777216L * Long.parseLong(ips[0]) + 65536L * Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2]) + Long.parseLong(ips[3]);
        return num;
    }

    /**
     * 整数转成ip地址.
     *
     * @param ipLong
     * @return
     */
    public static String long2ip(long ipLong) {
        //long ipLong = 1037591503;   
        long mask[] = {0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000};
        long num = 0;
        StringBuffer ipInfo = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            num = (ipLong & mask[i]) >> (i * 8);
            if (i > 0) ipInfo.insert(0, ".");
            ipInfo.insert(0, Long.toString(num, 10));
        }
        return ipInfo.toString();
    }


    public static void main(String[] orgs) {
        System.out.println(IPUtil.long2ip(976688596));

//		String rs =ScanvUtil.checkSite("www.wealthlake.cn");
//		for(int i=0;i<1000;i++){
//			long i = IPUtil.ip2long("27.159.23.83");
//			System.out.println(i);
////			String ip ="10.196.122.234, 211.143.145.209";
//			String ip ="222.214.202.231";
//			String[] t = ip.split(", ");
//			System.out.println(t[t.length-1]);
//			String rs =IPUtil.checkIp(t[t.length-1]);//-2
//			System.out.println((new Date()).toString()+"rs="+rs+"i="+i);
//			String[] temp = rs.split(" ");
//			System.out.println(temp.length);
//			for (String string : temp) {
//				System.out.println("==="+string); 
//			}
//		}
    }
}
