package com.tbea.ic.operation.service.report;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.service.util.pipe.configurator.ConfiguratorUtil;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class AdvanceAccByGroup{

	IAccumulator stub;
	

	
	IAccumulator proxy = new IAccumulator(){

		private boolean isSpecialCompany(CompanyType tp) {
			return tp == CompanyType.DJBGS || tp == CompanyType.WYDXDLC
					|| tp == CompanyType.WLGS || tp == CompanyType.ZHGS_MYGS
					|| tp == CompanyType.JWZHGS;
		} 
		
		private boolean isDeprecated(CompanyType tp){
			return tp == CompanyType.RDGS || tp == CompanyType.DLBZGS;
		}
		
		private void postHandleWxsr(List<Double> result, int col, Date start, Date end,
				List<Integer> zbs, List<Company> companies){
			if (zbs.contains(336)){
				int index = zbs.indexOf(336);
				if (index >= 0){
					List<Company> compTmpsWxsr = new ArrayList<Company>();
					List<Company> compTmpsXssr = new ArrayList<Company>();
					for (int i = 0; i < companies.size(); ++i){
						if (!isDeprecated(companies.get(i).getType())){
							if (isSpecialCompany(companies.get(i).getType())){
								compTmpsXssr.add(companies.get(i));
							}else{
								compTmpsWxsr.add(companies.get(i));
							}
						}
					}
					
					if (!compTmpsXssr.isEmpty()){

						List<Integer> zbsTmp = new ArrayList<Integer>();
						zbsTmp.add(336);
						Double sr = null;
						if (!compTmpsWxsr.isEmpty()){
							List<Double> wxsr = stub.compute(col, start, end, zbsTmp, compTmpsWxsr);
							sr = wxsr.get(0);
						}
					
						zbsTmp.set(0, GSZB.XSSR6.value());
						List<Double> xssr = stub.compute(col, start, end, zbsTmp, compTmpsXssr);
						sr = MathUtil.sum(sr, xssr.get(0));
						
						result.set(index, sr);
					}
					
				}
			}
		}
		
		
		private void postHandleTimepoint(List<Double> result, int col, Date start, Date end,
				List<Integer> zbs, List<Company> companies){
			List<Integer> tmpZbs = null;
			for (int i = zbs.size() - 1; i >= 0; --i){
				if (ConfiguratorUtil.getTimePointNumberZbs().contains(zbs.get(i))){
					if (tmpZbs == null){
						tmpZbs = new ArrayList<Integer>();
						tmpZbs.add(null);
					}
					tmpZbs.set(0, zbs.get(i));
					Double value = stub.compute(col, end, end, tmpZbs, companies).get(0);
					result.set(i, value);
				}
			}
		}
		
		@Override
		public List<Double> compute(int col, Date start, Date end,
				List<Integer> zbs, List<Company> companies) {
			List<Double> result = stub.compute(col, start, end, zbs, companies);

			postHandleWxsr(result, col, start, end, zbs, companies);
			
			postHandleTimepoint(result, col, start, end, zbs, companies);
			
			return result;
		}
		
	};
	
	
	public AdvanceAccByGroup(IAccumulator stub){
		this.stub = stub;
	}
	

	
	public List<Double> computeByCompanies(int col, Date start, Date end,
			List<List<Integer>> zbs, List companies) {
		List<Double> result = new ArrayList<Double>();
		List<Company> compsTmp = new ArrayList<Company>();
		compsTmp.add(null);
		for (int i = 0; i < companies.size(); ++i){
			if (companies.get(i) != null){
				if (companies.get(i) instanceof Company){
					compsTmp.set(0, (Company) companies.get(i));
					result.addAll(proxy.compute(col, start, end, zbs.get(i), compsTmp));
				}else{
					result.addAll(proxy.compute(col, start, end, zbs.get(i), (List<Company>) companies.get(i)));
				}
			}else{
				result.addAll(Util.resize(new ArrayList<Double>(), zbs.get(i).size()));
			}
		}
		return result;
	}
	
	public List<Double> computeByZbs(int col, Date start, Date end,
			List<Integer> zbs, List<List<Company>> companies) {
		List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < zbs.size(); ++i){
			result.addAll(proxy.compute(col, start, end, zbs, companies.get(i)));
		}
		return result;
	}

	
	public Double computeSumZbs(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<Double> result = proxy.compute(col, start, end, zbs, companies);
		return MathUtil.sum(result);
	}

}
