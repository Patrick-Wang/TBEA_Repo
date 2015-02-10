package com.tbea.ic.operation.service.approve;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zbdao.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zbdao.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;

@Service
@Transactional("transactionManager")
public class ApproveServiceImpl implements ApproveService {

	@Autowired
	QXGLDao qxglDao;
	
	@Autowired
	SJZBDao sjzbDao;
	
	@Autowired
	YJ20ZBDao yj20zbDao;
	
	@Autowired
	YJ28ZBDao yj28zbDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	@Autowired
	ZBXXDao zbxxDao;
	
	@Autowired
	SHZTDao shztDao;
	
	@Autowired
	YDJHZBDao ydjhzbDao;
	
	@Autowired
	NDJHZBDao ndjhzbDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	

	@Override
	public List<List<String[]>> getZb(List<Company> comps, Date date, ZBType entryType) {
		List<List<String[]>> ret = null;
		switch(entryType){
		case BY20YJ:
			ret = get20Zb(comps, date);
			break;
		case BY28YJ:
			ret = get28Zb(comps, date);
			break;
		case BYSJ:
			ret = getBysjZb(comps, date);
			break;
		case NDJH:
			ret = getNdjhZb(comps, date);
			break;
		case YDJDMJH:
			ret = getYdjdZb(comps, date);
			break;
		default:
			break;
		}
		return ret;
	}

	
    private List<List<String[]>> getYdjdZb(List<Company> comps, Date date) {
		// TODO Auto-generated method stub
		return null;
	}


