package com.tbea.ic.operation.service.navigator;

import java.util.List;

import com.tbea.ic.operation.model.entity.navigator.NavigatorItem;

public interface NavigatorService {

	List<NavigatorItem> getNaviItems(List<Integer> auths, List<String> openAuths);


}
