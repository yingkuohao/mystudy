package com.mylearn.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.InputStream;
import java.security.KeyStore;

@Component
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);


    @Autowired
    private Environment environment;

    private PoolingHttpClientConnectionManager connMgr;

    private RequestConfig requestConfig;

    private final int MAX_TIMEOUT = 7000;

    private final String storePwd = "neusoft";

    private final String CHARSET_ENCODEING = "UTF-8";

    @PostConstruct
    private void init() {
        // 设置连接池  
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小  
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom().setRedirectsEnabled(false);    //这是组织url重定向
        // 设置连接超时  
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时  
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时  
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }

    public String doPostSSL(String apiUrl, String json) {
        String httpStr = null;
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                .setDefaultRequestConfig(requestConfig).build();

            HttpPost httpPost = new HttpPost(apiUrl);

            CloseableHttpResponse response = null;
            try {
                httpPost.setConfig(requestConfig);
                StringEntity stringEntity = new StringEntity(json, CHARSET_ENCODEING);
                stringEntity.setContentEncoding(CHARSET_ENCODEING);
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                }
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    return null;
                }
                httpStr = EntityUtils.toString(entity, CHARSET_ENCODEING);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (response != null) {
                    try {
                        EntityUtils.consume(response.getEntity());
                    } catch (Throwable e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        return httpStr;
    }

    private SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            KeyStore trustStore = KeyStore.getInstance("BKS");
//            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            //区别环境
            String sslPath = "ssl/client_jc3_daily.bks";
            if (environment.getActiveProfiles() != null && !EnvEnum.isOffline(environment.getActiveProfiles()[0])) {
                sslPath = "ssl/client_jc3_product.bks";
            }
            //注意取相对路径的api
            InputStream instream = this.getClass().getClassLoader().getResourceAsStream(sslPath);
            trustStore.load(instream, storePwd.toCharArray());
            instream.close();
            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy());
            SSLContext sslContext = sslContextBuilder.build();
            // 初始化SSL上下文
            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        return sslsf;
    }
}
