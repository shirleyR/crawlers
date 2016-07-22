package com.crawlers.crawlers;

/**
 * Created by rsl_pc on 2016/7/17.
 */
public class CrawlUrl {
    private static final long serialVersionUID = 79332323432323L;

    public CrawlUrl() {

    }
    private int depth;
    
    public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	private String oriUrl;    //原始url

    private String url;       //url地址

    public String getOriUrl() {
        return oriUrl;
    }
    public void setOriUrl(String oriUrl) {
        this.oriUrl = oriUrl;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
