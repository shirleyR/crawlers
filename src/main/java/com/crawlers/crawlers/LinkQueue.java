package com.crawlers.crawlers;

import java.util.HashSet;
import java.util.Set;
public class LinkQueue implements Frontier {
	
	public static BloomFilter bloomFilter=new BloomFilter();
	public static Set<CrawlUrl> linkQueue=new HashSet<CrawlUrl>();

    public static int threads=CrawlConfig.CRAWL_THREAD_NUM;
	public synchronized CrawlUrl getNext()throws Exception{
		CrawlUrl result=null;
		while(true){
			if(!linkQueue.isEmpty()){
//				notifyAll();
				CrawlUrl element=linkQueue.iterator().next();
				linkQueue.remove(element);
			
				return element;
			}else{
				LinkQueue.threads--;
				if(LinkQueue.threads>0){
					System.out.println(Thread.currentThread().getName()+"thread wait");
					wait();
					LinkQueue.threads++;
				}else{
					System.out.println(Thread.activeCount()+"thread alive");
					// why notifyALl????
					notifyAll();
					return null;
				}
			}
		}
//			return result;
	}
	
	public synchronized boolean putUrl(CrawlUrl url)throws Exception{
		if(url.getOriUrl()!=null&&!url.getOriUrl().equals("")&&!bloomFilter.contains(url.getOriUrl())){
			bloomFilter.add(url.getOriUrl());
			linkQueue.add(url);
			notifyAll();
			return true;
		}
		return false;
	}
	
	
	
}
