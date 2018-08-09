/*
package com.mylearn.util;
import com.mylearn.consts.CharsetContants;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/**
 * ????http??????????
 * User: gaotianlin
 * Date: 2010-12-29
 * Time: 11:42:27
 *//*

@SuppressWarnings({"JavaDoc"})
public class HttpClientUtil {
    private final static Log log = LogFactory.getLog(HttpClientUtil.class);
    private static String timeout= "10000";

    public static final long CONNECT_TIMEOUT = 5 * 1000;//????????????
    public static final int GET_DATA_TIMEOUT = 20 * 1000;//???????????

    public static final String FUNCTIONID_PATTERN = "functionId=(.*?)&|functionId=(.+)";
    public static final String ACTION_PATTERN = ".*\\/(.*?)\\.";
    public static final String SPLIT_URL_TOKEN = "(key=.*?&)|(token=.*?&)|(key=.+)|(token=.+)";

    public static String sendHttpRequest(String url) {
        return sendHttpRequest(url, null);
    }


    */
/**
     * ?????????post????
     *
     * @param url
     * @param data
     * @return
     *//*

    public static String sendHttpRequestByParams(String url, NameValuePair[] data) {
        return sendHttpRequestByParams(url, data, 30000, CharsetContants.CHARSET_UTF_8);
    }

    public static String sendHttpRequestByParams4gbk(String url, NameValuePair[] data) {
        return sendHttpRequestByParams(url, data, 30000, CharsetContants.CHARSET_GBK);
    }

    */
/**
     * ?????????post????
     *
     * @param url
     * @param data
     * @return
     *//*

    public static String sendHttpRequestByParams(String url, NameValuePair[] data, int timeoutTime, String code) {
        //????HttpClient?????
        HttpClient httpClient = new HttpClient();
        log.info(splitToken(url));
        PostMethod getMethod = new PostMethod(url);
//        getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK" );
        getMethod.setRequestHeader("Connection", "close");

        getMethod.addParameters(data);
        //???????????????????
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //?????????????
        getMethod.getParams().setContentCharset(code);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutTime);

        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeoutTime);

       //???oss?????cookie
        Cookie cookie=new Cookie();
        cookie.setName("_appstoreosslogin_");
        cookie.setValue("OBGQ6WUIUSXL3ZROCIR5Z3K6G2I6HBDVCPCO3MI72WWRD4KTMMSLJB2PBZMD5AE5");
        cookie.setDomain("gw.m.360buy.com");
        cookie.setPath("/");
        Cookie[] cookieses =new Cookie[4];
        cookieses[0]=cookie;
        httpClient.getState().addCookies(cookieses);

        // getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        //?????????????
        InputStream ins = null;
        //?????????
        BufferedReader br = null;
        try {

            long timeBeforeExecute = System.currentTimeMillis();
            //???getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.info("method failure: " + getMethod.getStatusLine() + ",url:-----------" + splitToken(url) + ",params:" + toJson(data));
            }
            long taskTime = System.currentTimeMillis() - timeBeforeExecute;


            //???getResponseBodyAsStream????????????????????????????????????????????????????
            //   Thread.sleep(2000l);//???????????????10s
            ins = getMethod.getResponseBodyAsStream();
            String charset = getMethod.getResponseCharSet();
            if (charset.toUpperCase().equals("ISO-8859-1")) {
                charset = "utf-8";
            }
            //?????????????????????????????CHARSET??????????????
            br = new BufferedReader(new InputStreamReader(ins, getMethod.getResponseCharSet()));
            StringBuilder sbf = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sbf.append(line);
            }
            return new String(sbf.toString().getBytes(getMethod.getResponseCharSet()), charset);
        } catch (HttpException e) {
//            registerError(key);
            //???????????????????????????????????????????  \
            log.error("please check your http url address??,ulr------" + splitToken(url) + ",params:" + toJson(data), e);
        } catch (IOException e) {
//            registerError(key);
            log.error("network exception is happening,ulr------" + splitToken(url) + ",params:" + toJson(data), e);
        } catch (Exception e) {
//            registerError(key);
            log.error("Exception  is happening,ulr------" + splitToken(url) + ",params:" + toJson(data), e);
        } finally {
            //??????????????
            try {
                if (br != null) {
                    br.close();
                }
                if (ins != null) {
                    ins.close();
                }
                if (getMethod != null) {
                    try {
                        getMethod.releaseConnection();
                        httpClient.getHttpConnectionManager().closeIdleConnections(0);
                    } catch (Exception e) {
                        log.error("close http connetion failure,ulr------" + splitToken(url) + ",params:" + toJson(data), e);
                    }
                }
            } catch (IOException e) {
                log.error("stream connection close failure,ulr------" + splitToken(url) + ",params:" + toJson(data), e);
            } catch (Exception e) {
                log.error("Exception,ulr------" + splitToken(url) + ",params:" + toJson(data), e);
            }
        }
        return null;
    }

    */
