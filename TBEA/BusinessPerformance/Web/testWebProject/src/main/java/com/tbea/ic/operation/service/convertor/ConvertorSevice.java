package com.tbea.ic.operation.service.convertor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ConvertorSevice {

	String convertNdjh(HttpServletRequest request, HttpServletResponse response)
			throws IOException;

	String convertYdjh(HttpServletRequest request, HttpServletResponse response) throws IOException;

	String convertYdsj(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
