package com.crawlers.crawlers;

import java.io.IOException;

import org.apache.http.HttpException;
import org.junit.Test;

public class RetrievePageTest {
	@Test
	 public  void test()
    {
        try{
            System.out.println("下载开始");
            RetrievePage.downloadPage("http://www.baidu.com");
            System.out.println("下载结束");
        }
        catch(HttpException e){
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
         
    }
}
