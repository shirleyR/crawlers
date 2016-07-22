package com.crawlers.Threads;
import java.util.*;
public class MyStack {
	private List	list=new ArrayList();
	synchronized public void push(){
		try{
			while(list.size()==1){
				this.wait();
			}
			list.add("ddddd");
			this.notifyAll();
			System.out.println("push");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	synchronized public String pop(){
		String returnValue="";
		try{
			while(list.size()==0){
				System.out.println("pop"+Thread.currentThread().getName()+" wait");
				this.wait();
			}
			returnValue=""+list.get(0);
			list.remove(0);
			this.notifyAll();
			System.out.println("pop="+list.size());
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return returnValue;
	}
}
