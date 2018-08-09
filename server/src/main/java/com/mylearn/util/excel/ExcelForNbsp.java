package com.mylearn.util.excel;


import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Package:com.jd.ebook.admin.service.excel
 * User: yingkuohao
 * Date: 12-2-18
 * Time: ????2:13
 * CopyRight:360buy
 * Descrption:
 */
public class ExcelForNbsp {
    public static String padnbsp(String str) {
        str = str.trim();
        if (StringUtils.isNotEmpty(str)) {

            //5??????&nbsp;???????????????????
            str = str.replaceAll("&nbsp??", "&nbsp;");
            // <p>&nbsp;</p>
            str = str.replaceAll("<p>&nbsp;</p>", "");

            String str_tar = "????";
            String prefix = "??";

            str = str.trim();
            //?????????
            while (str.startsWith(prefix)) {
                str = str.substring(2);
                str = str.trim();
            }

            //???nbsp
            while (str.startsWith("&nbsp;")) {
                str = str.substring(6);
            }
            str = str.trim();
            String target_prefix = "????";
            //???nbsp
            str = str.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "??");
            //???lt??gt??
            str = str.replaceAll("&lt;br&gt;", "<br>");

            //??&nbsp;&nbsp;&nbsp;  ?? ??3??&nbsp;??????????????????2???????
            str = str.replaceAll("&nbsp;&nbsp;&nbsp; ", target_prefix);
            //??????&nbsp;????1?????????????&nbsp;????4???????&nbsp;??&nbsp;????nbsp????????????????�I?2???????
            str = str.replaceAll("??&nbsp;", target_prefix);
            str = str.replaceAll("    ", target_prefix);
            str = str.replaceAll("&nbsp;??&nbsp;", target_prefix);

            // ??????????????��,????.????!????;????:??????�I???????????????��??????????????????????
            str = str.replaceAll(",", "??");
            str = str.replaceAll("\\!", "??");
            str = str.replaceAll(":", "??");
            str = str.replaceAll(";", "??");
            str = str.replaceAll("\\(", "??");
            str = str.replaceAll("\\)", "??");

            // ????br
            str = brReplace(str);
            //????html?��???????????
            str = engCommaReplace(str);
            //????<p>????nbsp????
            str = pReplace(str);
            //?????????
            str = str.replaceAll("\\.", "??");
            //??????
            str = peroidReplace(str);
            str = peroid2Replace(str);

            //?????<p>???????��?????
            if (!str.startsWith("<p>")) {
                str = str_tar + str;
            }
        }
        return str;
    }

    /**
     * ???
     *
     * @param targetStr
     * @return
     */
    public static String peroidReplace(String targetStr) {
        String style = "[0-9]+??";    //?�I????<p>????nbsp??
        String replacement = "??";
        return regexChange(targetStr, style, replacement, true);
    }

    public static String peroid2Replace(String targetStr) {
        String style = "[0-9]???[0-9]";    //?�I????<p>????nbsp??
        String replacement = ".";
        return regexChange(targetStr, style, replacement, true);
    }

    /**
     * ?�I<p>????nbsp??
     *
     * @param targetStr
     * @return
     */
    public static String pReplace(String targetStr) {
        String style = "<p>( *??*(&nbsp;)* *??*)*";    //?�I????<p>????nbsp??
        String replacement = "<p>????";
        return regexChange(targetStr, style, replacement, false);
    }

    /**
     * ?�Ihtml????��???????
     *
     * @param targetStr
     * @return
     */
    public static String brReplace(String targetStr) {
        String style = "<br */?>( *??*(&nbsp[;|??])* *??*)*";    //?�I???��?html????��????????????
        String replacement = "<br />????";
        return regexChange(targetStr, style, replacement, false);
    }

    /**
     * ?�Ihtml????��???????
     *
     * @param targetStr
     * @return
     */
    public static String engCommaReplace(String targetStr) {
        String style = "&[a-z]+??";
        String replacement = ";";
        return regexChange(targetStr, style, replacement, true);
    }


    /**
     * ?????�I
     *
     * @param test        ??????
     * @param style       ?�I?????
     * @param replacement ?�I??????
     * @param flag        ??????true?????????�Ireplacement?��?????????????false????????replaceent????�I
     * @return
     */
    private static String regexChange(String test, String style, String replacement, boolean flag) {
        Pattern pattern = Pattern.compile(style);
        Matcher matcher = pattern.matcher(test);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String cur_str = matcher.group();
            System.out.println(cur_str);
            if (!flag) {
                matcher.appendReplacement(sb, replacement);
            } else {

                if (".".equals(replacement)) {
                    String end = cur_str.substring(cur_str.length() - 1, cur_str.length());
//                    ????????????��
                    matcher.appendReplacement(sb, cur_str.substring(0, cur_str.length() - 2) + replacement + end);
                } else {
                    matcher.appendReplacement(sb, cur_str.substring(0, cur_str.length() - 1) + replacement);
                }
            }

        }
        matcher.appendTail(sb);
        return sb.toString();
    }


}
