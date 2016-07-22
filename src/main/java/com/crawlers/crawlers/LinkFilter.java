package com.crawlers.crawlers;

/**
 * Created by rsl_pc on 2016/7/17.
 */
public interface LinkFilter {
    public boolean accept(String url);
}
