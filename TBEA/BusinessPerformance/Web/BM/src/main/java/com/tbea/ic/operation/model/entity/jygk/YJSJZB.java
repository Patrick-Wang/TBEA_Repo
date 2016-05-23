package com.tbea.ic.operation.model.entity.jygk;

import java.sql.Date;

public interface YJSJZB{
	public int getId();
	public DWXX getDwxx();
	public ZBXX getZbxx();
	public Integer getNf();
	public Integer getYf();
	public Double getValue();
	public SHZT getShzt();
	public Date getXgsj();
	public void setDwxx(DWXX dwxx);
	public void setZbxx(ZBXX zbxx);
	public void setNf(Integer nf);
	public void setYf(Integer yf);
	public void setValue(Double val);
	public void setShzt(SHZT shzt);
	public void setXgsj(Date sj);
}
