package com.frame.script.el.em;

import java.util.HashMap;
import java.util.Map;

public class EMRegistry {
	static Map<String, ExtendMethod> emMap = new HashMap<String, ExtendMethod>();
	static{
		register(new EMAsJson());
		register(new EMArrayJudge());
		register(new EMListJudge());
		register(new EMDistinct());
		register(new EMTranspose());
		register(new EMListPack());
		register(new EMAsTimestamp());
		register(new EMJsonString2Json());
		register(new EM2Int());
		register(new EM2Fixed());
		register(new EMListResize());
		register(new EMListColumn());
		register(new EMListLastColumn());	
		register(new EMListLast());	
		register(new EMListRmCol());
		register(new EMListRemoveAt());
		register(new EMListLastRemoveAt());
		register(new EMListLastRmCol());
		register(new EMListClone());
		register(new EMListMinI());
		register(new EMListMaxI());
		register(new EMListMin());
		register(new EMListMax());
		register(new EMListSort(true, true));
		register(new EMListSort(false, true));
		register(new EMListSort(true, false));
		register(new EMListSort(false, false));
		register(new EMListIsAsc());
		register(new EMListIsDesc());
		register(new EMAsEmpty());
		register(new EMStore());
		register(new EMListPush());
		register(new EMListConcat());
		register(new EMListLeft());
		register(new EMListRight());
		register(new EMListSub());
	}

	public static void register(ExtendMethod em) {
		emMap.put(em.getName(), em);
	}
	
	public static ExtendMethod find(String emName) {
		return emMap.get(emName);
	}
}
