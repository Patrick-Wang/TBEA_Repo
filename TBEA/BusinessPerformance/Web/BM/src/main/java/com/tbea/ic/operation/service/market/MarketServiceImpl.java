package com.tbea.ic.operation.service.market;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.market.bidInfo.MktBidInfoDao;
import com.tbea.ic.operation.model.dao.market.projectInfo.MktProjectInfoDao;
import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.model.entity.MktProjectInfo;
import com.tbea.ic.operation.model.entity.MktSignContract;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;
import com.tbea.ic.operation.service.market.pipe.MarketUnit.Type;
import com.tbea.ic.operation.service.market.pipe.configurator.ConfiguratorFactory;
import com.tbea.ic.operation.service.util.pipe.core.CompositePipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;

@Service
@Transactional("transactionManager")
public class MarketServiceImpl implements MarketService {

	interface OnUpdateMktObjectListener {
		Class<?> onGetClass();

		void update(Object createMktObject);
	}
	
	@Autowired
	private MktBidInfoDao bidInfoDao;

	@Autowired
	private MktProjectInfoDao projectInfoDao;

	@Autowired
	private MktSignContractDao signContractDao;

	private ConfiguratorFactory configFactory;
	
	private final String ERROR_OK = "OK";
	private final String ERROR_COUNT_NOT_MATCH = "文档不匹配(列数不匹配)";
	private final String ERROR_UNKNOWN = "未知错误";

	private OnUpdateMktObjectListener bidUpdateListener;

	private OnUpdateMktObjectListener signUpdateListener;

	private OnUpdateMktObjectListener projectUpdateListener;

	
	@Autowired
	public void init() {
		configFactory = new ConfiguratorFactory(bidInfoDao, signContractDao);
		bidUpdateListener = ObjectUpdateListenerFactory
				.createBidUpdateListener(bidInfoDao);

		signUpdateListener = ObjectUpdateListenerFactory
				.createSignUpdateListener(signContractDao);

		projectUpdateListener = ObjectUpdateListenerFactory
				.createProjectUpdateListener(projectInfoDao);
	}
	
	private final static List<Integer> bidIndicators = new ArrayList<Integer>();
	static{
		bidIndicators.add(Indicator.TBSL.ordinal());
		bidIndicators.add(Indicator.TBJE.ordinal());
		bidIndicators.add(Indicator.ZBJE.ordinal());
		bidIndicators.add(Indicator.ZBL.ordinal());
		bidIndicators.add(Indicator.TBZB.ordinal());
	}
	
	private final static List<Integer> signIndicators = new ArrayList<Integer>();
	static{
		signIndicators.add(Indicator.HTSL.ordinal());
		signIndicators.add(Indicator.QYJE.ordinal());
		signIndicators.add(Indicator.QYZB.ordinal());
	}
	
	private final static List<Integer> mixedIndicators = new ArrayList<Integer>();
	static{
		mixedIndicators.add(Indicator.TBJE.ordinal());
		mixedIndicators.add(Indicator.ZBJE.ordinal());
		mixedIndicators.add(Indicator.ZBL.ordinal());
		mixedIndicators.add(Indicator.QYJE.ordinal());
	}
		 
	private String validate(XSSFWorkbook workbook, Class<?> cls) {
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		String result = ERROR_OK;
		int lastCellNum = row.getLastCellNum();
		Field[] fields = cls.getDeclaredFields();
		if (lastCellNum != fields.length - 3) {
			result = ERROR_COUNT_NOT_MATCH;
		}
		return result;
	}