/**
     * ???????????
     * ???????????
     *
     * @param url   url
     * @param param param
     * @return string
     *//*

    public static String sendHttpRequest(String url, String param) {
        return sendHttpRequest(url, param, Integer.parseInt(timeout));
    }


    public static String sendHttpRequest(String url, String param, int timeoutTime) {
        return httpPostResult(url, param, timeoutTime, CharsetContants.CHARSET_UTF_8);
    }


    public static String sendHttpRequest4Gbk(String url, String param, int timeoutTime) {
        return httpPostResult(url, param, timeoutTime, CharsetContants.CHARSET_GBK);
    }

    public static String sendGetRequest4Gbk(String url, int port, String param, int timeoutTime) {
        //????HttpClient?????
        HttpClient httpClient = new HttpClient();
        httpClient.getHostConfiguration().setHost(url, port);
        GetMethod getMethod = new GetMethod();
        getMethod.getParams().setContentCharset(CharsetContants.CHARSET_GBK);
        getMethod.setQueryString(param);

        //???????????????????
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //?????????????
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutTime);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeoutTime);

        try {
            httpClient.executeMethod(getMethod);
            return getMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    private static String httpPostResult(String url, String param, int timeoutTime, String charset) {
        //????HttpClient?????
        HttpClient httpClient = new HttpClient();
        log.info(splitToken(url));
        PostMethod getMethod = new PostMethod(url);
        getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
        getMethod.setRequestHeader("Connection", "close");
        if (StringUtils.isNotEmpty(param)) {
            if (url.indexOf("purchase.soa.360buy.com") != -1) {
                getMethod.addParameter("param", param);
            } else {
                getMethod.addParameter("body", param);
            }

        }

        //???????????????????
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //?????????????
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutTime);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeoutTime);
        // getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        //?????????????
        InputStream ins = null;
        //?????????
        BufferedReader br = null;
        try {

            long timeBeforeExecute = System.currentTimeMillis();
            //???getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.error("method failure: " + getMethod.getStatusLine() + ",url:-----------" + splitToken(url) + ",params:" + splitToken(param));
            }
            long executeSpendTime = System.currentTimeMillis() - timeBeforeExecute;

            //???getResponseBodyAsStream????????????????????????????????????????????????????
            //   Thread.sleep(2000l);//???????????????10s
            ins = getMethod.getResponseBodyAsStream();
            String tempCharset = getMethod.getResponseCharSet();
            if (tempCharset.toUpperCase().equals("ISO-8859-1")) {
                tempCharset = "utf-8";
            }
            //?????????????????????????????CHARSET??????????????
            br = new BufferedReader(new InputStreamReader(ins, getMethod.getResponseCharSet()));
            StringBuilder sbf = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sbf.append(line);
            }
            return new String(sbf.toString().getBytes(getMethod.getResponseCharSet()), tempCharset);
        } catch (HttpException e) {
            //???????????????????????????????????????????  \
//            registerError(key);
            log.error("please check your http url address,ulr------" + splitToken(url) + ",params:" + splitToken(param), e);
        } catch (IOException e) {
//            registerError(key);
            log.error("network exception is happening,ulr------" + splitToken(url) + ",params:" + splitToken(param), e);
        } catch (Exception e) {
//            registerError(key);
            log.error("Exception  is happening,ulr------" + splitToken(url) + ",params:" + splitToken(param), e);
        } finally {
            //??????????????
            try {
                if (br != null) {
                    br.close();
                }
                if (ins != null) {
                    ins.close();
                }
                if (getMethod != null) {
                    try {
                        getMethod.releaseConnection();
                        httpClient.getHttpConnectionManager().closeIdleConnections(0);
                    } catch (Exception e) {
                        log.error("close http connetion failure,ulr------" + splitToken(url) + ",params:" + splitToken(param), e);
                    }
                }
            } catch (IOException e) {
                log.error("stream connection close failure,ulr------" + splitToken(url) + ",params:" + splitToken(param), e);
            } catch (Exception e) {
                log.error("Exception,ulr------" + splitToken(url) + ",params:" + splitToken(param), e);
            }
        }
        return null;
    }

    */
