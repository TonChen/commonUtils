package com.genaral.calculate;

import com.genaral.data.NullUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DoubleUtils {

    private static final int DEF_DIV_SCALE = 10;

    /**
     * 两个double比较大小(解决非整数精度问题)
     *
     * @param d1
     * @param d2
     * @return -1:第二个数大，0：一样大，1：第一个数大
     */
    public static int compare(Double d1, Double d2) {
        d1 = NullUtils.handleNull(d1);
        d2 = NullUtils.handleNull(d2);
        BigDecimal val1 = new BigDecimal(d1);
        BigDecimal val2 = new BigDecimal(d2);
        int result = -1;
        // 第二位数大
        if (val1.compareTo(val2) < 0) {
            result = -1;
        }
        // 一样大
        if (val1.compareTo(val2) == 0) {
            result = 0;
        }
        // 第一位数大
        if (val1.compareTo(val2) > 0) {
            result = 1;
        }
        return result;
    }

    /**
     * 两个String比较大小(解决非整数精度问题)
     *
     * @param v1
     * @param v2
     * @return -1:第二个数大，0：一样大，1：第一个数大
     */
    public static int compare(String v1, String v2) {
        v1 = NullUtils.handleNull(v1);
        v2 = NullUtils.handleNull(v2);
        BigDecimal val1 = new BigDecimal(v1);
        BigDecimal val2 = new BigDecimal(v2);
        int result = -1;
        // 第二位数大
        if (val1.compareTo(val2) < 0) {
            result = -1;
        }
        // 一样大
        if (val1.compareTo(val2) == 0) {
            result = 0;
        }
        // 第一位数大
        if (val1.compareTo(val2) > 0) {
            result = 1;
        }
        return result;
    }


    /**
     * 两个Double数相加 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double add(Double v1, Double v2) {
        v1 = NullUtils.handleNull(v1);
        v2 = NullUtils.handleNull(v2);
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.add(b2).doubleValue());
    }


//	public static Double sub(Double v1, Double v2) {
//		v1=NullUtils.handleNull(v1);
//		v2=NullUtils.handleNull(v2);
//		BigDecimal b1 = new BigDecimal(v1.toString());
//		BigDecimal b2 = new BigDecimal(v2.toString());
//		return new Double(b1.subtract(b2).doubleValue());
//	}

    /**
     * 两个Double数相减
     *
     * @param v1 减数
     * @param v  被减数 不定参数
     * @return
     */
    public static Double sub(Double v1, Double... v) {
        v1 = NullUtils.handleNull(v1);
        BigDecimal b1 = new BigDecimal(v1.toString());
        for (Double double1 : v) {
            double1 = NullUtils.handleNull(double1);
            BigDecimal b2 = new BigDecimal(double1.toString());
            Double res = new Double(b1.subtract(b2).doubleValue());
            b1 = new BigDecimal(res);
        }
        return b1.doubleValue();
    }

    /**
     * 两个Double数相乘 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double mul(Double v1, Double v2) {
        v1 = NullUtils.handleNull(v1);
        v2 = NullUtils.handleNull(v2);
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.multiply(b2).doubleValue());
    }

    /**
     * 两个Double数相除 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double div(Double v1, Double v2) {
        if (0d == v2) return 0d;
        v1 = NullUtils.handleNull(v1);
        v2 = NullUtils.handleNull(v2);
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    /**
     * 两个Double数相除，并保留scale位小数 *
     *
     * @param v1    *
     * @param v2    *
     * @param scale *
     * @return Double
     */
    public static Double div(Double v1, Double v2, int scale) {
        v1 = NullUtils.handleNull(v1);
        v2 = NullUtils.handleNull(v2);
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (v2 == 0) {
            throw new IllegalArgumentException("The v2 Can not be equal to 0");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }


    /**
     * 进行四舍五入
     *
     * @param d
     * @param len 保留小数位数
     * @return
     */
    public static Double round(Double d, int len) {
        d = NullUtils.handleNull(d);
        if (d == 0) return 0d;
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量， 表示进行四舍五入的操作
        return new Double(b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue());
    }


    /**
     * 两个Double取模
     *
     * @param v1 *
     * @param v2 *
     * @return int
     */
    public static Double remain(Double v1, Double v2) {
        v1 = NullUtils.handleNull(v1);
        v2 = NullUtils.handleNull(v2);
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.remainder(b2).doubleValue());
    }


    /**
     * Double转String类型，解决科学计数表示
     *
     * @param v1 *
     * @return String
     */
    public static String toString(Double v1) {
        v1 = NullUtils.handleNull(v1);
        if (v1 == null) return null;
        BigDecimal b = new BigDecimal(Double.toString(v1));
        return b.stripTrailingZeros().toPlainString();
    }


    /**
     * double字符串类型转String类型，解决科学计数表示
     *
     * @param v1 *
     * @return String
     */
    public static String toString(String v1) {
        v1 = NullUtils.handleNull(v1);
        if (v1 == null) return null;
        BigDecimal b = new BigDecimal(v1);
        return b.stripTrailingZeros().toPlainString();
    }


    /**
     * Double格式化
     *
     * @param v1
     * @param format “#,##0.00”：123,456,789.00
     * @return String
     */
    public static String format(Double v1, String format) {
        v1 = NullUtils.handleNull(v1);
        if (v1 == null) return null;
        if (format == null) format = "#,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(format);//格式化设置
        return decimalFormat.format(v1);
    }

    /**
     * Double转百分比
     *
     * @param v1
     * @return
     */
    public static String formatPercent(Double v1) {
        v1 = NullUtils.handleNull(v1);
        return formatPercent(v1, 2, 2);
    }

    /**
     * Double转百分比
     *
     * @param v1
     * @param integerDigits  小数点前保留几位，默认2位
     * @param fractionDigits 小数点后保留几位，默认2位
     * @return
     */
    public static String formatPercent(Double v1, int integerDigits, int fractionDigits) {
        if (v1 == null) return null;
        //百分数格式化
        NumberFormat fmt = NumberFormat.getPercentInstance();
        //最多两位百分小数，如25.23%
        fmt.setMaximumIntegerDigits(integerDigits);//数点前保留几位
        fmt.setMaximumFractionDigits(fractionDigits);// 小数点后保留几位
        return fmt.format(v1);
    }

    /**
     * 保留小数位
     *
     * @param d
     * @param scale 小数位数
     * @return
     */
    public static Double scaleRound(Double d, int scale) {
        d = NullUtils.handleNull(d);
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 小数近位只保留后一位小数位<br>
     * 凡是有分位自动前延，只入不舍  比如22.15=22.2，22.11=22.2，22.154=22.2
     *
     * @param d
     * @return
     */
    public static Double decimalNearRound(Double d) {
        d = NullUtils.handleNull(d);
        if (d == 0) return d;
        //是小数
        String dstr = toString(d);
        if (dstr != null && dstr.indexOf(".") != -1) {
            String decimals = dstr.substring(dstr.lastIndexOf(".") + 1, dstr.length());
            //小数位后只有一位时不做处理
            //小数位后大于一位时进行小数处理（小数位后大于两位直接进行四舍五入，等于两位时数字小于5不做处理，反之进行五入处理）
            if (decimals != null && decimals.length() > 1) {
                //小数位大于2位
                if (decimals.length() > 2) {
                    //234-》 3
                    decimals = decimals.substring(1, 2);
                    int de = Integer.valueOf(decimals);
                    if (de >= 0 && de < 5)
                        d = add(d, 0.05);
                    d = scaleRound(d, 1);

                } else {//小数位等于2位
					/*decimals = decimals.substring(decimals.length() - 1);
					int d = Integer.valueOf(decimals);
					if(d>=5){
						goodsPrice = new BigDecimal(goodsPrice).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
					}*/
                    //凡是有分位自动前延，只入不舍  比如22.15=22.2  22.11也=22.2 rsh 2016-11-29
                    decimals = decimals.substring(decimals.length() - 1);
                    int de = Integer.valueOf(decimals);
                    if (de > 0 && de < 5)
                        d = add(d, 0.05);
                    d = scaleRound(d, 1);
                }
            }
        }
        return d;
    }

    public static void main(String[] args) {
        int m = 7 - 7 % 2;
        System.out.println(new Double(m));
    }

}