	String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");
			s = s.replaceAll("[.]$", "");
		}
		return s;
	}

	private Object createMktObject(XSSFRow row, Object obj) {

		Class<?> cls = obj.getClass();

		Field[] fields = cls.getDeclaredFields();

		for (int i = 0; i < row.getLastCellNum(); ++i) {
			Field field = fields[i + 1];
			XSSFCell cell = row.getCell(i);
			String val = "";
			if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				short df = cell.getCellStyle().getDataFormat();
				if (14 == df) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
					java.util.Date date = cell.getDateCellValue();
					val = sdf.format(date);
				} else {
					val = subZeroAndDot(cell.getNumericCellValue() + "");
				}

			} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				val = cell.getStringCellValue() + "";
			}
			if (null != val && !val.isEmpty()) {
				String name = field.getName();
				try {
					Method method = cls.getMethod(Util.getSetMethodName(name),
							field.getType());

					/*
					 * if (field.getType().getName()
					 * .equals(Integer.class.getName())) { method.invoke(obj,
					 * Double.valueOf(val).intValue()); } else
					 */if (field.getType().getName()
							.equals(Double.class.getName())) {
						method.invoke(obj, Double.valueOf(val));
					} else if (field.getType().getName()
							.equals(String.class.getName())) {
						method.invoke(obj, val);
					} else if (field.getType().getName()
							.equals(Date.class.getName())) {
						method.invoke(obj, Util.toDate(val));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return obj;
	}

	private Object createMktObject(JSONArray row, Object obj) {

		Class<?> cls = obj.getClass();

		Field[] fields = cls.getDeclaredFields();

		for (int i = 0; i < row.size(); ++i) {
			Field field = fields[i + 1];
			String val = row.getString(i);
			if (null != val && !val.isEmpty()) {
				String name = field.getName();
				try {
					Method method = cls.getMethod(Util.getSetMethodName(name),
							field.getType());
					if (field.getType().getName()
							.equals(Double.class.getName())) {
						method.invoke(obj, Double.valueOf(val));
					} else if (field.getType().getName()
							.equals(String.class.getName())) {
						method.invoke(obj, val);
					}else if (field.getType().getName()
							.equals(Date.class.getName())){
						method.invoke(obj, Util.toDate(val));
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
					listener.update(createMktObject(row, listener.onGetClass()
							.newInstance()));
				} catch (Exception e) {
					e.printStackTrace();
					result = ERROR_UNKNOWN;
				}
			}
		}
		return result;
	}

	private void importMktData(JSONArray row,
			OnUpdateMktObjectListener listener){
		try {
			listener.update(createMktObject(row, listener.onGetClass().newInstance()));
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[][] getBidData(String companyName, Integer year) {
		List<MktBidInfo> Bids = bidInfoDao.getData(companyName, year);
		
		String[][] result = new String[Bids.size()][24];
		Integer col = 0;
		
		for (MktBidInfo bid : Bids) {
			if (null != bid) {
				result[col][0] = bid.getCompanyName() + "";
				result[col][1] = bid.getBidNo() + "";
				result[col][2] = bid.getProjectNo() + "";
				result[col][3] = bid.getAuthorizationNo() + "";
				result[col][4] = bid.getOfficeName() + "";
				if(null != bid.getBidDate()){
					result[col][5] = Util.formatToDay(bid.getBidDate());
				}else{
					result[col][5] = "";
				}
				
				result[col][6] = bid.getIndustryCategory() + "";
				result[col][7] = bid.getSystemClassification() + "";
				result[col][8] = bid.getProjectArea() + "";
				result[col][9] = bid.getProjectName() + "";
				result[col][10] = bid.getOwnerName() + "";
				result[col][11] = bid.getProductModel() + "";
				result[col][12] = bid.getProductAmount() + "";
				result[col][13] = bid.getProductLevel() + "";
				result[col][14] = bid.getProductVolume() + "";
				result[col][15] = bid.getBidPrice() + "";
				result[col][16] = bid.getSuccessfulBidderName() + "";
				result[col][17] = bid.getSucessfulBidderPrice() + "";
				result[col][18] = bid.getAnalysisOfCause() + "";
				result[col][19] = bid.getSuccessfulBidderMonth() + "";
				result[col][20] = bid.getBidStatus() + "";
				result[col][21] = bid.getWhetherFeedbackBidSummary() + "";
				result[col][22] = bid.getSpecificBidCompanyName() + "";
				col++;
			}
		}
		
		return result;
	}

	@Override
	public String[][] getPrjData(String companyName, Integer year) {
//		Util.Elapse escape = new Util.Elapse();
//		escape.start();
		List<MktProjectInfo> list = projectInfoDao.getData(companyName, year);
//		escape.end("bidInfoDao.getData");
		String[][] result = new String[list.size()][20];
		Integer col = 0;
//		escape.start();
		for (MktProjectInfo obj : list) {
			if (null != obj) {
				result[col][0] = obj.getCompanyName() + "";
				result[col][1] = obj.getOfficeName() + "";
				result[col][2] = obj.getProjectNo() + "";
				result[col][3] = obj.getIndustryCategory() + "";
				result[col][4] = obj.getSystemClassification() + "";
				result[col][5] = obj.getProjectName() + "";
				result[col][6] = obj.getOwnerName() + "";
				result[col][7] = obj.getProductModel() + "";
				result[col][8] = obj.getProductAmount() + "";
				result[col][9] = obj.getExceptedBidCost() + "";
				result[col][10] = obj.getExceptedBidTime() + "";
				result[col][11] = obj.getProjectArea() + "";
				result[col][12] = obj.getProjectSummary() + "";
				result[col][13] = obj.getProjectAdvancement() + "";
				result[col][14] = obj.getChiefInfo() + "";
				result[col][15] = obj.getLeaderInfo() + "";
				result[col][16] = obj.getOtherCompanyName() + "";
				
				result[col][17] = obj.getBidSituation() + "";
				result[col][18] = obj.getBidRestrict() + "";
				result[col][19] = obj.getRemark() + "";

				col++;
			}
		}
//		escape.end("mapList");
		return result;
	}

	@Override
	public String[][] getContData(String companyName) {
		List<MktSignContract> list = signContractDao.getData(companyName);
		String[][] result = new String[list.size()][19];
		Integer col = 0;
		for (MktSignContract obj : list) {
			if (null != obj) {
				result[col][0] = obj.getCompanyName() + "";
				result[col][1] = obj.getContractNo() + "";
				result[col][2] = obj.getOfficeName() + "";
				if(null != obj.getSignDate()){
					result[col][3] = Util.formatToDay(obj.getSignDate());
				}else{
					result[col][3] = "";
				}

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
				result[col][17] = obj.getWhetherInstantPayment() + "";
				result[col][18] = obj.getWhetherManufacturingIndustry() + "";
				col++;
			}
		}
		return result;
	}

	@Override
	public void carryDownBidInfo(Date dStart, Date dEnd, Date target) {
		List<MktBidInfo> undecidedInfos = bidInfoDao.getUndecidedBidInfo(
				dStart, dEnd);
		for (MktBidInfo bidInfo : undecidedInfos) {
			bidInfo.setEnddate(target);
			bidInfoDao.update(bidInfo);
		}

	}

	@Override
	public void carryDownProjectInfo(Date dStart, Date dEnd, Date target) {
		List<MktProjectInfo> carryDownInfos = projectInfoDao
				.getCarryDownProjectInfo(dStart, dEnd);
		for (MktProjectInfo projectInfo : carryDownInfos) {
			projectInfo.setEnddate(target);
			projectInfoDao.update(projectInfo);
		}
	}

	@Override
	public String importProjectData(XSSFWorkbook workbook) {
		return importMktData(workbook, projectUpdateListener);
	}

	@Override
	public String importSignData(XSSFWorkbook workbook) {
		return importMktData(workbook, signUpdateListener);
	}
	
	@Override
	public String importBidData(XSSFWorkbook workbook) {
		return importMktData(workbook, bidUpdateListener);
	}


	@Override
	public ErrorCode editProjectData(JSONArray jsonArray, String rawKey) {
		if (jsonArray.getString(2).isEmpty()){
			return ErrorCode.PREMARY_KEY_NULL;
		}
		
		if (!jsonArray.getString(2).equals(rawKey)){
			if (null != projectInfoDao.getById(jsonArray.getString(2))){
				return ErrorCode.PREMARY_KEY_CONFILICT;
			}
			importMktData(jsonArray, ObjectUpdateListenerFactory.createProjectEditListener(projectInfoDao, this.projectInfoDao.getById(rawKey)));
			this.projectInfoDao.remove(rawKey);
		} else{
			importMktData(jsonArray, ObjectUpdateListenerFactory.createProjectEditListener(projectInfoDao, this.projectInfoDao.getById(rawKey)));
		}
		return ErrorCode.OK;
	}


	@Override
	public ErrorCode editSignData(JSONArray jsonArray, String rawKey) {
		if (jsonArray.getString(1).isEmpty()){
			return ErrorCode.PREMARY_KEY_NULL;
		}
		
		if (!jsonArray.getString(1).equals(rawKey)){
			if (null != this.signContractDao.getById(jsonArray.getString(1))){
				return ErrorCode.PREMARY_KEY_CONFILICT;
			}
			importMktData(jsonArray, ObjectUpdateListenerFactory.createSignEditListener(signContractDao, this.signContractDao.getById(rawKey)));
			this.signContractDao.remove(rawKey);
		} else{
			importMktData(jsonArray, ObjectUpdateListenerFactory.createSignEditListener(signContractDao, this.signContractDao.getById(rawKey)));
		}
		return ErrorCode.OK;
	}


	@Override
	public ErrorCode editBidData(JSONArray jsonArray, String rawKey) {
		if (jsonArray.getString(1).isEmpty()){
			return ErrorCode.PREMARY_KEY_NULL;
		}
		
		if (!jsonArray.getString(1).equals(rawKey)){
			if (null != this.bidInfoDao.getById(jsonArray.getString(1))){
				return ErrorCode.PREMARY_KEY_CONFILICT;
			}
			importMktData(jsonArray, ObjectUpdateListenerFactory.createBidEditListener(bidInfoDao, this.bidInfoDao.getById(rawKey)));
			this.bidInfoDao.remove(rawKey);
		} else{
			importMktData(jsonArray, ObjectUpdateListenerFactory.createBidEditListener(bidInfoDao, this.bidInfoDao.getById(rawKey)));
		}
		return ErrorCode.OK;
	}


	@Override
	public ErrorCode addProjectData(JSONArray jsonArray) {
		if (jsonArray.getString(2).isEmpty()){
			return ErrorCode.PREMARY_KEY_NULL;
		}
		
		if (null == this.projectInfoDao.getById(jsonArray.getString(2))){
			importMktData(jsonArray, ObjectUpdateListenerFactory.createProjectAddListener(projectInfoDao));
		}
		else{
			return ErrorCode.PREMARY_KEY_CONFILICT;
		}
		return ErrorCode.OK;
	}


	@Override
	public ErrorCode addSignData(JSONArray jsonArray) {
		if (jsonArray.getString(1).isEmpty()){
			return ErrorCode.PREMARY_KEY_NULL;
		}
		
		if (null == this.signContractDao.getById(jsonArray.getString(1))){
			importMktData(jsonArray, ObjectUpdateListenerFactory.createSignAddListener(signContractDao));
		}
		else{
			return ErrorCode.PREMARY_KEY_CONFILICT;
		}
		return ErrorCode.OK;
	}


	@Override
	public ErrorCode addBidData(JSONArray jsonArray) {
		if (jsonArray.getString(1).isEmpty()){
			return ErrorCode.PREMARY_KEY_NULL;
		}
		
		if (null == this.bidInfoDao.getById(jsonArray.getString(1))){
			importMktData(jsonArray, ObjectUpdateListenerFactory.createBidAddListener(bidInfoDao));
		}
		else{
			return ErrorCode.PREMARY_KEY_CONFILICT;
		}
		return ErrorCode.OK;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<List<String>> getIndustryBidData(JSONArray companyNames, Date date) {
		List<MarketUnit> muSb = toMarketUnit(companyNames, Type.INDUSTRY);//new MarketUnit(companyName, Type.INDUSTRY);
		IPipeConfigurator options = configFactory.getIndustryBidAnalysisConfigurator(muSb);
		List<MarketUnit> mus = this.bidInfoDao.getIndustries(muSb);
		Map<Company, List<Company>> totalMap = new HashMap<Company, List<Company>>();
		MarketUnit muTotal = new MarketUnit("合计", Type.INDUSTRY);
		totalMap.put(muTotal, (List)mus);
		CompositePipe pipe = new CompositePipe(
				bidIndicators, date,
				this.configFactory.getIndustryBidAnalysisCompositeConfigurator(totalMap));
		for(MarketUnit mu : mus){
			pipe.addCompany(mu, options);
		}
		pipe.addCompany(muTotal, null);
		List<Double[]> ret = pipe.getData();
		List<List<String>> result = new ArrayList<List<String>>();
		int len = mus.size() + 1;
		for (int i = 0; i < len - 1; ++i){
			result.add(transformIndustryBid(ret, i,len, mus.get(i)));
		}
		result.add(transformIndustryBid(ret, len - 1, len, muTotal));
		
		return result;
	}
	
	private List<String> transformIndustryBid(List<Double[]> data, int i, int step, MarketUnit mu) {
		List<String> row = new ArrayList<String>();
		row.add(mu.getName());
		row.add(Util.toString(data.get(i)[0]));
		row.add(Util.toString(data.get(i + step * 1)[0]));
		row.add(Util.toString(data.get(i + step * 2)[0]));
		row.add(Util.toString(data.get(i + step * 4)[0]));

		row.add(Util.toString(data.get(i)[1]));
		row.add(Util.toString(data.get(i + step * 1)[1]));
		row.add(Util.toString(data.get(i + step * 2)[1]));
		row.add(Util.toString(data.get(i + step * 3)[1]));
		row.add(Util.toString(data.get(i + step * 4)[1]));
		
		row.add(Util.toString(data.get(i)[2]));
		row.add(Util.toString(data.get(i + step * 1)[2]));
		row.add(Util.toString(data.get(i + step * 2)[2]));
		row.add(Util.toString(data.get(i + step * 3)[2]));
		row.add(Util.toString(data.get(i + step * 4)[2]));
		row.add(Util.toString(data.get(i + step * 1)[3]));
		return row;
	}

	@Override
	public List<List<String>> getCompanyBidData(JSONArray companyNames, Date date) {
		List<MarketUnit> muSb = toMarketUnit(companyNames, Type.COMPANY);//new MarketUnit(companyName, Type.COMPANY);
		IPipeConfigurator options = configFactory.getCompanyBidAnalysisConfigurator(muSb);
		Map<Company, List<Company>> totalMap = new HashMap<Company, List<Company>>();
		List<MarketUnit> companies = bidInfoDao.getCompanies(muSb);
		MarketUnit muTotal = new MarketUnit("合计", Type.COMPANY);
		totalMap.put(muTotal, (List)companies);
		CompositePipe pipe = new CompositePipe(
				bidIndicators, date,
				this.configFactory.getCompanyBidAnalysisCompositeConfigurator(totalMap));
		for(MarketUnit mu : companies){
			pipe.addCompany(mu, options);
		}
		pipe.addCompany(muTotal, null);
		List<Double[]> ret = pipe.getData();
		List<List<String>> result = new ArrayList<List<String>>();
		int len = companies.size() + 1;
		for (int i = 0; i < len - 1; ++i){
			result.add(transformCompanyBid(ret, i, len, companies.get(i)));
		}
		result.add(transformCompanyBid(ret, len - 1, len, muTotal));
		return result;
	}


	private List<String> transformCompanyBid(List<Double[]> data, int i,
			int step, MarketUnit mu) {
		List<String> row = new ArrayList<String>();
		row.add(mu.getName());
		row.add(Util.toString(data.get(i)[0]));
		row.add(Util.toString(data.get(i + step * 1)[0]));
		row.add(Util.toString(data.get(i + step * 2)[0]));
		row.add(Util.toString(data.get(i + step * 4)[0]));

		row.add(Util.toString(data.get(i)[1]));
		row.add(Util.toString(data.get(i + step * 1)[1]));
		row.add(Util.toString(data.get(i + step * 2)[1]));
		row.add(Util.toString(data.get(i + step * 3)[1]));
		row.add(Util.toString(data.get(i + step * 4)[1]));
		return row;
	}
	
	@Override
	public List<List<String>> getCompanySignData(JSONArray companyNames, Date date) {
		List<MarketUnit> muSb = toMarketUnit(companyNames, Type.COMPANY);//new MarketUnit(companyName, Type.COMPANY);
		IPipeConfigurator options = configFactory.getCompanySignAnalysisConfigurator(muSb);
		Map<Company, List<Company>> totalMap = new HashMap<Company, List<Company>>();
		List<MarketUnit> companies = signContractDao.getCompanies(muSb);
		MarketUnit muTotal = new MarketUnit("合计", Type.COMPANY);
		totalMap.put(muTotal, (List)companies);
		CompositePipe pipe = new CompositePipe(
				signIndicators, date,
				this.configFactory.getCompanySignAnalysisCompositeConfigurator(totalMap));
		for(MarketUnit mu : companies){
			pipe.addCompany(mu, options);
		}
		pipe.addCompany(muTotal, null);
		List<Double[]> ret = pipe.getData();
		List<List<String>> result = new ArrayList<List<String>>();
		int len = companies.size() + 1;
		for (int i = 0; i < len - 1; ++i){
			result.add(transform2Sign(ret, i, len, companies.get(i)));
		}
		result.add(transform2Sign(ret, len - 1, len, muTotal));
		return result;
	}

	
	@Override
	public List<List<String>> getIndustrySignData(JSONArray companyNames, Date date) {
		List<MarketUnit> muSb = toMarketUnit(companyNames, Type.INDUSTRY);//new MarketUnit(companyName, Type.INDUSTRY);
		IPipeConfigurator options = configFactory.getIndustrySignAnalysisConfigurator(muSb);
		Map<Company, List<Company>> totalMap = new HashMap<Company, List<Company>>();
		List<MarketUnit> companies = signContractDao.getIndustries(muSb);
		MarketUnit muTotal = new MarketUnit("合计", Type.INDUSTRY);
		totalMap.put(muTotal, (List)companies);
		CompositePipe pipe = new CompositePipe(
				signIndicators, date,
				this.configFactory.getIndustrySignAnalysisCompositeConfigurator(totalMap));
		for(MarketUnit mu : companies){
			pipe.addCompany(mu, options);
		}
		pipe.addCompany(muTotal, null);
		List<Double[]> ret = pipe.getData();
		List<List<String>> result = new ArrayList<List<String>>();
		int len = companies.size() + 1;
		for (int i = 0; i < len - 1; ++i){
			result.add(transform2Sign(ret, i, len, companies.get(i)));
		}
		result.add(transform2Sign(ret, len - 1, len, muTotal));
		return result;
	}

	private List<String> transform2Sign(List<Double[]> data, int i, int step,
			MarketUnit mu) {
		List<String> row = new ArrayList<String>();
		row.add(mu.getName());
		row.add(Util.toString(data.get(i)[0]));
		row.add(Util.toString(data.get(i + step * 1)[0]));
		row.add(Util.toString(data.get(i + step * 2)[0]));

		row.add(Util.toString(data.get(i)[1]));
		row.add(Util.toString(data.get(i + step * 1)[1]));
		row.add(Util.toString(data.get(i + step * 2)[1]));

		row.add(Util.toString(data.get(i)[2]));
		row.add(Util.toString(data.get(i + step * 1)[2]));
		row.add(Util.toString(data.get(i + step * 2)[2]));
		row.add(Util.toString(data.get(i + step * 1)[3]));
		return row;
	}

	
	private List<MarketUnit> merge(List<MarketUnit> companiesBid, List<MarketUnit> companiesSign){
		List<MarketUnit> result = new ArrayList<MarketUnit>();
		result.addAll(companiesBid);
		for (MarketUnit  mubs: companiesSign){
			boolean contains = false;
			for (MarketUnit mub : companiesBid){
				if (mubs.getUniqueId().equals(mub.getUniqueId())){
					contains = true;
					break;
				}
			}
			if (!contains){
				result.add(mubs);
			}
		}
		return result;
	}
	
	@Override
	public List<List<String>> getAreaMixedAnalysisData(JSONArray companyNames, Date dateStart, Date dateEnd) {
		List<MarketUnit> muSb = toMarketUnit(companyNames, Type.AREA);// new MarketUnit(companyName, Type.AREA);
		IPipeConfigurator options = configFactory.getAreaAnalysisConfigurator(muSb, dateStart);
		Map<Company, List<Company>> totalMap = new HashMap<Company, List<Company>>();
		List<MarketUnit> companiesSign = signContractDao.getAreas(muSb);
		List<MarketUnit> companiesBid = bidInfoDao.getAreas(muSb);
		List<MarketUnit> companies = merge(companiesBid, companiesSign);
		MarketUnit muTotal = new MarketUnit("合计", Type.AREA);
		totalMap.put(muTotal, (List)companies);
		CompositePipe pipe = new CompositePipe(
				mixedIndicators, dateEnd,
				this.configFactory.getAreaAnalysisCompositeConfigurator(totalMap));
		for(MarketUnit mu : companies){
			pipe.addCompany(mu, options);
		}
		pipe.addCompany(muTotal, null);
		List<Double[]> ret = pipe.getData();
		List<List<String>> result = new ArrayList<List<String>>();
		int len = companies.size() + 1;
		for (int i = 0; i < len - 1; ++i){
			result.add(transform2Area(ret, i, len, companies.get(i)));
		}
		result.add(transform2Area(ret, len - 1, len, muTotal));
		return result;
	}


	private List<String> transform2Area(List<Double[]> data, int i, int step,
			MarketUnit mu) {
		List<String> row = new ArrayList<String>();
		row.add(mu.getName());
		row.add(Util.toString(data.get(i)[0]));
		row.add(Util.toString(data.get(i + step * 1)[0]));
		row.add(Util.toString(data.get(i + step * 2)[0]));
		row.add(Util.toString(data.get(i + step * 3)[0]));
		return row;
	}


	private List<MarketUnit> toMarketUnit(JSONArray arr, Type type){
		List<MarketUnit> ret = new ArrayList<MarketUnit>();
		for (int i = 0; i < arr.size(); ++i){
			ret.add(new MarketUnit(arr.getString(i), type));
		}
		return ret;
	}
	
	@Override
	public List<List<String>> getIndustryMixedAnalysisData(JSONArray companyNames,
			Date startDate, Date endDate) {
		List<MarketUnit> muSb = toMarketUnit(companyNames, Type.INDUSTRY);
		IPipeConfigurator options = configFactory.getIndustryMixedAnalysisConfigurator(muSb, startDate);
		Map<Company, List<Company>> computeMap = new HashMap<Company, List<Company>>();
//		List<MarketUnit> companiesSign = signContractDao.getIndustries(muSb);
//		List<MarketUnit> companiesBid = bidInfoDao.getIndustries(muSb);
		List<MarketUnit> companies = new ArrayList<MarketUnit>();//merge(companiesBid, companiesSign);
		CompositePipe pipe = new CompositePipe(
				mixedIndicators, endDate,
				this.configFactory.getIndustryMixedAnalysisCompositeConfigurator(computeMap));
		
		List<MarketUnit> dwMUs = new ArrayList<MarketUnit>();
		dwMUs.add(new MarketUnit("国网", Type.INDUSTRY));
		dwMUs.add(new MarketUnit("南网", Type.INDUSTRY));
		
		List<MarketUnit> dyMUs = new ArrayList<MarketUnit>();
		dyMUs.add(new MarketUnit("火电", Type.INDUSTRY));
		dyMUs.add(new MarketUnit("水电", Type.INDUSTRY));
		dyMUs.add(new MarketUnit("核电", Type.INDUSTRY));
		dyMUs.add(new MarketUnit("风电", Type.INDUSTRY));
		dyMUs.add(new MarketUnit("光伏", Type.INDUSTRY));
		
		List<MarketUnit> fdlMUs = new ArrayList<MarketUnit>();
		fdlMUs.add(new MarketUnit("轨道交通", Type.INDUSTRY));
		fdlMUs.add(new MarketUnit("石油石化", Type.INDUSTRY));
		fdlMUs.add(new MarketUnit("煤炭煤化工", Type.INDUSTRY));
		fdlMUs.add(new MarketUnit("钢铁冶金", Type.INDUSTRY));
		fdlMUs.add(new MarketUnit("航天军工", Type.INDUSTRY));
		
		List<MarketUnit> otherMUs = new ArrayList<MarketUnit>();
		otherMUs.add(new MarketUnit("其它", Type.INDUSTRY));
		
		MarketUnit dwMU = new MarketUnit("电网小计", Type.INDUSTRY);
		MarketUnit dyMU = new MarketUnit("电源小计", Type.INDUSTRY);
		MarketUnit fdlMU = new MarketUnit("五大非电力小计", Type.INDUSTRY);
		MarketUnit hjMU = new MarketUnit("合计", Type.INDUSTRY);

		companies.addAll(dwMUs);
		pipe.addCompany((List)dwMUs, options);
		companies.add(dwMU);
		pipe.addCompany(dwMU, null);
		
		companies.addAll(dyMUs);
		pipe.addCompany((List)dyMUs, options);
		
		companies.add(dyMU);
		pipe.addCompany(dyMU, null);
		
		
		companies.addAll(fdlMUs);
		pipe.addCompany((List)fdlMUs, options);
		
		companies.add(fdlMU);
		pipe.addCompany(fdlMU, null);
		
		companies.addAll(otherMUs);
		pipe.addCompany((List)otherMUs, options);
		
		companies.add(hjMU);
		pipe.addCompany(hjMU, null);
		
		List<MarketUnit> hjMUs = new ArrayList<MarketUnit>();
		hjMUs.addAll(dwMUs);
		hjMUs.addAll(dyMUs);
		hjMUs.addAll(fdlMUs);
		hjMUs.addAll(otherMUs);
		
		computeMap.put(dwMU, (List)dwMUs);
		computeMap.put(dyMU, (List)dyMUs);
		computeMap.put(fdlMU, (List)fdlMUs);
		computeMap.put(hjMU, (List)hjMUs);	
		
//		for(MarketUnit mu : companies){
//			pipe.addCompany(mu, options);
//		}
		
		
		List<Double[]> ret = pipe.getData();
		List<List<String>> result = new ArrayList<List<String>>();
		int len = companies.size();
		for (int i = 0; i < len; ++i){
			result.add(transform2MixedIndustry(ret, i, len, companies.get(i)));
		}
//		result.add(transform2MixedIndustry(ret, len - 1, len, muTotal));
		return result;
	}


	private List<String> transform2MixedIndustry(List<Double[]> data, int i,
			int step, MarketUnit mu) {
		List<String> row = new ArrayList<String>();
		row.add(mu.getName());
		row.add(Util.toString(data.get(i)[0]));
		row.add(Util.toString(data.get(i + step * 1)[0]));
		row.add(Util.toString(data.get(i + step * 2)[0]));
		row.add(Util.toString(data.get(i + step * 3)[0]));
		
		row.add(Util.toString(data.get(i)[1])); 
		row.add(Util.toString(data.get(i + step * 1)[1]));
		row.add(Util.toString(data.get(i + step * 2)[1]));
		row.add(Util.toString(data.get(i + step * 3)[1]));
		
		row.add(Util.toString(data.get(i)[2]));
		row.add(Util.toString(data.get(i + step * 1)[2]));
		row.add(Util.toString(data.get(i + step * 2)[2]));
		row.add(Util.toString(data.get(i + step * 3)[2]));
		return row;
	}

}
