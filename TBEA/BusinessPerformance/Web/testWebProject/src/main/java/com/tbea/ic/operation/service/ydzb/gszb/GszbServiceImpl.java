package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;

public class GszbServiceImpl implements GszbService{

	@Autowired
	NDJHZBDao ndjhzbDao;
	
	@Autowired
	ZBXXDao zbxxDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	private static List<Integer> gsztzbs = new ArrayList<Integer>();
	static {
		gsztzbs.add(GSZB.LRZE.getValue());
		gsztzbs.add(GSZB.XSSR.getValue());
		gsztzbs.add(GSZB.JYXJXJL.getValue());
		gsztzbs.add(GSZB.YSZK.getValue());
		gsztzbs.add(GSZB.QZYQK.getValue());
		gsztzbs.add(GSZB.BL.getValue());
		gsztzbs.add(GSZB.CH.getValue());
		gsztzbs.add(GSZB.QZJYWY.getValue());
		gsztzbs.add(GSZB.HTQYE.getValue());
		gsztzbs.add(GSZB.ZJHL.getValue());
		gsztzbs.add(GSZB.BHSCZ.getValue());
		gsztzbs.add(GSZB.RS.getValue());
		gsztzbs.add(GSZB.RJLR.getValue());
		gsztzbs.add(GSZB.RJSR.getValue());
		gsztzbs.add(GSZB.SXFY.getValue());
		gsztzbs.add(GSZB.SXFYL.getValue());
		gsztzbs.add(GSZB.JZCSYL.getValue());
	}
	
	
	private static Map<Integer, ZBXX> zbxxMap = new HashMap<Integer, ZBXX>();
	
	
	private ZBXX getZbxx(Integer zbId){
		if (zbxxMap.isEmpty()){
			List<ZBXX> zbxxs = zbxxDao.getZbs(gsztzbs);
			for (ZBXX zbxx : zbxxs){
				zbxxMap.put(zbxx.getId(), zbxx);
			}
		}
		return zbxxMap.get(zbId);
	}
	
	private List<Company> filterCompany(List<Company> companies){
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies){
			if (!comp.getSubCompanys().isEmpty()){
				retComps.addAll(comp.getSubCompanys());
			}
		}
		return retComps;
	}
	
	@Override
	public List<String[]> getGsztzb(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		GszbPipe pipe = new GszbPipe(gsztzbs, 15, filterCompany(org.getCompany(CompanyType.GFGS).getSubCompanys()), date);
		pipe.add(new QnjhPipeFilter(ndjhzbDao, 0));
		List<Double[]> gszbs = pipe.getGszb();
		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0, len = gsztzbs.size(); i < len; ++i){
			String[] zbRow = new String[16];
			Double[] gszbRow = gszbs.get(i);
			zbRow[0] = getZbxx(gsztzbs.get(i)).getName();
			for (int j = zbRow.length - 1; j > 0; --j){
				if (null != gszbRow[j - 1]){
					zbRow[i] = gszbRow[j - 1] + "";
				}
			}
		}
		return result;
	}
	
}