	//[[compId ,zbId, zbName, value] ...] approved 
    //[[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> getNdjhZb(List<Company> comps, Date date) {
		Organization org = companyManager.getBMOrganization();
		List<List<String[]>> retList = new ArrayList<List<String[]>>();
		List<String[]> approveList = new ArrayList<String[]>();
		List<String[]> unapproveList = new ArrayList<String[]>();
		List<NDJHZB> ndjhzbs = ndjhzbDao.getZbs(date, comps);
		for (NDJHZB ndjhzb : ndjhzbs){
			String[] zbTmp = new String[4];
			if (ndjhzb.getNdjhshzt().getId() == 1){//approved
				unapproveList.add(zbTmp);
			} else{
				approveList.add(zbTmp);
			}
			Company comp = org.getCompany(ndjhzb.getDwxx().getId());
			zbTmp[0] = comp.getType().ordinal() + "";
			zbTmp[1] = ndjhzb.getZbxx().getId() + "";
			zbTmp[2] = ndjhzb.getZbxx().getName();
			zbTmp[3] = ndjhzb.getNdjhz() + "";
		}
		retList.add(approveList);
		retList.add(unapproveList);
		return retList;
	}

	//[[compId ,zbId, zbName, value] ...] approved 
    //[[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> getBysjZb(List<Company> comps, Date date) {
		Organization org = companyManager.getBMOrganization();
		List<List<String[]>> retList = new ArrayList<List<String[]>>();
		List<String[]> approveList = new ArrayList<String[]>();
		List<String[]> unapproveList = new ArrayList<String[]>();
		List<SJZB> sjzbs = sjzbDao.getZbs(date, comps);
		for (SJZB sjzb : sjzbs){
			String[] zbTmp = new String[4];
			if (sjzb.getSjshzt().getId() == 1){//approved
				unapproveList.add(zbTmp);
			} else{
				approveList.add(zbTmp);
			}
			Company comp = org.getCompany(sjzb.getDwxx().getId());
			zbTmp[0] = comp.getType().ordinal() + "";
			zbTmp[1] = sjzb.getZbxx().getId() + "";
			zbTmp[2] = sjzb.getZbxx().getName();
			zbTmp[3] = sjzb.getSjz() + "";
		}
		retList.add(approveList);
		retList.add(unapproveList);
		return retList;
	}

    //[[compId ,zbId, zbName, value, year, month] ...] approved 
    //[[compId ,zbId, zbName, value, year, month] ...] unapproved
	private List<List<String[]>> get28Zb(List<Company> comps, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

    //[[compId ,zbId, zbName, value, year, month] ...] approved 
    //[[compId ,zbId, zbName, value, year, month] ...] unapproved
	private List<List<String[]>> get20Zb(List<Company> comps, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasApprovePlanPermission(Account account) {
		return qxglDao.getJhzshCount(account) > 0;
	}

	@Override
	public boolean hasApprovePredictPermission(Account account) {
		return qxglDao.getSjzshCount(account) > 0;
	}


	@Override
	public boolean approveNdjhZb(List<Company> comps, Date date) {
		List<NDJHZB> ndjhzbs = ndjhzbDao.getUnapprovedZbs(date, comps);
		for (NDJHZB ndjhzb : ndjhzbs){
			ndjhzb.setNdjhshzt(shztDao.getById(1));
			ndjhzbDao.merge(ndjhzb);
		}
		return true;
	}


	
	@Override
	public boolean unapproveNdjhZb(List<Company> comps, Date date) {
		List<NDJHZB> ndjhzbs = ndjhzbDao.getApprovedZbs(date, comps);
		for (NDJHZB ndjhzb : ndjhzbs){
			ndjhzb.setNdjhshzt(shztDao.getById(2));
			ndjhzbDao.merge(ndjhzb);
		}
		return true;
	}


	@Override
	public boolean approveYj20Zb(List<Company> comps, List<Date> dateList) {
		List<YJ20ZB> zbs = yj20zbDao.getUnapprovedZbs(dateList, comps);
		for (YJ20ZB yj20zb : zbs){
			yj20zb.setYj20shzt(shztDao.getById(1));
			yj20zbDao.merge(yj20zb);
		}
		return true;
	}


	@Override
	public boolean approveYj28Zb(List<Company> comps, List<Date> dateList) {
		List<YJ28ZB> zbs = yj28zbDao.getUnapprovedZbs(dateList, comps);
		for (YJ28ZB yj28zb : zbs){
			yj28zb.setYj28shzt(shztDao.getById(1));
			yj28zbDao.merge(yj28zb);
		}
		return true;
	}


	@Override
	public boolean approveSjZb(List<Company> comps, Date date) {
		List<SJZB> zbs = sjzbDao.getUnapprovedZbs(date, comps);
		for (SJZB zb : zbs){
			zb.setSjshzt(shztDao.getById(1));
			sjzbDao.merge(zb);
		}
		return true;
	}


	@Override
	public boolean approveYdjdZb(List<Company> comps, List<Date> dateList) {
//		List<SJZB> zbs = sjzbDao.getApprovedZbs(date, comps);
//		for (SJZB zb : zbs){
//			zb.setSjshzt(shztDao.getById(2));
//			sjzbDao.merge(zb);
//		}
		return true;
	}


	@Override
	public boolean unapproveSjZb(List<Company> comps, Date date) {
		List<SJZB> zbs = sjzbDao.getApprovedZbs(date, comps);
		for (SJZB zb : zbs){
			zb.setSjshzt(shztDao.getById(2));
			sjzbDao.merge(zb);
		}
		return true;
	}


	@Override
	public boolean unapproveYj28Zb(List<Company> comps, List<Date> dateList) {
		List<YJ28ZB> zbs = yj28zbDao.getApprovedZbs(dateList, comps);
		for (YJ28ZB yj28zb : zbs){
			yj28zb.setYj28shzt(shztDao.getById(2));
			yj28zbDao.merge(yj28zb);
		}
		return true;
	}


	@Override
	public boolean unapproveYj20Zb(List<Company> comps, List<Date> dateList) {
		List<YJ20ZB> zbs = yj20zbDao.getApprovedZbs(dateList, comps);
		for (YJ20ZB zb : zbs){
			zb.setYj20shzt(shztDao.getById(2));
			yj20zbDao.merge(zb);
		}
		return true;
	}


}