/**
     * ??NameValuePair????????json???
     *
     * @param data NameValuePair ????
     * @return
     *//*

    private static String toJson(NameValuePair[] data) {
        StringBuilder buffer = new StringBuilder("{");
        for (NameValuePair nvp : data) {
            if (nvp.getName().equals("key") || nvp.getName().equals("token")) {
                continue;
            }
            buffer.append(nvp.getName());
            buffer.append(":");
            buffer.append(nvp.getValue());
            buffer.append(",");
        }
        buffer.append("}");

        return buffer.toString();
    }


    private static String splitToken(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        return splitStr(url, SPLIT_URL_TOKEN);
    }

    private static String splitStr(String str, String pathern) {
        String tempStr = str;
        Pattern p = Pattern.compile(pathern);
        Matcher m = p.matcher(tempStr);
        while (m.find()) {
            String funcId = m.group(1) != null ? m.group(1) : m.group(2) != null ? m.group(2) : m.group(3) != null ? m.group(3) : m.group(4);
            if (StringUtils.isNotBlank(funcId)) {
                int keyIndex = tempStr.indexOf(funcId);
                tempStr = tempStr.substring(0, keyIndex) + tempStr.substring(keyIndex + funcId.length());
            }
        }
        return tempStr;
    }


    private static String streamToString(InputStream inputStream) {
        StringBuilder response = new StringBuilder();
        String brLine = "";
        BufferedReader br = null;
        try {
            if (inputStream != null) {
                br = new BufferedReader(new InputStreamReader(inputStream, CharsetContants.CHARSET_UTF_8));
                while ((brLine = br.readLine()) != null) {
                    response.append(brLine);
                }
            }
        } catch (Exception e) {
            log.info("stream read error :" + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                log.info("input stream close fail");
            }
        }
        return response.toString();
    }


    public void setTimeout(String timeout) {
        HttpClientUtil.timeout = timeout;
    }


    */
/**
     * ?????????????360??????????
     *
     * @param file post????????
     * @param url  ???url
     *//*

    public static int uploadFile(File file, String url) {
        int status = -1;
        if (!file.exists()) {
            return status;
        }
        PostMethod postMethod = new PostMethod(url);
        try {
            //FilePart????????????????
            FilePart fp = new FilePart("file", file);
            Part[] parts = {fp};

            //????MIME?????????httpclient???????MulitPartRequestEntity???????
            MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
            postMethod.setRequestEntity(mre);
            HttpClient client = new HttpClient();
            postMethod.getParams().setContentCharset("GBK");  //360?????????GBK???????????????????????????
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// ???????????
            status = client.executeMethod(postMethod);
        } catch (Exception e) {
            status = -1;
            e.printStackTrace();
        } finally {
            //???????
            postMethod.releaseConnection();
        }
        return status;
    }
}

*/
