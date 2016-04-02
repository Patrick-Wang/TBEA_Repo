package com.tbea.ic.operation.service.pricelib.jcycljg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.dmdjyx.DmdjyxDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.dmdjyx.DmdjyxDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva.EVADao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva.EVADaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.fgc.FgcDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.fgc.FgcDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp.GgpDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp.GgpDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy.GjyyDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy.GjyyDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gx.GxDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gx.GxDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj.JkzjDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj.JkzjDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt.JtDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt.JtDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg.LwgDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg.LwgDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lzbb.LzbbDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lzbb.LzbbDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.myzs.MyzsDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.myzs.MyzsDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pmicpippi.PmiCpiPpiDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pmicpippi.PmiCpiPpiDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pvcsz.PVCSzDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pvcsz.PVCSzDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks.TksDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks.TksDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.yhjzll.YhjzllDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.yhjzll.YhjzllDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb.ZhbDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb.ZhbDaoImpl;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.DataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.DmdjyxDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.EVADataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.FgcDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.GgpDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.GjyyDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.GxDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.JkzjDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.JtDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.LwgDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.LzbbDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.MyzsDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.PMICPIPPIDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.PVCSzDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.StorageAssemble;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.TksDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.YhjzllDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.YsjsDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.ZhbDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.ValidationException;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.excel.CommonValidator;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.excel.FormatValidator;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.excel.ImportHandler;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.excel.ImportHandlerFactory;

@Service(JcycljgServiceImpl.NAME)
@Transactional("transactionManager")
public class JcycljgServiceImpl implements JcycljgService {
	@Resource(name = YhjzllDaoImpl.NAME)
	YhjzllDao yhjzllDao;

	@Resource(name = PmiCpiPpiDaoImpl.NAME)
	PmiCpiPpiDao pmiCpiPpiDao;

	@Resource(name = LwgDaoImpl.NAME)
	LwgDao lwgDao;

	@Resource(name = MyzsDaoImpl.NAME)
	MyzsDao myzsDao;

	@Resource(name = JkzjDaoImpl.NAME)
	JkzjDao jkzjDao;

	@Resource(name = EVADaoImpl.NAME)
	EVADao evaDao;

	@Resource(name = DmdjyxDaoImpl.NAME)
	DmdjyxDao dmdjyxDao;

	@Resource(name = PVCSzDaoImpl.NAME)
	PVCSzDao pVCSzDao;

	@Resource(name = GxDaoImpl.NAME)
	GxDao gxDao;

	@Resource(name = ZhbDaoImpl.NAME)
	ZhbDao zhbDao;

	@Resource(name = LzbbDaoImpl.NAME)
	LzbbDao lzbbDao;

	@Resource(name = FgcDaoImpl.NAME)
	FgcDao fgcDao;

	@Resource(name = JtDaoImpl.NAME)
	JtDao jtDao;

	@Resource(name = TksDaoImpl.NAME)
	TksDao tksDao;

	@Resource(name = GjyyDaoImpl.NAME)
	GjyyDao gjyyDao;

	@Resource(name = GgpDaoImpl.NAME)
	GgpDao ggpDao;

	@Resource(name = YsjsDaoImpl.NAME)
	YsjsDao ysjsDao;
	
	public final static String NAME = "YsjsServiceImpl";

	
	@Autowired StorageAssemble sa;
	UtilQuery[] queries;

	List<ImportHandler> importHandlers = new ArrayList<ImportHandler>();
	List<OutputHandler> outputHandlers = new ArrayList<OutputHandler>();
			
	@Autowired
	public void init() {
		queries = new UtilQuery[] { 
				new UtilQuery(ysjsDao),
				new UtilQuery(ggpDao), 
				new UtilQuery(gjyyDao),
				new UtilQuery(tksDao), 
				new UtilQuery(jtDao),
				new UtilQuery(fgcDao), 
				new UtilQuery(lzbbDao),
				new UtilQuery(zhbDao), 
				new UtilQuery(gxDao),
				new UtilQuery(pVCSzDao),
				new UtilQuery(dmdjyxDao), 
				new UtilQuery(evaDao),
				new UtilQuery(jkzjDao), 
				new UtilQuery(myzsDao),
				new UtilQuery(lwgDao),
				new UtilQuery(pmiCpiPpiDao),
				new UtilQuery(yhjzllDao) };
		
//		storages = new DataStorage[] { 
//				new YsjsDataStorage(ysjsDao),
//				new GgpDataStorage(ggpDao), 
//				new GjyyDataStorage(gjyyDao),
//				new TksDataStorage(tksDao), 
//				new JtDataStorage(jtDao),
//				new FgcDataStorage(fgcDao), 
//				new LzbbDataStorage(lzbbDao),
//				new ZhbDataStorage(zhbDao), 
//				new GxDataStorage(gxDao),
//				new PVCSzDataStorage(pVCSzDao),
//				new DmdjyxDataStorage(dmdjyxDao), 
//				new EVADataStorage(evaDao),
//				new JkzjDataStorage(jkzjDao), 
//				new MyzsDataStorage(myzsDao),
//				new LwgDataStorage(lwgDao),
//				new PMICPIPPIDataStorage(pmiCpiPpiDao),
//				new YhjzllDataStorage(yhjzllDao) };


		
		for (int i = 0; i <= JcycljgType.YHJZLL.ordinal(); ++i){
			JcycljgType type = JcycljgType.valueOf(i);
			importHandlers.add(ImportHandlerFactory.create(type, sa));
			outputHandlers.add(new OutputHandler(type, queries[i], sa));
		}
		
	}

	@Override
	public void importExcel(JcycljgType type, XSSFWorkbook workbook)
			throws ValidationException {
		importHandlers.get(type.ordinal()).handle(workbook);
	}

	@Override
	public List<List<String>> getValues(JcycljgType type, Date start, Date end) {
		return outputHandlers.get(type.ordinal()).handle(start, end);
	}

}
