package com.xml.frame.report.util;

import junit.framework.TestCase;

public class MathUtilTest extends TestCase {

	public void testCurrency() {
		String currency = MathUtil.currency("12348781.2");
		System.out.println(currency);
		currency = MathUtil.currency("81.2123");
		System.out.println(currency);
		currency = MathUtil.currency("281.2123");
		System.out.println(currency);
		currency = MathUtil.currency("281");
		System.out.println(currency);
		currency = MathUtil.currency("81");
		System.out.println(currency);
		currency = MathUtil.currency("123281");
		System.out.println(currency);
	}

}
