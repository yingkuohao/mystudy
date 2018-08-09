package com.mylearn.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: tuhao
 * Date: 11-5-30
 * Time: ????9:30
 */
public class ReadExcelUtil {

    /**
     * ?????????????
     * <pre>
     *     "?????
     ??0j? 1??? 2?????"	???	??????	???	?????
     1	1878532	????	0853	24
     1	1878533	????	0853	24
     1	1878534	????	0853	24
     1	1878535	????	0853	24
     1	1878536	????	0853	24
     * </pre>
     * @param file Excel2007??????
     * @return list ????????
     * @throws Exception  ???????????
     */
//    public static List<String[]> readExcel2007(File file) throws Exception {
//        list = new ArrayList();
//        try {
//            // ???Book1.xlsx new File("F:\\IdeaProject\\v_New_20110503\\jd-chongzhiman-web\\target\\jd-chongzhiman-web.war\\upload\\excel\\19pay??????.xlsx")
//            ZipFile xlsxFile = new ZipFile(file);
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            // ????sharedStrings.xml??????,??????
//            ZipEntry sharedStringXML = xlsxFile.getEntry("xl/sharedStrings.xml");
//            InputStream sharedStringXMLIS = xlsxFile.getInputStream(sharedStringXML);
//            Document sharedString = (Document) dbf.newDocumentBuilder().parse(sharedStringXMLIS);
//            //???si????t????????????????????
//            NodeList str = ((Document) sharedString).getElementsByTagName("si");
//            String sharedStrings[] = new String[str.getLength()];
//            for (int n = 0; n < str.getLength(); n++) {
//                Node nsi = str.item(n);
//                NodeList ndt = nsi.getChildNodes();
//                String temp = "";
//                //????????
//                for (int m = 0; m < ndt.getLength(); m++) {
//                    temp = temp + ndt.item(m).getTextContent();
//                }
//                sharedStrings[n] = temp;
//            }
//            // ??????????????workbook.xml,??????忘??????????????技???sheet
//            ZipEntry workbookXML = xlsxFile.getEntry("xl/workbook.xml");
//            InputStream workbookXMLIS = xlsxFile.getInputStream(workbookXML);
//            Document doc = dbf.newDocumentBuilder().parse(workbookXMLIS);
//            // ???????技???sheet
//            NodeList nl = doc.getElementsByTagName("sheet");
//            for (int i = 0; i < nl.getLength(); i++) {
//                Element element = (Element) nl.item(i);// ??node????element????4?????????????
//                // System.out.println(element.getAttribute("name")+"----2---");// ???sheet????name??????
//                // ??????????????????????????name???xml???????????workbook.xml????<sheet name="Sheet1" sheetId="1" r:id="rId1" /> ???
//                // ????????????????????xl/worksheets?????sheet1.xml,???xml???????????????????
//                ZipEntry sheetXML = xlsxFile.getEntry("xl/worksheets/sheet" + element.getAttribute("sheetId").toLowerCase() + ".xml");
//                InputStream sheetXMLIS = xlsxFile.getInputStream(sheetXML);
//                Document sheetdoc = dbf.newDocumentBuilder().parse(sheetXMLIS);
//                NodeList rowdata = sheetdoc.getElementsByTagName("row");
//                //System.out.println(rowdata.getLength());
//                for (int j = 0; j < rowdata.getLength(); j++) {
//                    // ???????? ?快?????
//                    Element row = (Element) rowdata.item(j);
//                    // ????快????????快???
//                    NodeList columndata = row.getElementsByTagName("c");
//                    String[] temp = new String[columndata.getLength()];
//                    for (int k = 0; k < columndata.getLength(); k++) {
//                        Element column = (Element) columndata.item(k);
//                        NodeList values = column.getElementsByTagName("v");
//                        Element value = (Element) values.item(0);
//                        if (column.getAttribute("t") != null & column.getAttribute("t").equals("s")) {
//                            // ???????????????sharedstring.xml???????快??
//                            temp[k] = sharedStrings[Integer.parseInt(value.getTextContent())];
//                            //System.out.print(sharedStrings[Integer.parseInt(value.getTextContent())] + "---3-- ");
//                        } else {
//                            if (null != value) {
//                                temp[k] = value.getTextContent();
//                                //System.out.print(value.getTextContent() + "---4---");
//                            }
//                        }
//                    }
//                    //System.out.println();
//                    list.add(temp) ;
//                }
//            }
//        } catch (Exception e) {
//           throw e;
//        }
//        return list;
//    }

    /**
     * ?????????????
     * <pre>
     *     "?????
     * ??0j? 1??? 2?????"	???	??????	???	?????
     * 1	1878532	????	0853	24
     * 1	1878533	????	0853	24
     * 1	1878534	????	0853	24
     * 1	1878535	????	0853	24
     * 1	1878536	????	0853	24
     * </pre>
     *
     * @param file Excel2003??????
     * @return list ????????
     * @throws Exception ???????????
     */
    public static List<String[]> readExcel2003(File file) throws Exception {
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
                    //System.out.print(cell.getContents() + "---");
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

//    public static void main(String[] args) {
//        readExcel2007(new File("F:\\IdeaProject\\v_New_20110503\\jd-chongzhiman-web\\target\\jd-chongzhiman-web.war\\upload\\excel\\19pay??????.xlsx")) ;
//        readExcel2003(new File("F:\\tuhao\\chongzhi\\19pay??????.xls"));
//        for(int i=0;i<list.size();i++){
//            String[] temp=(String[])list.get(i);
//            System.out.println(temp[0]+"-----"+temp[1]+"-----"+temp[4]+"-----");
//        }
//
//    }
}
