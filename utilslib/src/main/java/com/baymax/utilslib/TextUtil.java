package com.baymax.utilslib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author oukanggui
 * @date 2018/10/11
 * 描述 文本工具类
 */

public class TextUtil {

    /**
     * 判断一个字符串为空
     *
     * @param str 待处理字符串
     * @return true 对象为空，否则对象不为空
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 判断一个字符是否为空或者全是空格
     * @param s 待检测的字符串
     * @return Returns true if the parameter is null or contains only whitespace
     */
    public static boolean isBlank(final CharSequence s) {
        if (s == null) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个字符串是否是邮箱格式
     * 注：目前只是简单的通过字符串中是否含有@来判断
     *
     * @param strEmail 待处理字符串
     * @return true 是邮箱格式，否则不是邮箱格式
     */
    public static boolean isEmail(String strEmail) {
        if (isEmpty(strEmail)) {
            return false;
        }
        return strEmail.contains("@");
    }

    /**
     * 判断一个字符串是否是手机号（正则表达式）
     *
     * @param strPhone 待处理字符串
     * @return true 是手机号，否则不是手机号
     */
    public static boolean isPhoneNumber(String strPhone) {
        if (isEmpty(strPhone)) {
            return false;
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (strPhone.length() != 11) {
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(strPhone);
        return m.matches();
    }
}
