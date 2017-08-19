package com.tbea.ic.operation.common.indi.relation;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.WatchDirectory;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.xml.frame.report.util.Pair;
import com.xml.frame.report.util.XmlUtil;
import com.xml.frame.report.util.XmlUtil.OnLoop;

@Repository
public class Relationships {
	private static String resDir;
	private static String resPath;
	static {
		try {
			resDir = new URI(Relationships.class.getClassLoader().getResource("")
					.getPath()).getPath().substring(1)
					+ "META-INF";
			resPath = resDir + "/指标关联.xml";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	CompanyManager compMgr;
	
	@Autowired
	ZBXXDao zbxxDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	Map<String, Integer> indiByName = new HashMap<String, Integer>();
	
	List<Map<Integer, RelationGroup>> relaShares = new ArrayList<Map<Integer, RelationGroup>>();
	
	List<List<RelationSum>> relaSums = new ArrayList<List<RelationSum>>();
	
	Map<String, Integer> indis = new HashMap<String, Integer>();
	
	private void watch() throws Exception {
		Iterator<Pair<Kind<Path>, String>> it = new WatchDirectory(Paths.get(resDir), false);
		while (it.hasNext()) {
			Pair<Kind<Path>, String> p = it.next();
			if (p.getFirst() != StandardWatchEventKinds.ENTRY_DELETE &&
				p.getSecond().endsWith("指标关联.xml")) {
				this.reload();
			}
		}
	}
	
	@Autowired
	public void init(){
		try{
			this.reload();			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					try{
						watch();		
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	
	private void buildSharedIndis(){
		indis.clear();
		relaShares.clear();
		relaSums.clear();
		for (int i = 0; i <= ZBType.BYSJ.ordinal(); ++i){
			relaShares.add(new HashMap<Integer, RelationGroup>());
			relaSums.add(new ArrayList<RelationSum>());
		}
	}
	
	private void parseShare(Document doc) throws Exception{
		XmlUtil.each(doc.getElementsByTagName("share"), new OnLoop(){

			@Override
			public void on(Element elem) throws Exception {
				Set<ZBType> types = RelationUtil.parseTypes(elem);
				if (types == null || types.isEmpty()){
					return;
				}
				
				List<RelationGroup> rgs = RelationGroup.parse(elem, compMgr, dwxxDao, indis);
				if (null != rgs){
					Iterator<ZBType> it = types.iterator();
					while (it.hasNext()){
						ZBType type = it.next();
						for (RelationGroup rg : rgs){
							if (relaShares.get(type.ordinal()).containsKey(rg.indi())){
								relaShares.get(type.ordinal()).get(rg.indi()).merge(rg);
							}else{
								relaShares.get(type.ordinal()).put(rg.indi(), rg);
							}  
						}						
					}
				}
			}
			
		});
	}
	
	public void reload() throws Exception{
		buildSharedIndis();
		DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(resPath));
		parseIndis(doc);
		parseShare(doc);
		parseSum(doc);
	}

	private void parseIndis(Document doc) throws Exception {
		NodeList indicators = doc.getElementsByTagName("indicators");
		XmlUtil.eachChildren(XmlUtil.element(indicators, 0), new OnLoop(){

			@Override
			public void on(Element elem) throws Exception {
				Integer indi = RelationUtil.parseIndi(elem, zbxxDao);
				if (indi == null){
					return;
				}
				indis.put(elem.getTagName(), indi);
			}
		});
	}

	private void parseSum(Document doc) throws Exception {
		XmlUtil.each(doc.getElementsByTagName("sum"), new OnLoop(){

			@Override
			public void on(Element elem) throws Exception {
				Set<ZBType> types = RelationUtil.parseTypes(elem);
				if (types == null || types.isEmpty()){
					return;
				}
				
				List<RelationSum> rss = RelationSum.parse(elem, compMgr, dwxxDao, indis);
				if (null != rss){
					Iterator<ZBType> it = types.iterator();
					while (it.hasNext()){
						ZBType type = it.next();
						for (RelationSum rs : rss){
							relaSums.get(type.ordinal()).add(rs);
						}
					}
				}
			}
			
		});
	}

	public RelationGroup getShared(ZBType zbType, Integer zbId) {
		return this.relaShares.get(zbType.ordinal()).get(zbId);
	}

	public List<RelationSum> getSums(ZBType zbType) {
		return relaSums.get(zbType.ordinal());
	}
	
}
