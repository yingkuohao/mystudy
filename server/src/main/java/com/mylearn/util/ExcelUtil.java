package com.mylearn.util;

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
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * User: chenbaoan@360buy.com
 * Date: 11-10-4
 * Time: ????2:51
 */
public class ExcelUtil {
    private final static Logger log = Logger.getLogger(ExcelUtil.class);
    /**
     * ??????????i???linux??windows?????
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
                    log.error("ExcelUtil -> view() -> inputStream.close() error!", e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("ExcelUtil -> view() -> outputStream.close() error!", e);
                }
            }
        }
    }

    /**
     * ???EXCEL???
     *
     * @param fileName ?????
     * @param sheetNum sheet???
     * @return ?§Ò?
     */
    public static List<String[]> readExcel2003(String fileName, int sheetNum) {
        try {
            File file = new File(fileName);
            return readExcel(file, sheetNum);
        } catch (Exception e) {
            log.error("read excel file " + fileName + " error!" + e);
        }
        return null;
    }

    private static List<String[]> readExcel(File file, int sheetNum) throws IOException, BiffException {


        List<String[]> list = new ArrayList<String[]>();
        Workbook workbook = null;
        try {
            InputStream is = new FileInputStream(file);
            workbook = Workbook.getWorkbook(is);
            Sheet sheet = workbook.getSheet(sheetNum);
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
     * @param path ¡¤??
     * @param name excel???
     * @return
     */
    public static boolean writeExcel(List<String[]> list, String path, String separator, String name) {
        separator = null == separator ? FILE_SEPARATOR : separator;
        //description: ????????
        String date = DateUtil.getDate2Str("yyyy-MM-dd", Calendar.getInstance().getTime());
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
                    jxl.write.Label labelCF = new jxl.write.Label(j, i, str_arr[j]); //??????????????i??j
                    sheet.addCell(labelCF);
                }
            }
            workbook.write();
            workbook.close();
            os.close();
        } catch (IOException e) {
            log.error("io??§Õ??" + e.getMessage());
            return false;
        } catch (WriteException e1) {
            log.error("WriteException??§Õ??" + e1.getMessage());
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


    /**
     * ????excel
     *
     * @param set  ???
     * @param path ¡¤??
     * @param name excel???
     * @return
     */
    public static boolean writeExcel(Set<String[]> set, String path, String separator, String name) {
        separator = null == separator ? FILE_SEPARATOR : separator;
        //description: ????????
        String date = DateUtil.getDate2Str("yyyy-MM-dd", Calendar.getInstance().getTime());
        name = null == name ? "queryExcel-" + date + "-" + RandomUtils.nextInt() + EXCEL_SUFFIX_2003 : name;
        String worksheet = "sheet0"; //????excel??????????

        File file = new File(path + separator + name);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            WritableSheet sheet = workbook.createSheet(worksheet, 0);  //??????????

            int i = 1;
            for (String[] str : set) {

                for (int j = 0; j < str.length; j++) {
                    jxl.write.Label labelCF = new jxl.write.Label(j, i, str[j]); //??????????????i??j
                    sheet.addCell(labelCF);
                }
                i++;
            }

            workbook.write();
            workbook.close();
            os.close();
        } catch (IOException e) {
            log.error("io??§Õ??" + e.getMessage());
            return false;
        } catch (WriteException e1) {
            log.error("WriteException??§Õ??" + e1.getMessage());
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


    /**
     * ???????
     *
     * @param result
     * @param head
     * @param data   ???
     * @param path   ¡¤??
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
            // §Õ???
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
            // §Õ????
            for (int i = 0; i < head.length; i++) {
                jxl.write.Label labe2 = new jxl.write.Label(i, 4, (String) head[i]);
                sheet.addCell(labe2);
            }
            // §Õ??? 
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
            log.error("io??§Õ??" + e.getMessage());
            return false;
        } catch (WriteException e1) {
            log.error("WriteException??§Õ??" + e1.getMessage());
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

    /**
     * ?????????????????
     *
     * @param preffix ??
     * @param suffix  ???
     * @return
     */
    public static String createFileName(String preffix, String suffix) {
        //?????????
        String date = DateUtil.getDate2Str("yyyy-MM-dd", Calendar.getInstance().getTime());
        String userName ="";
        String fileName = preffix + userName + "-" + date + "-" + suffix;
        return fileName;
    }


    // ???????
    public static void responseDownload(HttpServletResponse response, String downFilename, String targetDirectory) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            // ????
            String filepath = targetDirectory;//?????????????¡¤??
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
