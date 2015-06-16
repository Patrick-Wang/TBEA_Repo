package com.tbea.ic.operation.service.nczb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.dao.nc.NCZBDao;
import com.tbea.ic.operation.service.nczb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.nczb.pipe.configurator.ConfiguratorFactory;
import com.tbea.ic.operation.service.util.pipe.core.BasicPipe;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
@Service
@Transactional("transactionManager")
public class NCZBServiceImpl implements NCZBService{

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	NCZBDao nczbDao;

	@Autowired
	DWXXDao dwxxDao;

	@Autowired
	ZBXXDao zbxxDao;
	
	static List<Integer> gsztZb = new ArrayList<Integer>();
	
	static{
		gsztZb.add(GSZB.ZCZE.getValue());
		gsztZb.add(GSZB.GDZC.getValue());
		gsztZb.add(GSZB.JZCQMS.getValue());
		gsztZb.add(GSZB.JZCQCS.getValue());
		gsztZb.add(GSZB.JLR.getValue());
		gsztZb.add(GSZB.FZZEQMS.getValue());
	}
	
	ConfiguratorFactory confFactory;
	
	@Autowired
	void init(){
		AccumulatorFactory accFac = new AccumulatorFactory(nczbDao);
		confFactory = new ConfiguratorFactory(accFac);
	}
	
	private List<String[]> makeZbResult(List<Integer> zbs, List<Double[]> gszbs) {

		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			Double[] gszbRow = gszbs.get(i);
			String[] zbRow = new String[gszbRow.length + 1];
			zbRow[0] = zbxxDao.getById(zbs.get(i)).getName();
			for (int j = zbRow.length - 1; j > 0; --j) {
				if (null != gszbRow[j - 1]) {
					zbRow[j] = gszbRow[j - 1] + "";
				}
			}
			result.add(zbRow);
		}
		return result;
	}

	
	@Override
	public List<String[]> getGSZB(Date d) {
		IPipe pipe = new BasicPipe(gsztZb, BMDepartmentDB.getJydw(companyManager), d, confFactory.getFinancialPipeConfigurator());
		List<Double[]> data = pipe.getData();
		return makeZbResult(gsztZb, data);
	}

	@Override
	public ConfiguratorFactory getConfigFactory() {
		return confFactory;
	}
}
