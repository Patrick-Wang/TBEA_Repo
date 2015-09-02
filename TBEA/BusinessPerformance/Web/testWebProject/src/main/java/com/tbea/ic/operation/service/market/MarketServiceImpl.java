package com.tbea.ic.operation.service.market;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
	private final String ERROR_COUNT_NOT_MATCH = "文档不匹配(列数不匹配)";
	
	
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
		if (lastCellNum != fields.length - 3){
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
				MktProjectInfo mpi = (MktProjectInfo)mktObject;
				MktProjectInfo mpiOld = projectInfoDao.getById(mpi.getProjectNo());
				if (mpiOld.getStartdate() == null){
					mpi.setStartdate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				} else{
					mpi.setStartdate(mpiOld.getStartdate());
				}
				
				if (mpiOld.getEnddate() == null){
					mpi.setEnddate(mpi.getStartdate());
				} else{
					mpi.setEnddate(mpiOld.getEnddate());
				}
				projectInfoDao.update(mpi);
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
				MktSignContract msc = (MktSignContract)mktObject;
				MktSignContract mscOld = signContractDao.getById(msc.getContractNo());
				if (mscOld.getStartdate() == null){
					msc.setStartdate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				} else{
					msc.setStartdate(mscOld.getStartdate());
				}
				
				if (mscOld.getEnddate() == null){
					msc.setEnddate(msc.getStartdate());
				} else{
					msc.setEnddate(mscOld.getEnddate());
				}
				signContractDao.update(msc);
			}
			
		});
	}
	
	String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");
            s = s.replaceAll("[.]$", "");
        }  
        return s;  
    }  
	
	private Object createMktObject(XSSFRow row, Object obj){
		
		Class<?> cls = obj.getClass();
		
		Field[] fields = cls.getDeclaredFields();

		for (int i = 0; i < row.getLastCellNum(); ++i) {
			Field field = fields[i + 1];
			XSSFCell cell = row.getCell(i);
			String val = "";
			if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
				short df = cell.getCellStyle().getDataFormat();
				if (176 == df){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月");  
					java.util.Date date = cell.getDateCellValue();  
					val = sdf.format(date);  
				} else if (58 == df){
					SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
					java.util.Date date = cell.getDateCellValue();  
					val = sdf.format(date);  
				} else{
					val = subZeroAndDot(cell.getNumericCellValue() + "");
				}
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
				MktBidInfo mbi = (MktBidInfo)mktObject;
				MktBidInfo mbiOld = bidInfoDao.getById(mbi.getBidNo());
				if (mbiOld.getStartdate() == null){
					mbi.setStartdate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				} else{
					mbi.setStartdate(mbiOld.getStartdate());
				}
				
				if (mbiOld.getEnddate() == null){
					mbi.setEnddate(mbi.getStartdate());
				} else{
					mbi.setEnddate(mbiOld.getEnddate());
				}
				
				bidInfoDao.update(mbi);
			}
			
		});
	}

	@Override
	public String[][] getBidData(String companyName, Integer year) {
		List<MktBidInfo> Bids = bidInfoDao.getData(companyName, year);
		String[][] result = new String[Bids.size()][24];
		Integer col=0;
		for (MktBidInfo bid : Bids) {
			if (null != bid) {
					result[col][0] = bid.getCompanyName() + "";
					result[col][1] = bid.getBidNo() + "";
					result[col][2] = bid.getProjectNo() + "";
					result[col][3] = bid.getAuthorizationNo()+ "";
					result[col][4] = bid.getOfficeName() + "";
					result[col][5] = bid.getBidMonth() + "";
					result[col][6] = bid.getBidDate() + "";
					result[col][7] = bid.getIndustryCategory() + "";
					result[col][8] = bid.getSystemClassification() + "";
					result[col][9] = bid.getProjectArea() + "";
					result[col][10] = bid.getProjectName() + "";
					result[col][11] = bid.getOwnerName() + "";
					result[col][12] = bid.getProductModel() + "";
					result[col][13] = bid.getProductAmount() + "";
					result[col][14] = bid.getProductLevel() + "";
					result[col][15] = bid.getProductVolume() + "";
					result[col][16] = bid.getBidPrice() + "";
					result[col][17] = bid.getSuccessfulBidderName() + "";
					result[col][18] = bid.getSucessfulBidderPrice() + "";
					result[col][19] = bid.getAnalysisOfCause() + "";
					result[col][20] = bid.getSuccessfulBidderMonth() + "";
					result[col][21] = bid.getBidStatus() + "";
					result[col][22] = bid.getWhetherFeedbackBidSummary() + "";
					result[col][23] = bid.getSpecificBidCompanyName() + "";
					col++;
			}
		}
		return result;
	}
	
	@Override
	public String[][] getPrjData(String companyName, Integer year) {
		List<MktProjectInfo> list = projectInfoDao.getData(companyName, year);
		String[][] result = new String[list.size()][19];
		Integer col=0;
		for (MktProjectInfo obj : list) {
			if (null != obj) {
					result[col][0] = obj.getCompanyName() + "";
					result[col][1] = obj.getOfficeName() + "";
					result[col][2] = obj.getProjectNo() + "";
					result[col][3] = obj.getIndustryCategory()+ "";
					result[col][4] = obj.getSystemClassification() + "";
					result[col][5] = obj.getProjectName() + "";
					result[col][6] = obj.getOwnerName() + "";
					result[col][7] = obj.getProductModel() + "";
					result[col][8] = obj.getProductAmount() + "";
					result[col][9] = obj.getExceptedBidCost() + "";
					result[col][10] = obj.getExceptedBidTime() + "";
					result[col][11] = obj.getProjectArea() + "";
					result[col][12] = obj.getProjectSummary()+ "";
					result[col][13] = obj.getProjectAdvancement() + "";
					result[col][14] = obj.getChiefInfo() + "";
					result[col][15] = obj.getLeaderInfo() + "";
					result[col][16] = obj.getOtherCompanyName() + "";
					result[col][17] = obj.getBidSituation() + "";
					result[col][18] = obj.getRemark() + "";
					
					col++;
			}
		}
		return result;
	}
	
	@Override
	public String[][] getContData(String companyName) {
		List<MktSignContract> list = signContractDao.getData(companyName);
		String[][] result = new String[list.size()][19];
		Integer col=0;
		for (MktSignContract obj : list) {
			if (null != obj) {
					result[col][0] = obj.getCompanyName() + "";
					result[col][1] = obj.getContractNo() + "";
					result[col][2] = obj.getOfficeName() + "";
					result[col][3] = obj.getSignMonth()+ "";
					result[col][4] = obj.getIndustryCategory() + "";
					result[col][5] = obj.getSystemClassfication() + "";
					result[col][6] = obj.getProjectArea() + "";
					result[col][7] = obj.getProjectName() + "";
					result[col][8] = obj.getOwnerName() + "";
					result[col][9] = obj.getProductModel() + "";
					result[col][10] = obj.getProductLevel() + "";
					result[col][11] = obj.getProductAmount() + "";
					result[col][12] = obj.getProductVolume() + "";
					result[col][13] = obj.getProductPrice() + "";
					result[col][14] = obj.getPaymentMethod() + "";
					result[col][15] = obj.getSignPerson() + "";
					result[col][16] = obj.getSpecificSignCompany() + "";
					result[col][17] = obj.getWhetherInstantPayment()+ "";
					result[col][18] = obj.getWhetherManufacturingIndustry() + "";
					col++;
			}
		}
		return result;
	}

	@Override
	public void carryDownBidInfo(Date dStart, Date dEnd, Date target) {
		List<MktBidInfo> undecidedInfos = bidInfoDao.getUndecidedBidInfo(dStart, dEnd);
		for(MktBidInfo bidInfo : undecidedInfos){
			bidInfo.setEnddate(target);
			bidInfoDao.update(bidInfo);
		}
		
	}

	@Override
	public void carryDownProjectInfo(Date dStart, Date dEnd, Date target) {
		List<MktProjectInfo> carryDownInfos = projectInfoDao.getCarryDownProjectInfo(dStart, dEnd);
		for(MktProjectInfo projectInfo : carryDownInfos){
			projectInfo.setEnddate(target);
			projectInfoDao.update(projectInfo);
		}
	}
}
