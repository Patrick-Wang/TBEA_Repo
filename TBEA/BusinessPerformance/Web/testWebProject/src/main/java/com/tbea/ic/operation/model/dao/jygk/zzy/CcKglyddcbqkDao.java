package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyBglx;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcKglyddcbqk;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;

public interface CcKglyddcbqkDao  extends AbstractReadWriteDao<JygkZzyCcKglyddcbqk>{
	Object[] getZb(Integer zb, Date date, int company, int bglx);
	Object getZbObject(Integer zb, Date date, int company, String classname, String bglx);

	JygkZzyFl getZbfl(int flid);
	List<JygkZzyFl> getZbfl(Company company, int bglx);
	List<JygkZzyDwReferBglxfl> getDwReferBglxfl(int company, int bglx);
	Object[] getBgCompanie(String id);
}
