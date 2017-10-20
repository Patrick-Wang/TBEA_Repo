package com.speed.frame.common;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

public class AjaxRedirect {
	private String error = "invalidate session";
	private String redirect = "";
	public AjaxRedirect(String error, String redirect) {
		super();
		this.error = error;
		this.redirect = redirect;
	}
	public AjaxRedirect(String redirect) {
		super();
		this.redirect = redirect;
	}
	
	public AjaxRedirect() {
		super();
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @return the redirect
	 */
	public String getRedirect() {
		return redirect;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @param redirect the redirect to set
	 */
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	
	public byte[] toUtf8Bytes() throws UnsupportedEncodingException{
		return JSONObject.fromObject(this).toString().getBytes("utf-8");
	}
}
