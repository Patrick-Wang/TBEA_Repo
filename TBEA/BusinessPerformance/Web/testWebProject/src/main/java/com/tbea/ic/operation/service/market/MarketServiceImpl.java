package com.tbea.ic.operation.service.market;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
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
	
	private final String ERROR_OK = "OK";
	private final String ERROR_COUNT_NOT_MATCH = "column count doesn't match";
	
	
	interface OnUpdateMktObjectListener{
		Class<?> onGetClass();
		void update(Object createMktObject);
	}
	
	private String validate(XSSFWorkbook workbook, Class<?> cls){
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		String result = ERROR_OK;
		int lastCellNum = row.getLastCellNum();
		Field[] fields = cls.getDeclaredFields();
		if (lastCellNum != fields.length - 1){
			result = ERROR_COUNT_NOT_MATCH;
		}
		return result;
	}
	
	@Override
	public String importProjectData(XSSFWorkbook workbook) {
		return importMktData(workbook, new OnUpdateMktObjectListener(){

			@Override
			public Class<?> onGetClass() {
				return MktProjectInfo.class;
			}

			@Override
			public void update(Object mktObject) {
				projectInfoDao.update((MktProjectInfo)mktObject);
			}
		});
	}

	
	
	
	@Override
	public String importSignData(XSSFWorkbook workbook) {
		return importMktData(workbook, new OnUpdateMktObjectListener(){

			@Override
			public Class<?> onGetClass() {
				return MktSignContract.class;
			}

			@Override
			public void update(Object mktObject) {
				signContractDao.update((MktSignContract)mktObject);
			}
			
		});
	}

	
	private Object createMktObject(XSSFRow row, Object obj){
		
		Class<?> cls = obj.getClass();
		
		Field[] fields = cls.getDeclaredFields();

		for (int i = 0; i < row.getLastCellNum(); ++i) {
			Field field = fields[i + 1];
			XSSFCell cell = row.getCell(i);
			String val = "";
			if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
				val = cell.getNumericCellValue() + "";
			} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
				val = cell.getStringCellValue() + "";
			} 
			if (null != val && !val.isEmpty()) {
				String name = field.getName();
				try {
					Method method = cls.getMethod(Util.getSetMethodName(name),
							field.getType());

					if (field.getType().getName()
							.equals(Integer.class.getName())) {
						method.invoke(obj, Double.valueOf(val).intValue());
					} else if (field.getType().getName()
							.equals(Double.class.getName())) {
						method.invoke(obj, Double.valueOf(val));
					} else if (field.getType().getName()
							.equals(String.class.getName())) {
						method.invoke(obj, val);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return obj;
	}

	private String importMktData(XSSFWorkbook workbook,
			OnUpdateMktObjectListener listener) {
		String result = validate(workbook, listener.onGetClass());
		if (ERROR_OK.equals(result)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				XSSFRow row = sheet.getRow(i);
				try {
					listener.update(createMktObject(row, listener
							.onGetClass().newInstance()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@Override
	public String importBidData(XSSFWorkbook workbook) {
		return importMktData(workbook, new OnUpdateMktObjectListener(){

			@Override
			public Class<?> onGetClass() {
				return MktBidInfo.class;
			}

			@Override
			public void update(Object mktObject) {
				bidInfoDao.update((MktBidInfo)mktObject);
			}
			
		});
	}

}
