package com.tbea.ic.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class Verifier {
	String sToken = "nH5";
	String sCorpID = "wx40b71464a42adcf3";
	String sEncodingAESKey = "fDYqXp5YJizTlMTLKu9b2L8RMH8jUaB1mivzfzV9As0";

	public Verifier(String sToken, String sCorpID, String sEncodingAESKey) {
		super();
		this.sToken = sToken;
		this.sCorpID = sCorpID;
		this.sEncodingAESKey = sEncodingAESKey;
	}

	public void verify(HttpServletRequest request, HttpServletResponse response)
			throws AesException, IOException {
		WXBizMsgCrypt wxcpt = null;
		try {
			wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String sVerifyMsgSig = HttpUtils.ParseUrl("msg_signature");
		String sVerifyMsgSig = request.getParameter("msg_signature");
		// String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
		String sVerifyTimeStamp = request.getParameter("timestamp");
		// String sVerifyNonce = HttpUtils.ParseUrl("nonce");
		String sVerifyNonce = request.getParameter("nonce");
		// String sVerifyEchoStr = HttpUtils.ParseUrl("echostr");
		String sVerifyEchoStr = request.getParameter("echostr");

		String sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
				sVerifyNonce, sVerifyEchoStr);
		response.getWriter().write(sEchoStr);
	}
}
