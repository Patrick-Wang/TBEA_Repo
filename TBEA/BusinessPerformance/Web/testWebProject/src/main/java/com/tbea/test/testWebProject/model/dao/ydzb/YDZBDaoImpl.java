package com.tbea.test.testWebProject.model.dao.ydzb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tbea.Connection.DBConnectionManager;
import com.tbea.test.testWebProject.model.entity.XJL;
import com.tbea.test.testWebProject.model.entity.YDZBBean;
import com.tbea.test.testWebProject.service.ydzb.Company;
import com.tbea.test.testWebProject.service.ydzb.CompanyGroup;

@Repository
public class YDZBDaoImpl implements YDZBDao{
	
	EntityManager entityManager;
	
	@PersistenceContext(unitName = "15DB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	private List<YDZBBean> injectV2Bean(ResultSet res) {
		List<YDZBBean> YDZBList = new ArrayList<YDZBBean>();
		if (null != res) {

			try {
//				ResultSetMetaData rsmd = res.getMetaData();
//				int len = rsmd.getColumnCount();
//				String columName = "";
//				for (int i = 0; i < len; ++i){
//					columName += rsmd.getColumnName(i + 1) + "\t";
//				}
//				 System.out.println(columName);
				while (res.next()) {
					YDZBBean ydzbbean = new YDZBBean();
					ydzbbean.setXh(res.getString(1));
					ydzbbean.setZblx(res.getString(2));
					ydzbbean.setZbmc(res.getString(3));
					ydzbbean.setByjh(res.getString(4));
					ydzbbean.setBywc(res.getString(5));
					ydzbbean.setJhwcl(res.getString(6));
					ydzbbean.setSywc(res.getString(7));
					ydzbbean.setJsyzzb(res.getString(8));
					ydzbbean.setQntq(res.getString(13));
					ydzbbean.setJqntqzzb(res.getString(14));
					ydzbbean.setJdjh(res.getString(15));
					ydzbbean.setJdlj(res.getString(16));
					ydzbbean.setJdjhwcl(res.getString(17));
					ydzbbean.setNdjh(res.getString(22));
					ydzbbean.setNdlj(res.getString(23));
					ydzbbean.setNdjhwcl(res.getString(24));
					ydzbbean.setQntqlj(res.getString(25));
					ydzbbean.setJqntqljzzb(res.getString(26));
					YDZBList.add(ydzbbean);
					
//					String rowData = "";
//					for (int i = 0; i < len; ++i){
//						rowData += res.getString(i + 1) + "\t";
//					}
//					
//					 System.out.println(rowData);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return YDZBList;
	}
	
	private List<YDZBBean> injectBean(ResultSet res) {
		List<YDZBBean> YDZBList = new ArrayList<YDZBBean>();
		if (null != res) {

			try {
//				ResultSetMetaData rsmd = res.getMetaData();
//				int len = rsmd.getColumnCount();
//				String columName = "";
//				for (int i = 0; i < len; ++i){
//					columName += rsmd.getColumnName(i + 1) + "\t";
//				}
//				 System.out.println(columName);
				while (res.next()) {
					YDZBBean ydzbbean = new YDZBBean();
					ydzbbean.setXh(res.getString("qybh"));
					ydzbbean.setZblx(res.getString("zbbh"));
					ydzbbean.setZbmc(res.getString("zbmc"));
					ydzbbean.setByjh(res.getString("byjhz"));
					ydzbbean.setBywc(res.getString("bywcz"));
					ydzbbean.setJhwcl(res.getString("jhwcl"));
					ydzbbean.setQntq(res.getString("qntqz"));
					ydzbbean.setJqntqzzb(res.getString("jtqzzb"));
					ydzbbean.setJdjh(res.getString("jdjh"));
					ydzbbean.setJdlj(res.getString("jdlj"));
					ydzbbean.setJdjhwcl(res.getString("jdwcl"));
					ydzbbean.setNdjh(res.getString("ndjhz"));
					ydzbbean.setNdlj(res.getString("ndljwcz"));
					ydzbbean.setNdjhwcl(res.getString("ndwcl"));
					ydzbbean.setQntqlj(res.getString("qntqlj"));
					ydzbbean.setJqntqljzzb(res.getString("jqntqljzzb"));
					YDZBList.add(ydzbbean);
//					String rowData = "";
//					for (int i = 0; i < len; ++i){
//						rowData += res.getString(i + 1) + "\t";
//					}
//					
//					 System.out.println(rowData);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return YDZBList;
	}
	
	private static Connection mConnection;


	private Connection getConnection() {
		if (null == mConnection) {
			DBConnectionManager manager = DBConnectionManager
					.getInstance("mobileSys");
			mConnection = manager.getConnection("mobileSys");
		}
		return mConnection;
	}

	private List<YDZBBean> getYDZBInfo_V2(int nf, int yf, String qybh,
			Connection conn) {
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.createStatement();
			
			String query = "exec p_jysj2014_zbhzcxV2 " + nf + "," + yf + ",'"
					+ qybh + "', 0, 0;";
//			System.out.println(query);
			res = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return injectV2Bean(res);
	}

	private List<YDZBBean> getYDZBInfo(int nf, int yf, String qybh,
			Connection conn) {
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.createStatement();
			String query = "exec p_jysj2014_zbfdwhz " + nf + "," + yf
					+ ",'" + qybh + "', 0, 0;";
//			System.out.println(query);
//			String query = "exec p_jysj2014_zbfdwhz " + nf + "," + yf
//					+ ",'4;5;6;7;8;9;29;22;', 0, 0;";
			
			//System.out.println(query);
			res = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return injectBean(res);
	}

	private String getCompanyIdString(Company company){
		String comIds = "";
		if (company instanceof CompanyGroup){
			CompanyGroup group = (CompanyGroup) company;
			Company[] cys = group.getCompanys();
			for (int i = 0; i < cys.length; ++i) {
				comIds += cys[i].getId() + ";";
			}
			
		} else{
			comIds = company.getId() + ";";
		}
		return comIds;
	}
	
	@Override
	public List<YDZBBean> getYDZB_V2(Calendar cal, Company company) {
		return getYDZBInfo_V2(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				getCompanyIdString(company), getConnection());
	}

	@Override
	public List<YDZBBean> getYDZB(Calendar cal, Company company) {
		return getYDZBInfo(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				getCompanyIdString(company), getConnection());
	}

	@Override
	public List<XJL> getXJL(Calendar cal) {
		Statement stmt = null;
		ResultSet res = null;
		List<XJL> xjls = new ArrayList<XJL>();
		try {
			stmt = getConnection().createStatement();
			String query = "exec yszk_xjlrb '" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "';";
			//System.out.println(query);
			res = stmt.executeQuery(query);
			
			
			try {
				while (res.next()) {
					XJL xjl = new XJL();
					xjl.setDrlr(res.getString("drlr"));
					xjl.setDrlc(res.getString("drlc"));
					xjl.setDrjll(res.getString("drjll"));
					xjl.setDylr(res.getString("dylr"));
					xjl.setDylc(res.getString("dylc"));
					xjl.setDyjll(res.getString("dyjll"));
					xjl.setDnlr(res.getString("dnlr"));
					xjl.setDnlc(res.getString("dnlc"));
					xjl.setDnjll(res.getString("dnjll"));
					xjl.setBytzs(res.getString("bytzs"));
					xjls.add(xjl);
					//System.out.println(JSONObject.fromObject(xjl).toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return xjls;
	}

}
