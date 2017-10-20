package com.tbea.erp.report.model.dao.navigateitem;

import com.speed.frame.model.dao.AbstractReadWriteDao;
import com.tbea.erp.report.model.entity.NavigateItemEntity;

import java.util.List;


public interface NavigateItemDao extends AbstractReadWriteDao<NavigateItemEntity> {
    List<NavigateItemEntity> getItems(List<String> auths);
}
