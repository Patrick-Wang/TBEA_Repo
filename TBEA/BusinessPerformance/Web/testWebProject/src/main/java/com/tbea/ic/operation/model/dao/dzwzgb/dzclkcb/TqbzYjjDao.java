package com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.dzwzgb.TqbzYjjEntity;



public interface TqbzYjjDao {
	List<TqbzYjjEntity> getByDate(Date d);

}
