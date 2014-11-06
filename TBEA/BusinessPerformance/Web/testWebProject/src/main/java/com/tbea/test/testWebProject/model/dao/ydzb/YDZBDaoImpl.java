package com.tbea.test.testWebProject.model.dao.ydzb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.tbea.Connection.DBConnectionManager;
import com.tbea.test.testWebProject.model.entity.XJL;
import com.tbea.test.testWebProject.model.entity.YDZBBean;

@Repository
public class YDZBDaoImpl implements YDZBDao {

	private List<YDZBBean> injectBean(ResultSet res) {
		List<YDZBBean> YDZBList = new ArrayList<YDZBBean>();
		if (null != res) {

			try {
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
					
					//System.out.println(JSONObject.fromObject(ydzbbean).toString());
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return YDZBList;
	}

	private Connection mConnection;

	private Connection getConnection() {
		if (null == mConnection) {
			DBConnectionManager manager = DBConnectionManager
					.getInstance("mobileSys");
			mConnection = manager.getConnection("mobileSys");
		}
		return this.mConnection;
	}

	private List<YDZBBean> getYDZBInfo_V2(int nf, int yf, String qybh,
			Connection conn) {
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.createStatement();
			
			String query = "exec p_jysj2014_zbhzcxV2 " + nf + "," + yf + ",'"
					+ qybh + "', 0, 0;";
			//System.out.println(query);
			res = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return injectBean(res);
	}

	private List<YDZBBean> getYDZBInfo(int nf, int yf, String qybh,
			Connection conn) {
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.createStatement();
			String query = "exec p_jysj2014_zbfdwhz " + nf + "," + yf
					+ ",'" + qybh + "', 0, 0;";
			//System.out.println(query);
			res = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return injectBean(res);
	}

	@Override
	public List<YDZBBean> getYDZB_V2(Calendar cal, int[] companys) {
		String coms = "";
		for (int i = 0; i < companys.length; ++i) {
			coms += companys[i] + ";";
		}
		return getYDZBInfo_V2(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				coms, getConnection());
	}

	@Override
	public List<YDZBBean> getYDZB(Calendar cal, int[] companys) {
		String coms = "";
		for (int i = 0; i < companys.length; ++i) {
			coms += companys[i] + ";";
		}
		return getYDZBInfo(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				coms, getConnection());
	}

	@Override
	public List<YDZBBean> getYDZB_V2(Calendar cal, int company) {
		return getYDZB_V2(cal, new int[]{company});
	}

	@Override
	public List<YDZBBean> getYDZB(Calendar cal, int company) {
		return getYDZB(cal, new int[]{company});
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
