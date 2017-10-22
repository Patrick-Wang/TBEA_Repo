package com.frame.script.util;

import java.io.IOException;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

public class Util {

	public static String toStringFromDoc(Element elem) {
		String result = null;

		StringWriter strWtr = new StringWriter();
		StreamResult strResult = new StreamResult(strWtr);
		TransformerFactory tfac = TransformerFactory.newInstance();
		try {
			javax.xml.transform.Transformer t = tfac.newTransformer();
			t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
			// text
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			t.transform(new DOMSource(elem), strResult);
		} catch (Exception e) {
			System.err.println("XML.toString(Document): " + e);
		}
		result = strResult.getWriter().toString();
		try {
			strWtr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String tableName2ComponentName(String tbName) {
		String[] names = tbName.split("_");
		String componentName = names[0];
		for (int i = 1; i < names.length; ++i) {
			componentName += names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
		}
		return getMD5(componentName);
	}

	public static String join(List list) {
		String ret = "";
		for (Object item : list) {
			ret += "," + item;
		}
		return ret.substring(1);
	}

	/**
	 * 生成md5
	 * 
	 * @param message
	 * @return
	 */
	public static String getMD5(String message) {
		String md5str = "";
		try {
			// 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 2 将消息变成byte数组
			byte[] input = message.getBytes();

			// 3 计算后获得字节数组,这就是那128位了
			byte[] buff = md.digest(input);

			// 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
			md5str = bytesToHex(buff);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	/**
	 * 二进制转十六进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];

			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}
}
