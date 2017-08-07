package com.tbea.ic.operation.model.dao.navigator;

import java.util.List;

import com.tbea.ic.operation.model.entity.navigator.NavigatorItem;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

public interface NavigatorItemDao extends AbstractReadWriteDao<NavigatorItem> {

	List<Integer> getExcludedItem(List<Integer> auths, List<String> openAuths);

	List<Integer> getIncludedItems(List<Integer> auths, List<String> openAuths, List<Integer> excludedItems);

	List<NavigatorItem> getNaviItems(List<Integer> includeItems);



}
