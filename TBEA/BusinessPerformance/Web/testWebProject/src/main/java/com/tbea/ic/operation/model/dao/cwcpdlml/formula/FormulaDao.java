package com.tbea.ic.operation.model.dao.cwcpdlml.formula;
import com.tbea.ic.operation.model.entity.cwcpdlml.FormulaEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface FormulaDao extends AbstractReadWriteDao<FormulaEntity> {

	FormulaEntity getByCpfl(Integer cpid);

}
