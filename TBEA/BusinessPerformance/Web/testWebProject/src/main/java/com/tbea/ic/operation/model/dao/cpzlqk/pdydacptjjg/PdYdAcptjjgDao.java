package com.tbea.ic.operation.model.dao.cpzlqk.pdydacptjjg;
import java.util.List;

import com.tbea.ic.operation.model.entity.cpzlqk.PdAcptjEntryEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.PdYdAcptjjgEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface PdYdAcptjjgDao extends AbstractReadWriteDao<PdYdAcptjjgEntity> {

	List<PdYdAcptjjgEntity> getAll();

	List<PdAcptjEntryEntity> getEntryEntities(Integer dwid);

}
