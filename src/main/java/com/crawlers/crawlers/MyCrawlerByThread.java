package com.crawlers.crawlers;
import java.util.ArrayList;



/**
 * Created by rsl_pc on 2016/7/17.
 */
public class MyCrawlerByThread extends MyCrawler implements Runnable {
    private int threadId;
    public MyCrawlerByThread(int id){
        this.threadId=id;
    }
//    @Override
    public void run(){
        try{
        	
            crawling(new String[]{CrawlConfig.CRAWL_PATH}, threadId);
        }catch (Exception e){

        }
    }
    public static void main(String [] args){
        try{

            long startTime=System.currentTimeMillis();
            System.out.println("采集开始");
            ArrayList<Thread> threadList = new ArrayList<Thread>(CrawlConfig.CRAWL_THREAD_NUM);
            for(int i = 0 ; i < CrawlConfig.CRAWL_THREAD_NUM; i++) {
                MyCrawlerByThread crawler = new MyCrawlerByThread(i);
                Thread t = new Thread(crawler,"thread_"+i);
                System.out.println(Thread.currentThread().getName()+"xian");
                t.start();
                threadList.add(t);
                Thread.sleep(10L);
            }
            
            while(threadList.size() > 0) {
                Thread child = (Thread) threadList.remove(0);
                child.join();
            }
            
//            while(true){  
//                if(MyCrawlerByThread.queue.threads==0&&MyCrawlerByThread.queue.linkQueue.isEmpty()&& Thread.activeCount() == 1){  
//                    long end = System.currentTimeMillis();   
//                    System.out.println("总共耗时"+(end-startTime)/1000+"秒");  
//                    System.exit(1);  
////                  break;  
//                }  
//                  
//            }
            System.out.println("采集结束");
            long endTime=System.currentTimeMillis();
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        }catch (Exception e){

        }finally{
        	
        }
    }
}
