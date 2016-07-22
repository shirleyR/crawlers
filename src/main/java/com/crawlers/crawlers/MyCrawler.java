package com.crawlers.crawlers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.conn.ConnectTimeoutException;

/**
 * Created by rsl_pc on 2016/7/17.
 */
public class MyCrawler {
    private static int num=0;
    public static LinkQueue queue=new LinkQueue();
    public MyCrawler(){

    }
    private void initCrawlerWithSeeds(String[] seeds){
        synchronized (this){
            try{
                for(int i=0;i<seeds.length;i++){
                    CrawlUrl url=new CrawlUrl();
                    url.setOriUrl(seeds[i]);
                    queue.putUrl(url);
                    // putUrl into queue
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public void crawling(String[] seeds,int threadId){
    	System.out.println(" which thread ID :"+threadId);
        try{
            LinkFilter filter=new LinkFilter() {
                public boolean accept(String url) {
                    Pattern pattern=Pattern.compile("^((https|http|ftp|rtsp|mms)?://)"
                            + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                            + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"
                            + "|"
                            + "([0-9a-z_!~*'()-]+\\.)*"
                            + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."
                            + "[a-z]{2,6})"
                            + "(:[0-9]{1,4})?"
                            + "((/?)|"
                            + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");
                    Matcher matcher=pattern.matcher(url);
                    boolean isMatch=matcher.matches();
                    if(isMatch&&url.startsWith(CrawlConfig.CRAWL_LIMIT_PATH)){
                        return true;
                    }

                    return false;
                }
            };
            initCrawlerWithSeeds(seeds);
            CrawlUrl visitedCrawlUrl =queue.getNext();
            do{

            	
//            	System.out.println(queue.);
            	if(visitedCrawlUrl==null){
            		continue;
            	}
//            	if(visitedCrawlUrl.getDepth()>CrawlConfig.CRAWL_DEPTH){
//            		continue;
//            	}
            	String visitedUrl=visitedCrawlUrl.getOriUrl();
            	int depth=visitedCrawlUrl.getDepth();
            	// 
            	System.out.println(Thread.currentThread().getName()+"  : size:"+LinkQueue.threads);
//            	System.out.println("size"+Thread.activeCount()+"::depth:"+depth);
            	// catch visitedFrontier
            	if(visitedUrl.equals("")||visitedUrl.trim().equals(""))continue;
            	try{
            		RetrievePage.downloadPage(visitedUrl);
            		Set<String> links=HtmlParseTool.extractLinks(visitedUrl, filter);
            		for(String link:links){
            			if(!queue.bloomFilter.contains(link)){
            				CrawlUrl unvisitedUrl=new CrawlUrl();
            				unvisitedUrl.setOriUrl(link);
            				unvisitedUrl.setDepth(depth+1);
            				queue.putUrl(unvisitedUrl);
            			}
            		}
            	}catch(ConnectTimeoutException e){
            		// time out 
            		visitedCrawlUrl=queue.getNext();
            		num++;
            		e.printStackTrace();
            		continue;
            	}catch(SocketTimeoutException e){
            		visitedCrawlUrl=queue.getNext();
            		num++;
            		e.printStackTrace();
            	}
            	visitedCrawlUrl=queue.getNext();
        		num++;
            }while (queue.threads>0&&num<100);
        }catch(IOException e){
        	e.printStackTrace();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}

