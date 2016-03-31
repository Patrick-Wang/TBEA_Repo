package com.tbea.ic.operation.service.pricelib.jcycljg;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
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
import com.tbea.ic.operation.service.pricelib.jcycljg.excelimport.ImportHandler;
import com.tbea.ic.operation.service.pricelib.jcycljg.excelimport.validation.CommonValidator;
import com.tbea.ic.operation.service.pricelib.jcycljg.excelimport.validation.ValidationException;
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
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.TksDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.YhjzllDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.YsjsDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.ZhbDataStorage;

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

	ImportHandler[] handlers = null;
	@SuppressWarnings("rawtypes")
	DataStorage[] storages = null;

	class Query {
		GetEntitiesDao geDao;
		public Query(GetEntitiesDao geDao){
			this.geDao = geDao;
		}
		public List<?> query(Date start, Date end) {
			return geDao.getEntities(start, end);
		}
	};

	Query[] queries = new Query[] { new Query(ysjsDao),
			new Query(ggpDao), 
			new Query(gjyyDao),
			new Query(tksDao), 
			new Query(jtDao),
			new Query(fgcDao), 
			new Query(lzbbDao),
			new Query(zhbDao), 
			new Query(gxDao),
			new Query(pVCSzDao),
			new Query(dmdjyxDao), 
			new Query(evaDao),
			new Query(jkzjDao), 
			new Query(myzsDao),
			new Query(lwgDao),
			new Query(pmiCpiPpiDao),
			new Query(yhjzllDao) };

	@Autowired
	public void init() {
		storages = new DataStorage[] { 
				new YsjsDataStorage(ysjsDao),
				new GgpDataStorage(ggpDao), 
				new GjyyDataStorage(gjyyDao),
				new TksDataStorage(tksDao), 
				new JtDataStorage(jtDao),
				new FgcDataStorage(fgcDao), 
				new LzbbDataStorage(lzbbDao),
				new ZhbDataStorage(zhbDao), 
				new GxDataStorage(gxDao),
				new PVCSzDataStorage(pVCSzDao),
				new DmdjyxDataStorage(dmdjyxDao), 
				new EVADataStorage(evaDao),
				new JkzjDataStorage(jkzjDao), 
				new MyzsDataStorage(myzsDao),
				new LwgDataStorage(lwgDao),
				new PMICPIPPIDataStorage(pmiCpiPpiDao),
				new YhjzllDataStorage(yhjzllDao) };

		handlers = new ImportHandler[] {
				new ImportHandler(new CommonValidator(2, 7), storages[0]),
				new ImportHandler(new CommonValidator(2, 9), storages[1]),
				new ImportHandler(new CommonValidator(1, 3), storages[2]),
				new ImportHandler(new CommonValidator(2, 7), storages[3]),
				new ImportHandler(new CommonValidator(1, 5), storages[4]),
				new ImportHandler(new CommonValidator(1, 5), storages[5]),
				new ImportHandler(new CommonValidator(1, 7), storages[6]),
				new ImportHandler(new CommonValidator(1, 9), storages[7]),
				new ImportHandler(new CommonValidator(1, 7), storages[8]),
				new ImportHandler(new CommonValidator(2, 9), storages[9]),
				new ImportHandler(new CommonValidator(1, 3), storages[10]), 
				new ImportHandler(new CommonValidator(1, 4), storages[11]),
				new ImportHandler(new CommonValidator(1, 4), storages[12]),
				new ImportHandler(new CommonValidator(1, 2), storages[13]),
				new ImportHandler(new CommonValidator(2, 9), storages[14]),
				new ImportHandler(new CommonValidator(1, 4), storages[15]),
				new ImportHandler(new CommonValidator(2, 7), storages[16]) };
	}

	@Override
	public void importExcel(JcycljgType type, XSSFWorkbook workbook)
			throws ValidationException {
		handlers[type.ordinal()].handle(workbook);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<String>> getValues(JcycljgType type, Date start, Date end) {
		return storages[type.ordinal()].stringify(queries[type.ordinal()]
				.query(start, end));
	}

}
