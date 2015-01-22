package com.tbea.datatransfer.model.dao.zjsb.yszkpzgh;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.YSZKPZGHSB;
import com.tbea.datatransfer.model.entity.zjtb.YSZKPZGHTB;

public interface YSZKPZGHSBDao extends AbstractReadOnlyDao<YSZKPZGHSB> {

	public List<YSZKPZGHSB> getAllYSZKPZGHSB();

}
