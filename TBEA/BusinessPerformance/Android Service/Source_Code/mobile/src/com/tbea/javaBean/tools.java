package com.tbea.javaBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class tools {
	public static boolean isEmpty(String source){
		boolean flag=false;
		if(source!=null&&!source.equals("")&&!source.equals("NULL")&&!source.equals("null")&&!source.equals(" ")){
			flag=true;
		}
		return flag;
	}
	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ����ʱ������ yyyy-MM-dd
	 */
	public static String getNowDate() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);	  
	   return dateString;
	}
	/**
	 * ���������
	 * 
	 * @return ���������
	 */
	public static String getRandomString(int length) 
	{ 
	String str = "0123456789"; 
	Random random = new Random(); 
	StringBuffer sb = new StringBuffer(); 

	for (int i = 0; i < length; ++i) 
	{ 
	int number = random.nextInt(10); 

	sb.append(str.charAt(number)); 
	} 

	return sb.toString(); 
	} 

	public static String getNewString(String input) throws Exception{
		String result = null;	
		result = new String(input.getBytes("ISO8859_1"),"gb2312");				
		return result;			
	}
}
