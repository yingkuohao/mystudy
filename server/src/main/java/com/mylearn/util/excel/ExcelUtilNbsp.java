package com.mylearn.util.excel;

import com.mylearn.util.DateUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: chenbaoan@360buy.com
 * Date: 11-10-4
 * Time: ????2:51
 */
public class ExcelUtilNbsp {
    private final static Logger log = Logger.getLogger(ExcelUtilNbsp.class);
    /**
     * ??????????çi???linux??windows?????
     */
    public final static String FILE_SEPARATOR_SIGAL = "/";
    public final static String FILE_SEPARATOR = "//";
    public final static String EXCEL_SUFFIX_2003 = ".xls";
    public final static String EXCEL_SUFFIX_2007 = ".xlsx";



    /**
     * ?????
     */
    public static void view(HttpServletResponse response, String path, String fileName) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            response.reset();  //????????
            response.setCharacterEncoding("GBK");//???????
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("GBK"), "iso-8859-1") + "\"");
            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(path + "/" + fileName);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
        } catch (IOException e) {
            log.error("down template error!" + e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("ExcelUtilNbsp -> view() -> inputStream.close() error!", e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("ExcelUtilNbsp -> view() -> outputStream.close() error!", e);
                }
            }
        }
    }

    /**
     * ???EXCEL???
     *
     * @param fileName ?????
     * @return ?ß“?
     */
    public static List<String[]> readExcel2003(String fileName) {
        try {
            File file = new File(fileName);
            return readExcel(file);
        } catch (Exception e) {
            log.error("read excel file " + fileName + " error!" + e);
        }
        return null;
    }

    private static List<String[]> readExcel(File file) throws IOException, BiffException {


        List<String[]> list = new ArrayList<String[]>();
        Workbook workbook = null;
        try {
            InputStream is = new FileInputStream(file);
            workbook = Workbook.getWorkbook(is);
            Sheet sheet = workbook.getSheet(0);
            for (int i = 0; i < sheet.getRows(); i++) {
                String[] temp = new String[sheet.getColumns()];
                boolean isNull = false;
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    temp[j] = cell.getContents();
                    if (!isNull && StringUtils.isNotBlank(temp[j])) {
                        isNull = true;
                    }
                }
                if (isNull) {
                    list.add(temp);
                }
            }
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //??????
    public static boolean mkdirFilePath(String targetDirectory) {
        try {
            File targetDirectoryFile = new File(targetDirectory);
            if (!targetDirectoryFile.isDirectory()) {
                targetDirectoryFile.mkdir();
                log.error("?????" + targetDirectory + "???");

            }
            return true;
        } catch (Exception e) {
            log.error("?????" + targetDirectory + "???", e);
        }
        return false;
    }


    /**
     * ????excel
     *
     * @param list ???
     * @param path °§??
     * @param name excel???
     * @return
     */
    public static boolean writeExcel(List<String[]> list, String path, String separator, String name) {
        separator = null == separator ? FILE_SEPARATOR : separator;
        //description: ????????
        String date = DateUtil.date2String(Calendar.getInstance().getTime()) ;
        name = null == name ? "queryExcel-" + date + "-" + RandomUtils.nextInt() + EXCEL_SUFFIX_2003 : name;
        String worksheet = "sheet0"; //????excel??????????

        File file = new File(path + separator + name);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            WritableSheet sheet = workbook.createSheet(worksheet, 0);  //??????????

            for (int i = 0; i < list.size(); i++) {
                String[] str_arr = list.get(i);

                for (int j = 0; j < str_arr.length; j++) {
                    //yingkhtodo description:test

                    String str = ExcelForNbsp.padnbsp(str_arr[j]);
                    jxl.write.Label labelCF = new jxl.write.Label(j, i, str); //??????????????i??j
                    sheet.addCell(labelCF);
                }
            }
            workbook.write();
            workbook.close();
            os.close();
        } catch (IOException e) {
            log.error("io??ß’??" + e.getMessage());
            return false;
        } catch (WriteException e1) {
            log.error("WriteException??ß’??" + e1.getMessage());
            return false;
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                log.error("IOException??????" + e.getMessage());
            }
        }
        return true;
    }


    public static String padnbsp(String str) {

        str = str.trim();
        if (StringUtils.isNotEmpty(str)) {


            //                5??????&nbsp;???????????????????
            str = str.replaceAll("&nbsp??", "&nbsp;");
//                   <p>&nbsp;</p>
            str = str.replaceAll("<p>&nbsp;</p>", "");


            String str_tar = "????";
            String prefix = "??";

            str = str.trim();
            //?????????
            while (str.startsWith(prefix)) {
                str = str.substring(2);
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
//??????&nbsp;????1?????????????&nbsp;????4???????&nbsp;??&nbsp;????nbsp????????????????ùI?2???????
            str = str.replaceAll("??&nbsp;", target_prefix);
            str = str.replaceAll("    ", target_prefix);
            str = str.replaceAll("&nbsp;??&nbsp;", target_prefix);


//              ??????????????≥è,????.????!????;????:??????ùI???????????????≥è??????????????????????
            str = str.replaceAll(",", "??");
//            str = str.replaceAll("\\.", "??");
            str = str.replaceAll("\\!", "??");
            str = str.replaceAll(":", "??");
            str = str.replaceAll(";", "??");
            str = str.replaceAll("\\(", "??");
            str = str.replaceAll("\\)", "??");

            // ????br
            str = brReplace(str);
            //????html?ß÷???????????
            str = engCommaReplace(str);
            //????<p>????nbsp????
            str = pReplace(str);
            //??????
            str = peroidReplace(str);
            //?????????
            str = str.replaceAll("\\.", "??");

            //?????<p>???????®π?????
            if(!str.startsWith("<p>"))  {
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
        String style = "[0-9]+\\.";    //?ùI????<p>????nbsp??
        String replacement = "??";
        return regexChange(targetStr, style, replacement, true);
    }

    /**
     * ?ùI<p>????nbsp??
     *
     * @param targetStr
     * @return
     */
    public static String pReplace(String targetStr) {
       String style = "<br */?>( *??*(&nbsp[;|??])* *??*)*";    //?ùI???ß÷?html????ß÷????????????
        String replacement = "<p>????";
        return regexChange(targetStr, style, replacement, false);
    }

    /**
     * ?ùIhtml????ß÷???????
     *
     * @param targetStr
     * @return
     */
    public static String brReplace(String targetStr) {
        String style = "<br ?/?>( *??*(&nbsp;)* *??*)*";    //?ùI???ß÷?html????ß÷????????????
        String replacement = "<br />????";
        return regexChange(targetStr, style, replacement, false);
    }

    /**
     * ?ùIhtml????ß÷???????
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
     * ?????ùI
     *
     * @param test        ??????
     * @param style       ?ùI?????
     * @param replacement ?ùI??????
     * @param flag        ??????true?????????ùIreplacement?ß÷?????????????false????????replaceent????ùI
     * @return
     */
    private static String regexChange(String test, String style, String replacement, boolean flag) {
        Pattern pattern = Pattern.compile(style);
        Matcher matcher = pattern.matcher(test);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String cur_str = matcher.group();
            if (!flag) {
                matcher.appendReplacement(sb, replacement);
            } else {

                matcher.appendReplacement(sb, cur_str.substring(0, cur_str.length() - 1) + replacement);
            }

            System.out.println(cur_str);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * ???????
     *
     * @param result
     * @param head
     * @param data   ???
     * @param path   °§??
     * @param name   excel???
     * @return
     */
    public static boolean exportLog(Map result, String head[], List<String[]> data, String path, String name) {

        String worksheet = "sheet0"; //????excel??????????
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        File file = new File(path + "/" + name);

        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            WritableSheet sheet = workbook.createSheet(worksheet, 0);  //??????????
            // ß’???
            Iterator it = result.entrySet().iterator();
            int row = 0;
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                jxl.write.Label labe1 = new jxl.write.Label(row, 1, (String) pairs.getKey());
                jxl.write.Label labe2 = new jxl.write.Label(row, 2, String.valueOf(pairs.getValue()));
                sheet.addCell(labe1);
                sheet.addCell(labe2);
                row++;
            }
            // ß’????
            for (int i = 0; i < head.length; i++) {
                jxl.write.Label labe2 = new jxl.write.Label(i, 4, (String) head[i]);
                sheet.addCell(labe2);
            }
            // ß’??? 
            for (int i = 0; i < data.size(); i++) {
                String[] str_arr = data.get(i);
                for (int j = 0; j < str_arr.length; j++) {
                    jxl.write.Label labelCF = new jxl.write.Label(j, i + 5, str_arr[j]);
                    sheet.addCell(labelCF);
                }
            }
            workbook.write();
            workbook.close();
            os.close();
        } catch (IOException e) {
            log.error("io??ß’??" + e.getMessage());
            return false;
        } catch (WriteException e1) {
            log.error("WriteException??ß’??" + e1.getMessage());
            return false;
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                log.error("IOException??????" + e.getMessage());
            }
        }
        return true;
    }


    // ???????
    public static void responseDownload(HttpServletResponse response, String downFilename, String targetDirectory) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            // ????
            String filepath = targetDirectory;//?????????????°§??
            response.setHeader("Location", downFilename);
            response.setHeader("Content-Disposition", "attachment; filename=" + downFilename);
            response.setContentType("application/ vnd.ms-excel");
            outputStream = response.getOutputStream();

            File file = new File(filepath + "\\" + downFilename);
            if (!file.exists())
                file.createNewFile();

            inputStream = new FileInputStream(filepath + "/" + downFilename);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
