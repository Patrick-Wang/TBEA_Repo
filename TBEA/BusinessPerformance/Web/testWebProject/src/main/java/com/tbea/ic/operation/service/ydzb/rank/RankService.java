package com.tbea.ic.operation.service.ydzb.rank;

import java.sql.Date;
import java.util.List;

public interface RankService {

	List<String[]> getJhlrRank(Date date);

	List<String[]> getLjlrRank(Date date);

	List<String[]> getJxjlRank(Date date);	
	
	List<String[]> getRjlrRank(Date date);

	List<String[]> getRjsrRank(Date date);

	List<String[]> getXmgsRjsrRank(Date date);

	List<String[]> getXmgsRjlrRank(Date date);

	List<String[]> getXmgsJhlrRank(Date date);	
}
