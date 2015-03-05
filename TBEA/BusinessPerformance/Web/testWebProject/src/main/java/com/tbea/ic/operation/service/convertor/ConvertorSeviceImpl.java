package com.tbea.ic.operation.service.convertor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
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
	private static String pathNdjhRaw = null;
	static 
	{
		try {
			pathNdjh = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/年度计划.xls";
			pathNdjhRaw = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/年度指标计划.xls";
			System.out.println(pathNdjh);
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
	
	private void convertNdjh(HSSFWorkbook destWorkbook, List<ZBXX> zbIds, HSSFRow rowSrc, HSSFSheet destSheet, StringBuilder resultBuilder){
		String compName = rowSrc.getCell(0).getStringCellValue();
		DWXX dwxx = dwxxDao.getByName(compName);
		if (null == dwxx){
			resultBuilder.append("<tr><td>" + compName + "</td><td>公司不存在</td></tr>");
			return;
		}
		
		List<Integer> dwzbs = mergeZbs(dwxx.getJhzbxxs(), dwxx.getSjzbxxs());
		Double year = rowSrc.getCell(1).getNumericCellValue();
		int columCount = rowSrc.getLastCellNum();
		for(int i = 2; i < columCount - 3; ++i){
			if (null != zbIds.get(i - 2)){
				if (dwzbs.contains(zbIds.get(i - 2).getId())){
					 HSSFCell cell = rowSrc.getCell(i);
					 if (null == cell){
						 continue;
					 }
					 Double value = cell.getNumericCellValue();
					 if (null != value){
						 HSSFRow rowDest = destSheet.createRow(destSheet.getLastRowNum() + 1);
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
						 HSSFDataFormat format = destWorkbook.createDataFormat(); 
						 cell.setCellValue(rowSrc.getCell(rowSrc.getLastCellNum() - 3).getDateCellValue());
						 cell.getCellStyle().setDataFormat(format.getFormat("yyyy-MM-dd"));
						 
					 }
				}
			}	
		}
	}

	@Override
	public String convertNdjh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		StringBuilder resultBuilder = new StringBuilder();
		HSSFWorkbook destWorkbook = new HSSFWorkbook(new FileInputStream(new File(
				pathNdjh)));
		do {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(
					pathNdjhRaw)));
			HSSFSheet sheet = workbook.getSheetAt(0);
			if (null == sheet){
				resultBuilder.append("<tr><td>" + "没有sheet页</td></tr>");
				break;
			}
			int rowNum = sheet.getLastRowNum();
			HSSFRow title = sheet.getRow(0);
			List<ZBXX> zbxxs = new ArrayList<ZBXX>();
			for(int i = 2; i <= title.getLastCellNum() - 3; ++i){
				 HSSFCell cell = title.getCell(i);
				 ZBXX zbTmp  = zbxxDao.getZbByName(cell.getStringCellValue());
				 if (null == zbTmp){
					 resultBuilder.append("<tr><td>" + cell.getStringCellValue() + "</td><td>指标不存在</td></tr>");
				 }
				 zbxxs.add(zbTmp);
			}
			
			HSSFSheet destSheet = destWorkbook.getSheetAt(0);
			
			for(int i = 1; i < rowNum; ++i){
				convertNdjh(destWorkbook, zbxxs, sheet.getRow(i), destSheet, resultBuilder);
			}
			
			
		}while(false);
		response.setContentType("application/octet-stream"); 
		response.setHeader("Content-disposition","attachment;filename=\"ndjz.xls\"");
		
		destWorkbook.write(response.getOutputStream());
		
		return resultBuilder.toString();
	
	}
}
