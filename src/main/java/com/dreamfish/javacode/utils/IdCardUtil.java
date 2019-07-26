package com.dreamfish.javacode.utils;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * @author: create by syb
 * @version: v1.0
 * @description: com.dreamfish.javacode.utils
 * @date:19-7-26 下午8:06
 */
public class IdCardUtil {

    private IdCardUtil() {}

    public final static int CHINA_ID_MIN_LENGTH = 15;
    public final static int CHINA_ID_MAX_LENGTH = 18;
    public static final String regex = "^\\d{6}(((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]" +
            "|3[0-1])\\d{3}([0-9]|x|X))|(\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}))$";

    public static Boolean isValid(String idCardNo) {
        return Pattern.matches(regex,idCardNo)||(idCardNo.length() == 17 && Pattern.matches(regex,idCardNo.substring(0,15)));
    }

    public static String getGender(String idCardNo) {

        if (!isValid(idCardNo)) {
            return null;
        }
        int idxSexStart = 16;
        if (idCardNo.length() == CHINA_ID_MIN_LENGTH) {
            idxSexStart = 14;
        }
        String idxSexStr = idCardNo.substring(idxSexStart, idxSexStart + 1);
        int idxSex = Integer.parseInt(idxSexStr) % 2;

        return idxSex == 1? "男" : "女";
    }

    public static String getBirthDate(String idCardNo) {

        if (!isValid(idCardNo)) {
            return null;
        }
        int birthSpan = 8;
        if (idCardNo.length() == CHINA_ID_MIN_LENGTH) {
            birthSpan = 6;
        }
        String birthDate =  (birthSpan == 4 ? "19" : "") + idCardNo.substring(6, 6 + birthSpan);

        return birthDate;
    }

    public static Integer getAge(String idCardNo) {

        if (!isValid(idCardNo)) {
            return null;
        }
        String birthYear = getBirthDate(idCardNo).substring(0, 4);
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.YEAR) - Integer.valueOf(birthYear);
    }
}
