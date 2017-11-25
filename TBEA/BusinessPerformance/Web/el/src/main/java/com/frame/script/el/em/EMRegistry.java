package com.frame.script.el.em;

import java.util.HashMap;
import java.util.Map;

import com.frame.script.el.em.list.EMListColumn;
import com.frame.script.el.em.list.EMListIsAsc;
import com.frame.script.el.em.list.EMListIsDesc;
import com.frame.script.el.em.list.EMListLast;
import com.frame.script.el.em.list.EMListLastColumn;
import com.frame.script.el.em.list.EMListPack;
import com.frame.script.el.em.list.broken.EM2Fixed;
import com.frame.script.el.em.list.broken.EMDistinct;
import com.frame.script.el.em.list.broken.EMListConcat;
import com.frame.script.el.em.list.broken.EMListLastRemoveAt;
import com.frame.script.el.em.list.broken.EMListLastRmCol;
import com.frame.script.el.em.list.broken.EMListPush;
import com.frame.script.el.em.list.broken.EMListRemoveAt;
import com.frame.script.el.em.list.broken.EMListResize;
import com.frame.script.el.em.list.broken.EMListRmCol;
import com.frame.script.el.em.list.broken.EMListSort;
import com.frame.script.el.em.list.copy.EMListClone;
import com.frame.script.el.em.list.copy.EMListLastPick;
import com.frame.script.el.em.list.copy.EMListLastSub;
import com.frame.script.el.em.list.copy.EMListLeft;
import com.frame.script.el.em.list.copy.EMListMax;
import com.frame.script.el.em.list.copy.EMListMaxI;
import com.frame.script.el.em.list.copy.EMListMid;
import com.frame.script.el.em.list.copy.EMListMin;
import com.frame.script.el.em.list.copy.EMListMinI;
import com.frame.script.el.em.list.copy.EMListPick;
import com.frame.script.el.em.list.copy.EMListRight;
import com.frame.script.el.em.list.copy.EMListSub;
import com.frame.script.el.em.list.copy.EMTranspose;

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
		register(new EMAsEmpty());
		register(new EMStore());
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
		register(new EMListPush());
		register(new EMListConcat());
		register(new EMListLeft());
		register(new EMListRight());
		register(new EMListSub());
		register(new EMListLastSub());
		register(new EMListMid());
		register(new EMListPick());
		register(new EMListLastPick());
	}

	public static void register(ExtendMethod em) {
		emMap.put(em.getName(), em);
	}
	
	public static ExtendMethod find(String emName) {
		return emMap.get(emName);
	}
}
