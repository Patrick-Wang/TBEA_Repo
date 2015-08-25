package com.tbea.ic.operation.service.market;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






import com.tbea.ic.operation.model.dao.market.bidInfo.MktBidInfoDao;
import com.tbea.ic.operation.model.dao.market.projectInfo.MktProjectInfoDao;
import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.model.entity.MktProjectInfo;
import com.tbea.ic.operation.model.entity.MktSignContract;

@Service
@Transactional("transactionManager")
public class MarketServiceImpl implements MarketService {

	@Autowired
	private MktBidInfoDao bidInfoDao;

	@Autowired
	private MktProjectInfoDao projectInfoDao;

	@Autowired
	private MktSignContractDao signContractDao;
	
	private final String OK = "OK";
	
	private String validate(HSSFWorkbook workbook, Class<?> cls){
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		String result = "OK";
		if (!(row.getLastCellNum() == cls.getDeclaredFields().length)){
			result = "column count doesn't match";
		}
		return result;
	}
	
	@Override
	public String importProjectData(HSSFWorkbook workbook) {
		String result = validate(workbook, MktProjectInfo.class);
		if (OK.equals(result)){
			
		}
		return result;
	}

	
	
	
	@Override
	public String importSignData(HSSFWorkbook workbook) {
		String result = validate(workbook, MktSignContract.class);
		if (OK.equals(result)){
			
		}
		return result;
	}

	
	private MktBidInfo createMktBidInfo(HSSFRow row){
		MktBidInfo bidInfo = new MktBidInfo();
		Field[] fields = MktBidInfo.class.getDeclaredFields();

		for (int i = 0; i < row.getLastCellNum(); ++i) {
			Field field = fields[i];
			HSSFCell cell = row.getCell(i);
			String val = cell.getStringCellValue();
			if (null != val && !val.isEmpty()) {
				String name = field.getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				try {
					Method method = MktBidInfo.class.getMethod(name,
							field.getType());

					if (field.getType().getName()
							.equals(Integer.class.getName())) {
						method.invoke(bidInfo, Integer.valueOf(val));
					} else if (field.getType().getName()
							.equals(Double.class.getName())) {
						method.invoke(bidInfo, Double.valueOf(val));
					} else if (field.getType().getName()
							.equals(String.class.getName())) {
						method.invoke(bidInfo, val);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return bidInfo;
	}

	@Override
	public String importBidData(HSSFWorkbook workbook) {
		String result = validate(workbook, MktBidInfo.class);
		if (OK.equals(result)){
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); ++i){
				HSSFRow row = sheet.getRow(i);
				bidInfoDao.update(createMktBidInfo(row));
			}
		}
		return result;
	}

}
