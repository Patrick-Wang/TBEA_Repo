package com.xml.frame.report.component.entity;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class WebResponse {

    int code;
    String msg;
    ByteArrayOutputStream result;
    ByteArrayOutputStream error;
    Map<String,List<String>> headers;

    public WebResponse() {
    }

    public void setCode(int responseCode) {
        this.code = responseCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(ByteArrayOutputStream result) {
        this.result = result;
    }

    public void setError(ByteArrayOutputStream error) {
        this.error = error;
    }

    public void setHeaders(Map<String,List<String>> headers) {
        this.headers = headers;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getHeader(String name){
        return headers.get(name).get(0);
    }

    public Map<String,List<String>> getHeaders(){
        return headers;
    }

    public String getUtf8Result() throws UnsupportedEncodingException {
        return new String(result.toByteArray(), "utf-8");
    }

    public byte[] getResult(){
        return result == null ? null : result.toByteArray();
    }

    public byte[] getError(){
        return error == null ? null : error.toByteArray();
    }

    public String getUtf8Error() throws UnsupportedEncodingException {
        return new String(error.toByteArray(), "utf-8");
    }
}
