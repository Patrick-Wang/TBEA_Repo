package com.tbea.ic.operation.service.nczb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
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
		gsztZb.add(GSZB.LRZE1.getValue());
		gsztZb.add(GSZB.XSSR6.getValue());
		gsztZb.add(GSZB.JYXJXJL29.getValue());
		gsztZb.add(GSZB.YSZK32.getValue());
		gsztZb.add(GSZB.CH35.getValue());
		gsztZb.add(GSZB.ZCZE179.getValue());
		gsztZb.add(GSZB.GDZC180.getValue());
		gsztZb.add(GSZB.JZC_QMS_181.getValue());
		gsztZb.add(GSZB.JZC_QCS_182.getValue());
		gsztZb.add(GSZB.JLR183.getValue());
		gsztZb.add(GSZB.FZZEQMS184.getValue());
		gsztZb.add(GSZB.FZL185.getValue());
		gsztZb.add(GSZB.SXFY64.getValue());
		gsztZb.add(GSZB.SXFYL_65.getValue());
		gsztZb.add(GSZB.JZCSYL_66.getValue());
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
	public List<String[]> getGSZB(Date d, List<Company> companies) {
		IPipe pipe = new BasicPipe(gsztZb, companies, d, confFactory.getFinancialPipeConfigurator());
		List<Double[]> data = pipe.getData();
		List<String[]> result = makeZbResult(gsztZb, data);
		result.remove(gsztZb.indexOf(GSZB.FZZEQMS184.getValue()));
		return result;
	}

}
