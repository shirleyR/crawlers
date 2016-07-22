package com.hotswap;

public class PossibleReordering {
	static int x=0,y=0;
	static int a=0,b=0;
	public static void main(String args[])throws InterruptedException{
		Thread one =new Thread(new Runnable(){
			public void run(){
				System.out.println("oe");
				a=1;
				
				x=b;
			}
		});
		Thread two=new Thread(new Runnable(){
			public void run(){
				System.out.println("ee");
				b=1;
				System.out.println("dee");
				y=a;
			}
		});
		one.start();
		two.start();

		two.join();
		one.join();
		System.out.println(x+"  "+ y);
	}
}
