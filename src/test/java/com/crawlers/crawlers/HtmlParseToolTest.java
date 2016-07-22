package com.crawlers.crawlers;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import org.junit.*;

public class HtmlParseToolTest {
	private static HtmlParseTool test=new HtmlParseTool();
	@Test
	public void testExtractLinks(){
		String url="http://www.open-open.com/jsoup/example-list-links.htm";
		LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
            
                Pattern pattern = Pattern.compile("^((https|http|ftp|rtsp|mms)?://)"
                        + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                        + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"
                        + "|"
                        + "([0-9a-z_!~*'()-]+\\.)*"
                        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."
                        + "[a-z]{2,6})"
                        + "(:[0-9]{1,4})?"
                        + "((/?)|"
                        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"); 
                Matcher matcher = pattern.matcher(url);
                boolean isMatch= matcher.matches();
                if(isMatch && url.startsWith(CrawlConfig.CRAWL_LIMIT_PATH)) {
                    return true;
                }
                else {
                    return false;
                }
            }
        };
        
        test.extractLinks(url,filter);
        
        
	}
}
