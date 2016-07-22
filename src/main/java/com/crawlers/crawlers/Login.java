package com.crawlers.crawlers;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Lookup;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.DefaultCookieSpecProvider;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.*;

/**
 * Created by rsl_pc on 2016/7/12.
 */
public class Login {
    public static void login(){
        RequestConfig requestConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        CloseableHttpClient httpClient=HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        try{
            HttpGet get=new HttpGet("http://www.zhihu.com/");
            CloseableHttpResponse response=httpClient.execute(get);
//            setCookie(response);
            String responseHtml=EntityUtils.toString(response.getEntity());
//            String xsrfValue=responseHtml.split("<input type=\"hidden\");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
