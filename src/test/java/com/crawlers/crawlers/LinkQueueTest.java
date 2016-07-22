package com.crawlers.crawlers;

import org.junit.Test;

public class LinkQueueTest {
	private static LinkQueue queue=new LinkQueue();
	@Test
	public void test() throws Exception {
		
		String url="http://jsoup.org/cookbook";
		CrawlUrl u=new CrawlUrl();
		u.setOriUrl(url);
		System.out.println(queue.putUrl(u));
		System.out.println(queue.getNext().getOriUrl()+"d");
		
	}
}
