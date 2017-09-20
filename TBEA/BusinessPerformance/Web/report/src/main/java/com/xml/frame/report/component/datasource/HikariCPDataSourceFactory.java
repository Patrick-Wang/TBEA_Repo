package com.xml.frame.report.component.datasource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xml.frame.report.util.Pair;
import com.xml.frame.report.util.Util;
import com.xml.frame.report.util.WatchDirectory;
import com.xml.frame.report.util.xml.Loop;
import com.xml.frame.report.util.xml.XmlWalker;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDataSourceFactory implements DataSourceFactory {
	static HikariCPDataSourceFactory ins;

	private void watch(String fName) throws Exception {
		
		File fRes = new File(fName);
		String parentPath = fRes.getParentFile().getAbsolutePath();
		Iterator<Pair<Kind<Path>, String>> it = new WatchDirectory(Paths.get(parentPath), false);
		
		while (it.hasNext()) {
			Pair<Kind<Path>, String> p = it.next();
			File f = new File(p.getSecond());
			if (p.getFirst() != StandardWatchEventKinds.ENTRY_DELETE) {
				if (f.getAbsolutePath().equals(fRes.getAbsolutePath())) {
					scan(fRes);
				}
			}
		}
	}

	void scan(File file) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		Map<String, DataSource> dsMapNew = new HashMap<String, DataSource>();
		XmlWalker.each(doc.getElementsByTagName("datasource"), null, new Loop() {

			@Override
			public void on(Element eDs) throws Exception {
				String name = eDs.getAttribute("name");
				DataSource newDs = validateDataSource(eDs, dsMap.remove(name));
				dsMapNew.put(name, newDs);
			}

		});

		delayDestroy();
		
		dsMap.clear();
		dsMap.putAll(dsMapNew);		
	}
	
	void delayDestroy(){
		Map<String, DataSource> dsMapOld = new HashMap<String, DataSource>(); 
		dsMapOld.putAll(dsMap);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	for (Entry<String, DataSource> entry : dsMapOld.entrySet()) {
						HikariDataSource hdsOld = (HikariDataSource) entry.getValue();
						if (!hdsOld.isClosed()) {
							hdsOld.close();
						}
					}
		        }
		}, 300000);
	}

	Integer intAttr(Element e, String attr) {
		if (e.hasAttribute(attr)) {
			String val = e.getAttribute(attr);
			return Util.toIntNull(val);
		}
		return null;
	}

	Long longAttr(Element e, String attr) {
		if (e.hasAttribute(attr)) {
			String val = e.getAttribute(attr);
			return Util.toLongNull(val);
		}
		return null;
	}

	HikariConfig createDataSourceConfig(Element eDs) {
		HikariConfig hc = new HikariConfig();
		hc.setUsername(eDs.getAttribute("username"));
		hc.setPassword(eDs.getAttribute("password"));
		hc.setDriverClassName(eDs.getAttribute("driverClassName"));
		hc.setJdbcUrl(eDs.getAttribute("jdbcUrl"));
		hc.setConnectionTestQuery(eDs.getAttribute("connectionTestQuery"));
		Long l = longAttr(eDs, "connectionTimeout");
		if (null != l) {
			hc.setConnectionTimeout(l);
		}
		l = longAttr(eDs, "idleTimeout");
		if (null != l) {
			hc.setIdleTimeout(l);
		}
		Integer i = intAttr(eDs, "maximumPoolSize");
		if (null != i) {
			hc.setMaximumPoolSize(i);
		}

		i = intAttr(eDs, "minimumIdle");
		if (null != i) {
			hc.setMinimumIdle(i);
		}

		i = intAttr(eDs, "maxLifetime");
		if (null != i) {
			hc.setMaxLifetime(i);
		}
		return hc;
	}

	boolean compare(HikariConfig hc, HikariDataSource hdsOld){
		
		if (!hdsOld.getUsername().equals(hc.getUsername())){
			return false;
		}
		if (!hdsOld.getPassword().equals(hc.getPassword())){
			return false;
		}
		if (!hdsOld.getDriverClassName().equals(hc.getDriverClassName())){
			return false;
		}
		if (!hdsOld.getJdbcUrl().equals(hc.getJdbcUrl())){
			return false;
		}
		if (!hdsOld.getConnectionTestQuery().equals(hc.getConnectionTestQuery())){
			return false;
		}
		if (hdsOld.getConnectionTimeout() != hc.getConnectionTimeout()){
			return false;
		}
		if (hdsOld.getIdleTimeout() != hc.getIdleTimeout()){
			return false;
		}
		if (hdsOld.getMaximumPoolSize() != hc.getMaximumPoolSize()){
			return false;
		}
		if (hdsOld.getMinimumIdle() != hc.getMinimumIdle()){
			return false;
		}
		if (hdsOld.getMaxLifetime() != hc.getMaxLifetime()){
			return false;
		}
		return true;
	}
	
	DataSource validateDataSource(Element eDs, DataSource oldDs) {
		HikariConfig hc = createDataSourceConfig(eDs);
		if (null != oldDs) {
			HikariDataSource hdsOld = (HikariDataSource) oldDs;
			if (compare(hc, hdsOld)) {
				return hdsOld;
			}else {
				if (!hdsOld.isClosed()) {
					hdsOld.close();
				}
			}
		}
		return new HikariDataSource(hc);
	}

	Map<String, DataSource> dsMap = Collections.synchronizedMap(new HashMap<String, DataSource>());

	private HikariCPDataSourceFactory(String filePath) throws Exception {
		scan(new File(filePath));
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						watch(filePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public DataSource getDataSource(String dsName) {
		return dsMap.get(dsName);
	}

	public static boolean start(String filePath) {
		if (ins == null) {
			try {
				ins = new HikariCPDataSourceFactory(filePath);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public static DataSourceFactory getInstance() {
		return ins;
	}
}
