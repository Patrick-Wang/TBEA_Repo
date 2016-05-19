package com.tbea.ic.operation.service.convertor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.controller.servlet.convertor.Convertor;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
@Service
@Transactional("transactionManager")
public class ConvertorSeviceImpl implements ConvertorSevice{
	private static String pathNdjh = null;
	private static String pathYdjh = null;
	private static String pathYdsj = null;
	static 
	{
		try {
			pathNdjh = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/convertor/template/年度计划.xls";
			pathYdjh = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/convertor/template/月度计划.xls";
			pathYdsj = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/convertor/template/月度实际.xls";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Autowired
	ZBXXDao zbxxDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	private List<Integer> mergeZbs(Set<ZBXX> jh, Set<ZBXX> sj){
		List<Integer> ret = new ArrayList<Integer>();
		for (ZBXX zb : jh){
			if (!ret.contains(zb.getId())){
				ret.add(zb.getId());
			}
		}
		for (ZBXX zb : sj){
			if (!ret.contains(zb.getId())){
				ret.add(zb.getId());
			}
		}
		return ret;
	}
	
	private void convertNdjh(
			HSSFWorkbook destWorkbook, List<ZBXX> zbIds,
			HSSFRow rowSrc, 
			HSSFSheet destSheet, 
			StringBuilder resultBuilder) {
		String compName = rowSrc.getCell(0).getStringCellValue();
		DWXX dwxx = dwxxDao.getByName(compName);
		if (null == dwxx) {
			resultBuilder.append("<tr><td>" + compName
					+ "</td><td>公司不存在</td></tr>");
			return;
		}

		List<Integer> dwzbs = mergeZbs(dwxx.getJhzbxxs(), new HashSet<ZBXX>());
		Double year = rowSrc.getCell(1).getNumericCellValue();
		int columCount = rowSrc.getLastCellNum();
		HSSFDataFormat format = destWorkbook.createDataFormat();
		HSSFCellStyle style = destWorkbook.createCellStyle();
		style.setDataFormat(format.getFormat("yyyy-MM-dd"));
		for (int i = 2; i < columCount - 1; ++i) {
			if (null != zbIds.get(i - 2)) {
				if (dwzbs.contains(zbIds.get(i - 2).getId())) {
					HSSFCell cell = rowSrc.getCell(i);
					if (null == cell) {
						continue;
					}

					Double value = cell.getNumericCellValue();					
					if (null != value) {
						HSSFRow rowDest = destSheet.createRow(destSheet
								.getLastRowNum() + 1);
						cell = rowDest.createCell(0);
						cell.setCellValue(rowDest.getRowNum());
						cell = rowDest.createCell(1);
						cell.setCellValue(dwxx.getId());
						cell = rowDest.createCell(2);
						cell.setCellValue(zbIds.get(i - 2).getId());
						cell = rowDest.createCell(3);
						cell.setCellValue(year);
						cell = rowDest.createCell(4);
						cell.setCellValue(value);
						cell = rowDest.createCell(5);
						cell.setCellValue("");
						cell = rowDest.createCell(6);
//						cell.setCellValue(rowSrc.getCell(
//								rowSrc.getLastCellNum() - 1).getDateCellValue());
						cell.setCellValue("");
						cell.setCellStyle(style);

					} else {
						resultBuilder.append("<tr><td>" + compName
								+ "</td><td>" + zbIds.get(i - 2) + " 指标值为空 " +  "</td></tr>");
					}
				} else{
					resultBuilder.append("<tr><td>" + compName
							+ "</td><td>" + "不包含   “" + zbIds.get(i - 2).getName() +  "” 指标</td></tr>");
				}
			}
		}
	}

	
	private void convertYd(boolean jh, 
			HSSFWorkbook destWorkbook, List<ZBXX> zbIds,
			HSSFRow rowSrc, 
			HSSFSheet destSheet, 
			StringBuilder resultBuilder) {
		String compName = rowSrc.getCell(0).getStringCellValue();
		DWXX dwxx = dwxxDao.getByName(compName);
		if (null == dwxx) {
			resultBuilder.append("<tr><td>" + compName
					+ "</td><td>公司不存在</td></tr>");
			return;
		}

		List<Integer> dwzbs = null;
		if (jh){
			dwzbs = mergeZbs(dwxx.getJhzbxxs(), new HashSet<ZBXX>());
		} else {
			dwzbs = mergeZbs(new HashSet<ZBXX>(), dwxx.getSjzbxxs());
		}
		
		Double year = rowSrc.getCell(1).getNumericCellValue();
		Double month = rowSrc.getCell(2).getNumericCellValue();
		int columCount = zbIds.size() + 3;
		HSSFDataFormat format = destWorkbook.createDataFormat();
		HSSFCellStyle style = destWorkbook.createCellStyle();
		style.setDataFormat(format.getFormat("yyyy-MM-dd"));
		for (int i = 3; i < columCount; ++i) {
			if (null != zbIds.get(i - 3)) {
				if (dwzbs.contains(zbIds.get(i - 3).getId())) {
					HSSFCell cell = rowSrc.getCell(i);
					if (null == cell) {
						resultBuilder.append("<tr><td>" + compName
								+ "</td><td>" + zbIds.get(i - 3) + " 指标值为空 " +  "</td></tr>");
						continue;
					}
					Double value = cell.getNumericCellValue();
					if (null != value) {
						HSSFRow rowDest = destSheet.createRow(destSheet
								.getLastRowNum() + 1);
						cell = rowDest.createCell(0);
						cell.setCellValue(rowDest.getRowNum());
						cell = rowDest.createCell(1);
						cell.setCellValue(dwxx.getId());
						cell = rowDest.createCell(2);
						cell.setCellValue(zbIds.get(i - 3).getId());
						cell = rowDest.createCell(3);
						cell.setCellValue(year);
						cell = rowDest.createCell(4);
						cell.setCellValue(month);
						cell = rowDest.createCell(5);
						cell.setCellValue(value);
						cell = rowDest.createCell(6);
						cell.setCellValue("");
						cell = rowDest.createCell(7);
						cell.setCellValue("");
						//cell.setCellStyle(style);

					} else {
						resultBuilder.append("<tr><td>" + compName
								+ "</td><td>" + zbIds.get(i - 3) + " 指标值为空 " +  "</td></tr>");
					}
				} else{
					resultBuilder.append("<tr><td>" + compName
							+ "</td><td>" + "不包含   “" + zbIds.get(i - 3).getName() +  "” 指标</td></tr>");
				}
			}
		}
	}
	
	
	
	private String convertNdjh(HSSFWorkbook destWorkbook, HSSFWorkbook workbook){
		StringBuilder resultBuilder = new StringBuilder();
		for (int i = 0; i < workbook.getNumberOfSheets(); ++i) {

			HSSFSheet sheet = workbook.getSheetAt(i);
			if (sheet.getSheetName().contains("njh")) {
				int rowNum = sheet.getLastRowNum();
				HSSFRow titleId = sheet.getRow(0);
				HSSFRow title = sheet.getRow(1);
				List<ZBXX> zbxxs = new ArrayList<ZBXX>();
				for (int j = 2; j < titleId.getLastCellNum() - 1; ++j) {
					HSSFCell cell = titleId.getCell(j);
					ZBXX zbTmp = zbxxDao.getById((int) cell
							.getNumericCellValue());
					if (null == zbTmp) {
						cell = title.getCell(j);
						resultBuilder.append("<tr><td>"
								+ cell.getNumericCellValue() + "  "
								+ cell.getStringCellValue()
								+ "</td><td>指标不存在</td></tr>");
					}
					zbxxs.add(zbTmp);
				}

				HSSFSheet destSheet = destWorkbook.getSheetAt(0);

				for (int j = 2; j <= rowNum; ++j) {
					convertNdjh(destWorkbook, zbxxs, sheet.getRow(j), destSheet, resultBuilder);
				}
			}
		}
		return resultBuilder.toString();
	}
	
	@Override
	public String convertNdjh(HttpServletRequest request,
			HttpServletResponse response, String pathData) throws IOException {
		StringBuilder resultBuilder = new StringBuilder();
		HSSFWorkbook destWorkbook = new HSSFWorkbook(new FileInputStream(new File(
				pathNdjh)));		
		File dataDir = new File(pathData);
		File[] files = dataDir.listFiles();
		for (File f : files){
			String name = f.getName();
			if(f.isFile() && name.length() > 4 && ".xls".equals(name.substring(name.length() - 4))){
				resultBuilder.append("<tr><td>" + "文件名"
						+ "</td><td>" + name + "</td></tr>");
				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(f));
				resultBuilder.append(convertNdjh(destWorkbook, workbook));
			}
		}
		
		response.setContentType("application/octet-stream"); 
		response.setHeader("Content-disposition","attachment;filename=\"ndjh.xls\"");
		
		destWorkbook.write(response.getOutputStream());
		String log = resultBuilder.toString();
		if (log.isEmpty()){
			return "All is well";
		}
		return resultBuilder.toString();
	
	}

	
	private String convertYd(boolean jh, HSSFWorkbook destWorkbook, HSSFWorkbook workbook) throws IOException{
		StringBuilder resultBuilder = new StringBuilder();

		String jhsjTag = jh ? "yjh" : "ysj";
		for (int i = 0; i < workbook.getNumberOfSheets(); ++i){
			HSSFSheet sheet = workbook.getSheetAt(i);
			if (sheet.getSheetName().contains(jhsjTag)){
				int rowNum = sheet.getLastRowNum();
				HSSFRow titleId = sheet.getRow(0);
				HSSFRow title = sheet.getRow(1);
				List<ZBXX> zbxxs = new ArrayList<ZBXX>();
				for(int j = 3; j < titleId.getLastCellNum(); ++j){
					 HSSFCell cell = titleId.getCell(j);
					 ZBXX zbTmp  = zbxxDao.getById((int)cell.getNumericCellValue());
					 if (null == zbTmp){
						 HSSFCell cellTmp = title.getCell(j);
						 resultBuilder.append("<tr><td>" + cell.getNumericCellValue() + "  " + cellTmp.getStringCellValue() +  "</td><td>指标不存在</td></tr>");
					 }
					 zbxxs.add(zbTmp);
				}
				
				HSSFSheet destSheet = destWorkbook.getSheetAt(0);
				
				for(int j = 2; j <= rowNum; ++j){
					convertYd(jh, destWorkbook, zbxxs, sheet.getRow(j), destSheet, resultBuilder);
				}
			}
		}
		
	
		String log = resultBuilder.toString();
		if (log.isEmpty()){
			return "All is well";
		}
		return resultBuilder.toString();
	}
	
	@Override
	public String convertYdjh(HttpServletRequest request,
			HttpServletResponse response, String pathData) throws IOException {
		File dataDir = new File(pathData);
		File[] files = dataDir.listFiles();
		StringBuilder resultBuilder = new StringBuilder();
		HSSFWorkbook destWorkbook = new HSSFWorkbook(new FileInputStream(new File(
				pathYdjh)));
		for (File f : files){
			String name = f.getName();
			if(f.isFile() && name.length() > 4 && ".xls".equals(name.substring(name.length() - 4))){
				resultBuilder.append("<tr><td>" + "文件名"
						+ "</td><td>" + name + "</td></tr>");
				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(f));
				resultBuilder.append(convertYd(true, destWorkbook, workbook));
			}
		}
		
		response.setContentType("application/octet-stream"); 
		response.setHeader("Content-disposition","attachment;filename=\"" + "ydjh.xls" + "\"");
		
		destWorkbook.write(response.getOutputStream());
		return resultBuilder.toString();
	}

	@Override
	public String convertYdsj(HttpServletRequest request,
			HttpServletResponse response, String pathData) throws IOException {		
		File dataDir = new File(pathData);
		File[] files = dataDir.listFiles();
		StringBuilder resultBuilder = new StringBuilder();
		HSSFWorkbook destWorkbook = new HSSFWorkbook(new FileInputStream(new File(
				pathYdsj)));
		for (File f : files){
			String name = f.getName();
			if(f.isFile() && name.length() > 4 && ".xls".equals(name.substring(name.length() - 4))){
				resultBuilder.append("<tr><td>" + "文件名"
						+ "</td><td>" + name + "</td></tr>");
				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(f));
				resultBuilder.append(convertYd(false, destWorkbook, workbook));
			}
		}
		
		response.setContentType("application/octet-stream"); 
		response.setHeader("Content-disposition","attachment;filename=\"" + "ydsj.xls" + "\"");
		
		destWorkbook.write(response.getOutputStream());
		return resultBuilder.toString();
		
	}
}
