package com.tbea.ic.operation.service.navigator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.navigator.NavigatorItemDao;
import com.tbea.ic.operation.model.entity.navigator.NavigatorItem;

@Service
@Transactional("transactionManager")
public class NavigatorServiceImpl implements NavigatorService {

	@Autowired
	NavigatorItemDao navigatorItemDao;

	@Override
	public List<NavigatorItem> getNaviItems(List<Integer> auths, List<String> openAuths) {
		auths.add(0);
		List<Integer> excludedItems = navigatorItemDao.getExcludedItem(auths, openAuths);
		List<Integer> includeItems = navigatorItemDao.getIncludedItems(auths, openAuths, excludedItems);
		List<NavigatorItem> resultItems = navigatorItemDao.getNaviItems(includeItems);
		return resultItems;
	}


}
