package com.crawlers.crawlers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.*;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rsl_pc on 2016/7/17.
 */
public class HtmlParseTool {
    public static Set<String> extractLinks(String url,LinkFilter filter){
        Set<String> links=new HashSet<String>();
        try{
            Document doc=Jsoup.connect(url).get();
            Elements link=doc.select("a[href]");
        
            Elements media=doc.select("[src]");
            Elements imports=doc.select("link[href]");
//            //media  about img 
//            System.out.println(link.size());
//            System.out.println(media.size());
//            System.out.print(imports.size());
            for(Element src :media){
//            	System.out.println(src.attr("abs:src"));
            	String tempUrl=src.attr("abs:src");
            	if(filter.accept(tempUrl)){
            		links.add(tempUrl);
            	}
            }
            for(Element ip:imports){
//            	System.out.println(ip.attr("abs:href"));
            	String tempUrl=ip.attr("abs:href");
            	if(filter.accept(tempUrl)){
            		links.add(tempUrl);
            	}
            }
            // links
            for(Element l :link){
//            	System.out.println(l.attr("abs:href"));
            	String tempUrl=l.attr("abs:href");
            	if(filter.accept(tempUrl)){
            		links.add(tempUrl);
            	}
            }
        }catch(IOException e){
        	e.printStackTrace();
        }
        return links;
    }
   
}
