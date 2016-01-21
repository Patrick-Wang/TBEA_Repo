package com.tbea.ic.weixin.service.weixin;

import java.util.List;

import com.tbea.ic.WeixinSdkException;

public interface WeiXinService {

	void transferOrg();

	void transferPersion();

	void sendNews(List<Integer> allIds, String[] usrs) throws WeixinSdkException;

	String getMsg(Integer valueOf);


}
