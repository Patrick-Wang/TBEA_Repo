package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;

@Component
public class StorageAssemble {

	@Autowired YsjsDataStorage ysjs;
	@Autowired  GgpDataStorage ggp;
	@Autowired  GjyyDataStorage gjyy;
	@Autowired  TksDataStorage tks;
	@Autowired  JtDataStorage jt;
	@Autowired  FgcDataStorage fgc;
	@Autowired  LzbbDataStorage lzbb;
	@Autowired  ZhbDataStorage zhb;
	@Autowired  GxDataStorage gx;
	@Autowired  PVCSzDataStorage pvc;
	@Autowired  DmdjyxDataStorage dmd;
	@Autowired  EVADataStorage eva;
	@Autowired  JkzjDataStorage jkzj;
	@Autowired  MyzsDataStorage myzs;
	@Autowired  LwgDataStorage lwg;
	@Autowired  PMICPIPPIDataStorage pmi;
	@Autowired  YhjzllDataStorage yhjzll;
	
	private static DataStorage<?>[] storages = new DataStorage<?>[JcycljgType.YHJZLL.ordinal() + 1];
	private static DataStringify<?>[] stringifies = new DataStringify<?>[JcycljgType.YHJZLL.ordinal() + 1];
	static void register(JcycljgType type, DataStorage storage, DataStringify stringify){
		storages[type.ordinal()] = storage;
		stringifies[type.ordinal()] = stringify;
	}
	
	public DataStorage getStorage(JcycljgType type){
		return storages[type.ordinal()];
	}
	
	public DataStringify getStringify(JcycljgType type){
		return stringifies[type.ordinal()];
	}
}
