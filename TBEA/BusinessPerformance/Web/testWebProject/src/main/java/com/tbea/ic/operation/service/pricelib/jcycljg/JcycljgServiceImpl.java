package com.tbea.ic.operation.service.pricelib.jcycljg;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.yhjzll.YhjzllDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.yhjzll.YhjzllDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pmicpippi.PmiCpiPpiDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pmicpippi.PmiCpiPpiDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg.LwgDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg.LwgDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.myzs.MyzsDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.myzs.MyzsDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj.JkzjDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj.JkzjDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva.EVADaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva.EVADao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.dmdjyx.DmdjyxDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.dmdjyx.DmdjyxDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pvcsz.PVCSzDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pvcsz.PVCSzDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gx.GxDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gx.GxDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb.ZhbDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb.ZhbDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lzbb.LzbbDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lzbb.LzbbDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.fgc.FgcDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.fgc.FgcDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt.JtDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt.JtDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks.TksDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks.TksDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy.GjyyDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy.GjyyDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp.GgpDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp.GgpDao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgService;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.YsjsDataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.validation.CommonValidator;
import com.tbea.ic.operation.service.pricelib.jcycljg.validation.ValidationException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(JcycljgServiceImpl.NAME)
@Transactional("transactionManager")
public class JcycljgServiceImpl implements JcycljgService {
	@Resource(name=YhjzllDaoImpl.NAME)
	YhjzllDao yhjzllDao;

	@Resource(name=PmiCpiPpiDaoImpl.NAME)
	PmiCpiPpiDao pmiCpiPpiDao;

	@Resource(name=LwgDaoImpl.NAME)
	LwgDao lwgDao;

	@Resource(name=MyzsDaoImpl.NAME)
	MyzsDao myzsDao;

	@Resource(name=JkzjDaoImpl.NAME)
	JkzjDao jkzjDao;

	@Resource(name=EVADaoImpl.NAME)
	EVADao eVADao;

	@Resource(name=DmdjyxDaoImpl.NAME)
	DmdjyxDao dmdjyxDao;

	@Resource(name=PVCSzDaoImpl.NAME)
	PVCSzDao pVCSzDao;

	@Resource(name=GxDaoImpl.NAME)
	GxDao gxDao;

	@Resource(name=ZhbDaoImpl.NAME)
	ZhbDao zhbDao;

	@Resource(name=LzbbDaoImpl.NAME)
	LzbbDao lzgbDao;

	@Resource(name=FgcDaoImpl.NAME)
	FgcDao fgcDao;

	@Resource(name=JtDaoImpl.NAME)
	JtDao jtDao;

	@Resource(name=TksDaoImpl.NAME)
	TksDao tksDao;

	@Resource(name=GjyyDaoImpl.NAME)
	GjyyDao gjyyDao;

	@Resource(name=GgpDaoImpl.NAME)
	GgpDao ggpDao;

	@Resource(name=YsjsDaoImpl.NAME)
	YsjsDao ysjsDao;

	public final static String NAME = "YsjsServiceImpl";

	ImportHandler[] handlers = null;
	@Autowired
	public void init(){
		handlers = new ImportHandler[]{
			new ImportHandler(new CommonValidator(7), new YsjsDataStorage(ysjsDao))
		};
	}

	
	@Override
	public void importExcel(JcycljgType type, XSSFWorkbook workbook) throws ValidationException {
		handlers[type.ordinal()].handle(workbook);
	}
	
	@Override
	public List<List<String>> getYsjs(Date start, Date end) {
		List<YsjsEntity> ysjsEntitys = ysjsDao.getYsjs(start, end);
		List<List<String>> result = new ArrayList<List<String>>();
		for (YsjsEntity ysjs : ysjsEntitys){
			List<String> entity = new ArrayList<String>();
			entity.add(Util.formatToDay(ysjs.getDate()));
			entity.add(ysjs.getCjxhCu() + "");
			entity.add(ysjs.getCjxhAl() + "");
			entity.add(ysjs.getCjxhZn() + "");
			entity.add(ysjs.getLEMCu() + "");
			entity.add(ysjs.getLEMAl() + "");
			entity.add( ysjs.getLEMZn() + "");
		    result.add(entity);
		}
		return result;
	}


	@Override
	public List<List<String>> getGgp(Date start, Date end) {
		
		List<GgpEntity> entitys = ggpDao.getGgp(start, end);
		List<List<String>> result = new ArrayList<List<String>>();
		for (GgpEntity entity : entitys){
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToMonth(entity.getDate()));
			list.add("" + entity.getWg30q120());
			list.add("" + entity.getWg30pk100());
			list.add("" + entity.getWg27pk095());
			list.add("" + entity.getWg23pk085());
			list.add("" + entity.getBgb30p120());
			list.add("" + entity.getBgb30p110());
			list.add("" + entity.getBgb27r095());
			list.add("" + entity.getBgb27r085());
		    result.add(list);
		}
		return result;
	}

}
