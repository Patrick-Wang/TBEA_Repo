package com.tbea.erp.report.model.dao.navigateitem;

import com.speed.frame.model.dao.AbstractReadWriteDao;
import com.tbea.erp.report.model.entity.Account;
import com.tbea.erp.report.model.entity.NavigateItemEntity;

import java.util.List;


public interface NavigateItemDao extends AbstractReadWriteDao<NavigateItemEntity> {
    NavigateItemEntity getItem(Integer item);

    List<NavigateItemEntity> getItems(Account account);
}
