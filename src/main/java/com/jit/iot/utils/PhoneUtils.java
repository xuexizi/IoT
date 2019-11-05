package com.jit.iot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @packageName: com.jit.iot.utils
 * @className: PhoneUtils
 * @Description:
 * @author: xxz
 * @date: 2019/7/25 9:33
 */

public class PhoneUtils {

    public static boolean isMobile(String mobile){
        if (mobile.length() != 11) {
            return false;
        } else {
            /**
             * 移动号段正则表达式
             */
            String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
            /**
             * 联通号段正则表达式
             */
            String pat2  = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
            /**
             * 电信号段正则表达式
             */
            String pat3  = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$";
            /**
             * 虚拟运营商正则表达式
             */
            String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

            if(isMatch(pat1, mobile) || isMatch(pat2, mobile) || isMatch(pat3, mobile) || isMatch(pat4, mobile)) {
                return true;
            }
            return false;
        }
    }

    public static boolean isMatch(String pat, String mobile){
        Pattern pattern = Pattern.compile(pat);
        Matcher match = pattern.matcher(mobile);
        boolean isMatch = match.matches();
        if(isMatch){
            return true;
        }
        return false;
    }

}
