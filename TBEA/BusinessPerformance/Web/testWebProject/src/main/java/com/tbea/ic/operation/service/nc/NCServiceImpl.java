package com.tbea.ic.operation.service.nc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.CommonMethod;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.dao.nc.NCZBDao;
import com.tbea.ic.operation.model.entity.jygk.NCZB;

@Service
@Transactional("transactionManager")
public class NCServiceImpl implements NCService {

	@Autowired
	NCZBDao nczbDao;

	@Autowired
	DWXXDao dwxxDao;

	@Autowired
	ZBXXDao zbxxDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	public static Map<String, CompanyType> companyMap = new HashMap<String, CompanyType>();
	static {
		companyMap.put("0202AA000000", CompanyType.SBGS);
		companyMap.put("0303AA000000", CompanyType.LLGS);
		companyMap.put("0304AA000000", CompanyType.DLGS);
		companyMap.put("0203AA000000", CompanyType.HBGS);
		companyMap.put("CC02", CompanyType.XBC);
		companyMap.put("CC03", CompanyType.XLC);
		companyMap.put("040203AA0000", CompanyType.XNYGS);
		companyMap.put("040202AA0000", CompanyType.XTNYGS);
		companyMap.put("CC11", CompanyType.TCNY);
		companyMap.put("CC10", CompanyType.XNYGS);
		companyMap.put("060100000000", CompanyType.JCKGS_JYDW);
		companyMap.put("CC04", CompanyType.GJGCGS_GFGS);
	}

	private void mergeNCZB(CompanyType companyType, GSZB zb, int nf, int yf,
			Object data) {
		Company company = companyManager.getBMDBOrganization().getCompany(
				companyType);
		NCZB nczb = null;
		int zbid = zb.getValue();
		nczb = nczbDao.getNCZB(company, zbid, nf, yf);
		if (null == nczb) {
			nczb = new NCZB();
		}
		nczb.setDwxx(dwxxDao.getById(company.getId()));
		nczb.setZbxx(zbxxDao.getById(zbid));
		nczb.setNf(nf);
		nczb.setYf(yf);
		nczb.setNczbz(CommonMethod.objectToDouble(data));
		nczb.setDrsj(new Date(System.currentTimeMillis()));
	}

	@Override
	public void connetToNCSystem(String ver, Calendar date,
			List<String> codeList) {
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=dm01-scan.tbea.com.cn)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=orcl)))";
		String userName = "iufo";
		String userPwd = "cwf5e7n9";

		Connection dbConn = null;
		try {
			Class.forName(driverName).newInstance();

			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			Statement statement = dbConn
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sdf.format(date.getTime());
			int size = codeList.size();
			StringBuffer code = new StringBuffer("");
			for (int i = 0; i < size; ++i) {
				if (0 == i) {
					code.append(", '");
				} else {
					code.append("'");
				}
				code.append(codeList.get(i));
				code.append("'");
			}
			String sql = "select a.unit_code as 公司编码"
					+ ", d.M10059 as 本月利润总额" // id 1
					+ ", d.M10107 as 本月销售收入" // id 6
					+ ", e.m10161 as 本月经营性净现金流" // id 29
					+ ", C.M10147 as 本月应收帐款期末数" // id 32
					+ ", C.M10083 as 本月存货期末数 " // id 35
					+ ", C.M10050 as 本月资产总额期末数" // id 179
					+ ", C.M10007 as 本月固定资产期末数"// id 180
					+ ", C.M10067 as 本月净资产期末数" // id 181
					+ ", C.M10066 as 本月净资产期初数" // id 182
					+ ", d.M10059 as 本月净利润" // id 183
					+ ", C.M10125 as 本月负债总额期末数" // id 184
					+ ", (d.M10091+d.M10099+d.M10028) as 月度三项费用" // id 64
					+ " from iufo_unit_info a" + ", iufo_measure_pubdata b"
					+ ",iufo_measure_data_aabf9rn7 c"
					+ ", iufo_measure_data_w67a04wo d"
					+ ", iufo_measure_data_9hzo24a7 e"
					+ " where a.unit_id=b.keyword1"
					+ " and b.alone_id=c.alone_id"
					+ " and b.alone_id=d.alone_id"
					+ " and b.alone_id=e.alone_id" + " and b.inputdate='"
					+ strDate + "'" + " and b.ver = '" + ver + "'"
					+ " and a.unit_code in (" + code + ")"
					+ " order by a.unit_code";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);

			String unitCode = null;
			CompanyType companyType = null;
			int nf = 0;
			int yf = 0;
			while (rs.next()) {
				unitCode = String.valueOf(rs.getObject(1));
				companyType = companyMap.get(unitCode);
				nf = date.get(Calendar.YEAR);
				// Calendar.MONTH获得月份正常情况下为自然月-1,
				// 且当前需求中数据的月份为存储时间的前一个月，所以在下面公式调用中不必+1
				yf = date.get(Calendar.MONTH);

				// 存储NC对应指标
				mergeNCZB(companyType, GSZB.LRZE, nf, yf, rs.getObject(2));
				mergeNCZB(companyType, GSZB.XSSR, nf, yf, rs.getObject(3));
				mergeNCZB(companyType, GSZB.JYXJXJL, nf, yf, rs.getObject(4));
				mergeNCZB(companyType, GSZB.YSZK, nf, yf, rs.getObject(5));
				mergeNCZB(companyType, GSZB.CH, nf, yf, rs.getObject(6));
				mergeNCZB(companyType, GSZB.ZCZE, nf, yf, rs.getObject(7));
				mergeNCZB(companyType, GSZB.GDZC, nf, yf, rs.getObject(8));
				mergeNCZB(companyType, GSZB.JZCQMS, nf, yf, rs.getObject(9));
				mergeNCZB(companyType, GSZB.JZCQCS, nf, yf, rs.getObject(10));
				mergeNCZB(companyType, GSZB.JLR, nf, yf, rs.getObject(11));
				mergeNCZB(companyType, GSZB.FZZEQMS, nf, yf, rs.getObject(12));
				mergeNCZB(companyType, GSZB.SXFY, nf, yf, rs.getObject(13));
			}
			if (null != rs) {
				rs.close();
				rs = null;
			}
			if (null != statement) {
				statement.close();
				statement = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != dbConn) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dbConn = null;
			}
		}
	}

	@Override
	public List<NCZB> getNCZBByDate(int nf, int yf) {
		return nczbDao.getNCZBByDate(nf, yf);
	}

}
