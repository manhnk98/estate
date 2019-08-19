package com.nkm.test;

public class Main {
	public static void main(String[] args) {
//		model.setTotalPage((int) Math.ceil((double) model.getTotalItem()/model.getMaxPageItem()));
		int TotalItem = 3;
		int MaxPageItem = 2;
		int a = (int)Math.ceil((double) TotalItem/MaxPageItem);
		System.out.println("a = "+a);
	}
}
