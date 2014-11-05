package com.tbea.test.testWebProject.service.ydzb;

import java.util.ArrayList;
import java.util.List;

//5	SB	沈变公司
//6	HB	衡变公司
//7	XB	新变厂
//8	TB	天变公司
//9	LL	鲁缆公司
//10	XL	新缆厂
//11	DL	德缆公司
//23	JCK	进出口公司
//25	NDGS	能动公司
//27	ZH	众和公司
//29	XNY	新能源
//30	GY	新特能源公司
//66	TCNY	天池能源
//70	GCGS	国际工程公司
//74	ZJWL	中疆物流


class Array{
	List<int[]> list = new ArrayList<int[]>();
	private int len = 0;
	public Array(int[] arr){
		list.add(arr);
		len += arr.length;
	}
	
	public Array join(int[] arr){
		list.add(arr);
		len += arr.length;
		return this;
	}
	
	public int[] toArray(){
		int[] ret = new int[len];
		int base = 0;
		for (int[] arr : list){
			for (int i = 0; i < arr.length; ++i){
				ret[base] = arr[i];
				base++;
			}
		}
		return ret;
	}
}

public class Company {
	public static int SB = 5;
	public static int HB = 6;
	public static int XB = 7;
	public static int TB = 8;
	public static int LL = 9;
	public static int XL = 10;
	public static int DL = 11;
	public static int JCK = 23;
	public static int NDGS = 25;
	public static int ZH = 27;
	public static int XNY = 29;
	public static int GY = 30;
	public static int TCNY = 66;
	public static int GCGS = 70;
	public static int ZJWL = 74;

	public static int[] getAll() {
		return new int[] { SB, HB, XB, TB, LL, XL, DL, JCK, NDGS, ZH, XNY, GY,
				TCNY, GCGS, ZJWL };
	}

	public static int[] getSbdcy() {
		return new int[] { SB, HB, XB, TB, LL, XL, DL };
	}

	public static int[] getXnycy() {
		return new int[] { GY, XNY };
	}

	public static int[] getNycy() {
		return new int[] { TCNY, NDGS, ZJWL };
	}

	public static int[] getGcl() {
		return new int[] { JCK, GCGS };
	}
}
