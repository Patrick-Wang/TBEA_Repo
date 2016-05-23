package com.tbea.ic.operation.service.jygk.zzy;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglx;

public interface ReferBglxService {

	public List<JygkZzyDwReferBglx> getDataList(List<Company> comps);
}
