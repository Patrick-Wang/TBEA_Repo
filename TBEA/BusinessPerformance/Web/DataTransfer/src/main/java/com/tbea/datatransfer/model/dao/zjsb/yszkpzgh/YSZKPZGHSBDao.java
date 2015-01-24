package com.tbea.datatransfer.model.dao.zjsb.yszkpzgh;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.YSZKPZGHBYQ;

public interface YSZKPZGHSBDao extends AbstractReadOnlyDao<YSZKPZGHBYQ> {

	public List<YSZKPZGHBYQ> getAllYSZKPZGH();

}
