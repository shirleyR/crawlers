package com.crawlers.crawlers;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * Created by rsl_pc on 2016/7/17.
 */
public class RetrievePage {
    private static String USER_AGENT="";
    private static String DEFAULT_CHARSET="GB2312,utf-8;q=0.7,*;q=0.7";
    private static String ACCEPT="text/html,application/xml,application/xhtml+xml;q=0.9,*/*;q=0.8";

    /**
     * download page
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean downloadPage(String path)throws Exception{
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(path);
        httpGet.addHeader("Accept-Charset",DEFAULT_CHARSET);
        httpGet.addHeader("Accept",ACCEPT);
        httpGet.addHeader("User-Agent",USER_AGENT);

        RequestConfig requestConfig=RequestConfig.custom()
                .setSocketTimeout(1000)
                .setConnectTimeout(1000)
                .build();
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response=httpClient.execute(httpGet);

        try{
            HttpEntity entity=response.getEntity();
            StatusLine statusLine=response.getStatusLine();
            if(statusLine.getStatusCode()== HttpStatus.SC_MOVED_PERMANENTLY||
                    statusLine.getStatusCode()==HttpStatus.SC_MOVED_TEMPORARILY||
                    statusLine.getStatusCode()==HttpStatus.SC_SEE_OTHER||
                    statusLine.getStatusCode()==HttpStatus.SC_TEMPORARY_REDIRECT){
                org.apache.http.Header header = httpGet.getFirstHeader("location");
                if(header!=null){
                    String newUrl=header.getValue();
                    if(newUrl==null||newUrl.equals("")){
                        newUrl="/";
                        HttpGet redirect=new HttpGet(newUrl);
                    }

                }
            }
            if(statusLine.getStatusCode()==HttpStatus.SC_OK){
                if(entity==null){
                    throw new ClientProtocolException("Response contains no");

                }else {
                    InputStream inputStream=entity.getContent();
                    String filename=getFilenameByUrl(path,entity.getContentType().getValue());
                    OutputStream outputStream=new FileOutputStream(CrawlConfig.CRAWL_DOWNLOAD_PATH + 
                    		filename);
                    try{
                        int tempByte=-1;
                        while ((tempByte=inputStream.read())>0){
                            outputStream.write(tempByte);
                        }
                        return true;
                    }catch (Exception e){
                        e.printStackTrace();
                        return false;
                    }finally {
                        if(inputStream!=null){
                            inputStream.close();
                        }
                        if(outputStream!=null){
                            outputStream.close();
                        }
                    }

                }
            }
            return false;
        }finally {
            response.close();
        }
    }
    public static String getFilenameByUrl(String url,String contentType){
        url=url.substring(7);
        if(contentType.indexOf("html")!=-1){
            url=url.replaceAll("[\\?/:*|<>\"]","_")+".html";
            return url;
        }else {
            url=url.replaceAll("[\\?/:*|<>\"]","_")+contentType.substring(contentType.lastIndexOf('/')+1);
            return url;
        }
    }

    /**
     * convert Stream to String
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is){
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String line=null;
        try{
            while ((line=reader.readLine())!=null){
                sb.append(line+"/n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
        return sb.toString();
    }
    
}
