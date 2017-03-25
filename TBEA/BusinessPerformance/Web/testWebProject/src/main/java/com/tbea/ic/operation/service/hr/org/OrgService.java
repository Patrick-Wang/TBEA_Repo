package com.tbea.ic.operation.service.hr.org;

import java.util.List;

import com.tbea.ic.operation.model.entity.hr.Org;

public interface OrgService {

	public boolean saveOrg(String batch, List<Org> org);

}
