package com.hotswap;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

public class HotswapCJ extends ClassLoader{
	private String basedir;
	private HashSet dynaclazns;
	
	/**
	 * 加载器加载的class文件在哪，并且依次加载
	 */
	public HotswapCJ(){
		super(null);
		dynaclazns=new HashSet();
	}
	 public void loadClassByStr(String [] clazns) throws FileNotFoundException, IOException {
		 
		 
	 }   
	  public void loadClassByStr(List clazns) throws FileNotFoundException, IOException {
		  
		  
	  }
	
}
