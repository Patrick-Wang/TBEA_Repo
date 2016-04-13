package com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm;
import com.tbea.ic.operation.model.entity.identifier.chgb.JykcxmEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDao;
import java.util.List;


public interface JykcxmDao extends AbstractReadWriteDao<JykcxmEntity> {

	int getXMAmount();
	
	List<JykcxmEntity> getXMMapping();
}
