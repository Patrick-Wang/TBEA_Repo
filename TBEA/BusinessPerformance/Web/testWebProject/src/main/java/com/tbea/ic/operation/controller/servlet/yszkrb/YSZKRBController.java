package com.tbea.ic.operation.controller.servlet.yszkrb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.JYGKPhase2SheetType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.service.yszkrb.YSZKRBService;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.excel.FormatterHandler;
import com.xml.frame.report.util.excel.HeaderFormatterHandler;
import com.xml.frame.report.util.excel.NumberFormatterHandler;
import com.xml.frame.report.util.excel.PercentFormatterHandler;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "yszkrb")
public class YSZKRBController {
	
	@Autowired
	private YSZKRBService yszkrbService;

	@RequestMapping(value = {"yszk.do", "v2/yszk.do"}, method = RequestMethod.GET)
	public ModelAndView getYszk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		DateSelection dateSel = new DateSelection();
		Map<String, Object> map = new HashMap<String, Object>();
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "yszkrb_view", map);
	}

	@RequestMapping(value ="yszk_update.do")
	public @ResponseBody byte[] getYszkUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date date = DateSelection.getDate(request);
		List<String[]> data = yszkrbService.getYszkData(date, SessionManager.getAccount(request.getSession()));
		return JSONArray.fromObject(data).toString().replace("null", "\"--\"").getBytes("utf-8");
	}

	/**
	 * 新添加应收日报展示
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value ="yszkrb_update.do")
	public @ResponseBody byte[] getYszkRbUpdate(HttpServletRequest request,
											  HttpServletResponse response) throws UnsupportedEncodingException {
		Date date = DateSelection.getDate(request);
		List<String[]> data = yszkrbService.getYszkRbData(date, SessionManager.getAccount(request.getSession()));
		return JSONArray.fromObject(data).toString().replace("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszk_view_export.do")
	public @ResponseBody byte[] getyszk_view_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = DateSelection.getDate(request);
		List<String[]> data = yszkrbService.getYszkData(d, SessionManager.getAccount(request.getSession()));
		if (data.size() > 1){
		
			ExcelHelper template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.YSDialy);
			String fileAndSheetName = (1900 + d.getYear()) + "-" + (d.getMonth() + 1) +"-" + d.getDate() + "应收账款日报";
			
			HSSFWorkbook workbook = template.getWorkbook();
			workbook.setSheetName(0, fileAndSheetName);
			HSSFSheet sheet = workbook.getSheetAt(0);

			for(int i = 0; i < data.size(); i++){
				if(i == 7){
					yszkrbAllDwFormatHeji(data,0,i);
				}else if(i == 10){
					yszkrbAllDwFormatHeji(data,8,i);
				}else if(i == 13){
					yszkrbAllDwFormatHeji(data,11,i);
				}else if(i == 16){
					yszkrbAllDwFormatHeji(data,14,i);
				}else if(i == 18){
					data.get(i)[0] = Double.valueOf(data.get(7)[0]==null?"0.0":data.get(7)[0])+Double.valueOf(data.get(10)[0]==null?"0.0":data.get(10)[0])+Double.valueOf(data.get(13)[0]==null?"0.0":data.get(13)[0])+Double.valueOf(data.get(16)[0]==null?"0.0":data.get(16)[0])+"";
					data.get(i)[1] = Double.valueOf(data.get(7)[1]==null?"0.0":data.get(7)[1])+Double.valueOf(data.get(10)[1]==null?"0.0":data.get(10)[1])+Double.valueOf(data.get(13)[1]==null?"0.0":data.get(13)[1])+Double.valueOf(data.get(16)[1]==null?"0.0":data.get(16)[1])+"";
					data.get(i)[2] = Double.valueOf(data.get(7)[2]==null?"0.0":data.get(7)[2])+Double.valueOf(data.get(10)[2]==null?"0.0":data.get(10)[2])+Double.valueOf(data.get(13)[2]==null?"0.0":data.get(13)[2])+Double.valueOf(data.get(16)[2]==null?"0.0":data.get(16)[2])+"";
					data.get(i)[3] = Double.valueOf(data.get(7)[3]==null?"0.0":data.get(7)[3])+Double.valueOf(data.get(10)[3]==null?"0.0":data.get(10)[3])+Double.valueOf(data.get(13)[3]==null?"0.0":data.get(13)[3])+Double.valueOf(data.get(16)[3]==null?"0.0":data.get(16)[3])+"";
					data.get(i)[4] = Double.valueOf(data.get(7)[4]==null?"0.0":data.get(7)[4])+Double.valueOf(data.get(10)[4]==null?"0.0":data.get(10)[4])+Double.valueOf(data.get(13)[4]==null?"0.0":data.get(13)[4])+Double.valueOf(data.get(16)[4]==null?"0.0":data.get(16)[4])+"";
					data.get(i)[5] = Double.valueOf(data.get(7)[5]==null?"0.0":data.get(7)[5])+Double.valueOf(data.get(10)[5]==null?"0.0":data.get(10)[5])+Double.valueOf(data.get(13)[5]==null?"0.0":data.get(13)[5])+Double.valueOf(data.get(16)[5]==null?"0.0":data.get(16)[5])+"";
					data.get(i)[6] = Double.valueOf(data.get(7)[6]==null?"0.0":data.get(7)[6])+Double.valueOf(data.get(10)[6]==null?"0.0":data.get(10)[6])+Double.valueOf(data.get(13)[6]==null?"0.0":data.get(13)[6])+Double.valueOf(data.get(16)[6]==null?"0.0":data.get(16)[6])+"";
					data.get(i)[7] = Double.valueOf(data.get(7)[7]==null?"0.0":data.get(7)[7])+Double.valueOf(data.get(10)[7]==null?"0.0":data.get(10)[7])+Double.valueOf(data.get(13)[7]==null?"0.0":data.get(13)[7])+Double.valueOf(data.get(16)[7]==null?"0.0":data.get(16)[7])+"";
					data.get(i)[8] = Double.valueOf(data.get(7)[8]==null?"0.0":data.get(7)[8])+Double.valueOf(data.get(10)[8]==null?"0.0":data.get(10)[8])+Double.valueOf(data.get(13)[8]==null?"0.0":data.get(13)[8])+Double.valueOf(data.get(16)[8]==null?"0.0":data.get(16)[8])+"";
					data.get(i)[10] = Double.valueOf(data.get(7)[10]==null?"0.0":data.get(7)[10])+Double.valueOf(data.get(10)[10]==null?"0.0":data.get(10)[10])+Double.valueOf(data.get(13)[10]==null?"0.0":data.get(13)[10])+Double.valueOf(data.get(16)[10]==null?"0.0":data.get(16)[10])+"";
					data.get(i)[11] = Double.valueOf(data.get(7)[11]==null?"0.0":data.get(7)[11])+Double.valueOf(data.get(10)[11]==null?"0.0":data.get(10)[11])+Double.valueOf(data.get(13)[11]==null?"0.0":data.get(13)[11])+Double.valueOf(data.get(16)[11]==null?"0.0":data.get(16)[11])+"";
					if(data.get(i)[0].equals("0.0")){data.get(i)[0]=null;}
					if(data.get(i)[1].equals("0.0")){data.get(i)[1]=null;}
					if(data.get(i)[2].equals("0.0")){data.get(i)[2]=null;}
					if(data.get(i)[3].equals("0.0")){data.get(i)[3]=null;}
					if(data.get(i)[4].equals("0.0")){data.get(i)[4]=null;}
					if(data.get(i)[5].equals("0.0")){data.get(i)[5]=null;}
					if(data.get(i)[6].equals("0.0")){data.get(i)[6]=null;}
					if(data.get(i)[7].equals("0.0")){data.get(i)[7]=null;}
					if(data.get(i)[8].equals("0.0")){data.get(i)[8]=null;}
					if(data.get(i)[10].equals("0.0")){data.get(i)[10]=null;}
					if(data.get(i)[11].equals("0.0")){data.get(i)[11]=null;}
					if(data.get(i)[1]==null || data.get(i)[8]==null){data.get(i)[9]=null;}else{data.get(i)[9]=Double.valueOf(data.get(i)[8])/Double.valueOf(data.get(i)[1])+"";}
					if(data.get(i)[0]==null || data.get(i)[11]==null){data.get(i)[12]=null;}else{data.get(i)[12]=Double.valueOf(data.get(i)[11])/Double.valueOf(data.get(i)[0])+"";}
				}
			}
			for(int i = 0; i < data.size(); i++){
				for(int j = 0; j < data.get(i).length; j++){
					if(j == 9 || j ==12){
						if(data.get(i)[j] != null){
							double dd = Double.valueOf(data.get(i)[j]);
							double newd = dd*100;
							java.text.DecimalFormat df =new java.text.DecimalFormat("0.0");
							data.get(i)[j] = df.format(newd)+"%";
						}
					}
				}
			}
			for (int i = 0, ilen = data.size(); i < ilen; ++i) {
				HSSFRow row = sheet.getRow(1 + i);
				for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
					HSSFCell cell = row.createCell(j+1);
					cell.setCellValue(data.get(i)[j]);
				}
			}
				
			template.write(response, fileAndSheetName + ".xls");
		}else{
			ExcelHelper template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.YSDialyJydw);
			String fileAndSheetName = (1900 + d.getYear()) + "-" + (d.getMonth() + 1) +"-" + d.getDate() + "应收账款日报";

			HSSFWorkbook workbook = template.getWorkbook();
			workbook.setSheetName(0, fileAndSheetName);
			HSSFSheet sheet = workbook.getSheetAt(0);

			for (int i = 0, ilen = data.size(); i < ilen; ++i) {
				HSSFRow row = sheet.getRow(1 + i);
				HSSFCell cell = row.getCell(0);
				cell.setCellValue(data.get(i)[0]);
				for (int j = 1, jlen = data.get(i).length; j < jlen; ++j) {
					cell = row.createCell(j);
					if(j == 10 || j ==13){
						if(data.get(i)[j] != null){
							double dd = Double.valueOf(data.get(i)[j]);
							double newd = dd*100;
							java.text.DecimalFormat df =new java.text.DecimalFormat("0.0");
							data.get(i)[j] = df.format(newd)+"%";
						}
					}
					cell.setCellValue(data.get(i)[j]);
				}
			}
				
			template.write(response, fileAndSheetName + ".xls");
		}
		return "".getBytes("utf-8");
	}
	
	private FormatterHandler getFormatterChainWithHeader(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new HeaderFormatterHandler(null, new Integer[]{0});
		formatterChain.next(getFormatterChainDataOnly(percentCols, jhCols));
		return formatterChain;
	}
	
	private FormatterHandler getFormatterChainDataOnly(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new PercentFormatterHandler(null, percentCols);
		formatterChain.next(new NumberFormatterHandler(0));
		return formatterChain;
	}

	/**
	 * 将得到全部公司的日报信息进行格式化与合计重新处理
	 * @param s
	 * @param start
	 * @param end
	 */
	private void yszkrbAllDwFormatHeji(List<String[]> s, int start,int end){
		double yszkzbSum = 0.0,hkjhSum = 0.0,qbkxSum = 0.0,zqkxSum = 0.0,syysyeSum = 0.0,jrxzyszkSum = 0.0,byljxzysSum = 0.0
				,jrhkSum = 0.0,ljhkSum = 0.0,hkwclSum = 0.0,ljkjyshkSum = 0.0,jryszkyeSum = 0.0,yszkwclSum = 0.0;
		for(int j = start; j < end; j++){
			if(s.get(j)[0]==null){yszkzbSum = yszkzbSum + 0.0;}else{yszkzbSum = yszkzbSum + Double.valueOf(s.get(j)[0]);}
			if(s.get(j)[1]==null){hkjhSum = hkjhSum + 0.0;}else{hkjhSum = hkjhSum + Double.valueOf(s.get(j)[1]);}
			if(s.get(j)[2]==null){qbkxSum = qbkxSum + 0.0;}else{qbkxSum = qbkxSum + Double.valueOf(s.get(j)[2]);}
			if(s.get(j)[3]==null){zqkxSum = zqkxSum + 0.0;}else{zqkxSum = zqkxSum + Double.valueOf(s.get(j)[3]);}
			if(s.get(j)[4]==null){syysyeSum = syysyeSum + 0.0;}else{syysyeSum = syysyeSum + Double.valueOf(s.get(j)[4]);}
			if(s.get(j)[5]==null){jrxzyszkSum = jrxzyszkSum + 0.0;}else{jrxzyszkSum = jrxzyszkSum + Double.valueOf(s.get(j)[5]);}
			if(s.get(j)[6]==null){byljxzysSum = byljxzysSum + 0.0;}else{byljxzysSum = byljxzysSum + Double.valueOf(s.get(j)[6]);}
			if(s.get(j)[7]==null){jrhkSum = jrhkSum + 0.0;}else{jrhkSum = jrhkSum + Double.valueOf(s.get(j)[7]);}
			if(s.get(j)[8]==null){ljhkSum = ljhkSum + 0.0;}else{ljhkSum = ljhkSum + Double.valueOf(s.get(j)[8]);}
			if(s.get(j)[9]==null){hkwclSum = hkwclSum + 0.0;}else{hkwclSum = hkwclSum + Double.valueOf(s.get(j)[9]);}
			if(s.get(j)[10]==null){ljkjyshkSum = ljkjyshkSum + 0.0;}else{ljkjyshkSum = ljkjyshkSum + Double.valueOf(s.get(j)[10]);}
			if(s.get(j)[11]==null){jryszkyeSum = jryszkyeSum + 0.0;}else{jryszkyeSum = jryszkyeSum + Double.valueOf(s.get(j)[11]);}
			if(s.get(j)[12]==null){yszkwclSum = yszkwclSum + 0.0;}else{yszkwclSum = yszkwclSum + Double.valueOf(s.get(j)[12]);}
		}
		if(yszkzbSum==0.0){s.get(end)[0]=null;s.get(end)[12]=null;}else{s.get(end)[0] = yszkzbSum+"";if(jryszkyeSum/yszkzbSum==0.0){s.get(end)[12]=null;}else{s.get(end)[12]=jryszkyeSum/yszkzbSum+"";}}
		if(hkjhSum==0.0){s.get(end)[1]=null;s.get(end)[9]=null;}else{s.get(end)[1] = hkjhSum+"";if(ljhkSum/hkjhSum==0.0){s.get(end)[9]=null;}else{s.get(end)[9]=ljhkSum/hkjhSum+"";}}
		if(qbkxSum==0.0){s.get(end)[2]=null;}else{s.get(end)[2] = qbkxSum+"";}
		if(zqkxSum==0.0){s.get(end)[3]=null;}else{s.get(end)[3] = zqkxSum+"";}
		if(syysyeSum==0.0){s.get(end)[4]=null;}else{s.get(end)[4] = syysyeSum+"";}
		if(jrxzyszkSum==0.0){s.get(end)[5]=null;}else{s.get(end)[5] = jrxzyszkSum+"";}
		if(byljxzysSum==0.0){s.get(end)[6]=null;}else{s.get(end)[6] = byljxzysSum+"";}
		if(jrhkSum==0.0){s.get(end)[7]=null;}else{s.get(end)[7] = jrhkSum+"";}
		if(ljhkSum==0.0){s.get(end)[8]=null;}else{s.get(end)[8] = ljhkSum+"";}
		if(ljkjyshkSum==0.0){s.get(end)[10]=null;}else{s.get(end)[10] = ljkjyshkSum+"";}
		if(jryszkyeSum==0.0){s.get(end)[11]=null;}else{s.get(end)[11] = jryszkyeSum+"";}
		if(yszkzbSum==0.0){s.get(end)[0]=null;}else{s.get(end)[0] = yszkzbSum+"";}
	}
}
