package com.tbea.test.testWebProject.model.dao.ydzb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tbea.Connection.DBConnectionManager;
import com.tbea.test.testWebProject.model.entity.YDZBBean;

@Repository
public class YDZBDaoImpl implements YDZBDao {

	private List<YDZBBean> getYDZBInfo(int nf, int yf, String qybh, int zblx,
			Connection conn) throws Exception {
		List<YDZBBean> YDZBList = new ArrayList<YDZBBean>();
		Statement stmt = null;
		ResultSet res = null;
		stmt = conn.createStatement();
		res = stmt.executeQuery("exec dbo.p_jysj2014_sjzbhzcx " + nf + "," + yf
				+ ",'" + qybh + "'," + zblx + ";");
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
		}
		return YDZBList;
	}

	public List<YDZBBean> getYDZB(Calendar cal) {
		List<YDZBBean> ydzbs = null;
		try {

			DBConnectionManager manager = DBConnectionManager
					.getInstance("mobileSys");

			Connection conn = manager.getConnection("mobileSys");

			ydzbs = getYDZBInfo(cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH), "4;5;6;7;8;9;10;11", 0, conn);

		} catch (Exception e) {

		}
		return ydzbs;
	}

}
