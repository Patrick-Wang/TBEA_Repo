package com.tbea.ic.operation.model.entity.local;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "zbhz")
public class ZBHZ extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ny;

	private Integer zbbh;

	private String zbmc;

	private Double byjhz;

	private Double bywcz;

	private String jhwcl;

	private Double sywcz;

	private String jsyzzb;

	private Double sjdpjz;

	private String jjzzzb;

	private Double qnpjz;

	private String jjzzzb1;

	private Double qntqz;

	private String jtqzzb;

	private Double jdjh;

	private Double jdlj;

	private String jdwcl;

	private Double qntqjdwc;

	private String jqntqzzb;

	private Double sjdz;

	private String jsjdzzb;

	private Double ndjhz;

	private Double ndljwcz;

	private String ndwcl;

	private Double qntqlj;

	private String jqntqljzzb;

	private Integer rowid;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public Integer getZbbh() {
		return zbbh;
	}

	public void setZbbh(Integer zbbh) {
		this.zbbh = zbbh;
	}

	public String getZbmc() {
		return zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}

	public Double getByjhz() {
		return byjhz;
	}

	public void setByjhz(Double byjhz) {
		this.byjhz = byjhz;
	}

	public Double getBywcz() {
		return bywcz;
	}

	public void setBywcz(Double bywcz) {
		this.bywcz = bywcz;
	}

	public String getJhwcl() {
		return jhwcl;
	}

	public void setJhwcl(String jhwcl) {
		this.jhwcl = jhwcl;
	}

	public Double getSywcz() {
		return sywcz;
	}

	public void setSywcz(Double sywcz) {
		this.sywcz = sywcz;
	}

	public String getJsyzzb() {
		return jsyzzb;
	}

	public void setJsyzzb(String jsyzzb) {
		this.jsyzzb = jsyzzb;
	}

	public Double getSjdpjz() {
		return sjdpjz;
	}

	public void setSjdpjz(Double sjdpjz) {
		this.sjdpjz = sjdpjz;
	}

	public String getJjzzzb() {
		return jjzzzb;
	}

	public void setJjzzzb(String jjzzzb) {
		this.jjzzzb = jjzzzb;
	}

	public Double getQnpjz() {
		return qnpjz;
	}

	public void setQnpjz(Double qnpjz) {
		this.qnpjz = qnpjz;
	}

	public String getJjzzzb1() {
		return jjzzzb1;
	}

	public void setJjzzzb1(String jjzzzb1) {
		this.jjzzzb1 = jjzzzb1;
	}

	public Double getQntqz() {
		return qntqz;
	}

	public void setQntqz(Double qntqz) {
		this.qntqz = qntqz;
	}

	public String getJtqzzb() {
		return jtqzzb;
	}

	public void setJtqzzb(String jtqzzb) {
		this.jtqzzb = jtqzzb;
	}

	public Double getJdjh() {
		return jdjh;
	}

	public void setJdjh(Double jdjh) {
		this.jdjh = jdjh;
	}

	public Double getJdlj() {
		return jdlj;
	}

	public void setJdlj(Double jdlj) {
		this.jdlj = jdlj;
	}

	public String getJdwcl() {
		return jdwcl;
	}

	public void setJdwcl(String jdwcl) {
		this.jdwcl = jdwcl;
	}

	public Double getQntqjdwc() {
		return qntqjdwc;
	}

	public void setQntqjdwc(Double qntqjdwc) {
		this.qntqjdwc = qntqjdwc;
	}

	public String getJqntqzzb() {
		return jqntqzzb;
	}

	public void setJqntqzzb(String jqntqzzb) {
		this.jqntqzzb = jqntqzzb;
	}

	public Double getSjdz() {
		return sjdz;
	}

	public void setSjdz(Double sjdz) {
		this.sjdz = sjdz;
	}

	public String getJsjdzzb() {
		return jsjdzzb;
	}

	public void setJsjdzzb(String jsjdzzb) {
		this.jsjdzzb = jsjdzzb;
	}

	public Double getNdjhz() {
		return ndjhz;
	}

	public void setNdjhz(Double ndjhz) {
		this.ndjhz = ndjhz;
	}

	public Double getNdljwcz() {
		return ndljwcz;
	}

	public void setNdljwcz(Double ndljwcz) {
		this.ndljwcz = ndljwcz;
	}

	public String getNdwcl() {
		return ndwcl;
	}

	public void setNdwcl(String ndwcl) {
		this.ndwcl = ndwcl;
	}

	public Double getQntqlj() {
		return qntqlj;
	}

	public void setQntqlj(Double qntqlj) {
		this.qntqlj = qntqlj;
	}

	public String getJqntqljzzb() {
		return jqntqljzzb;
	}

	public void setJqntqljzzb(String jqntqljzzb) {
		this.jqntqljzzb = jqntqljzzb;
	}

	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	@Override
	public String toString() {
		return "YDZBFDW [id=" + getId() + ", ny=" + ny + ", zbbh=" + zbbh
				+ ", zbmc=" + zbmc + ", byjhz=" + byjhz + ", bywcz=" + bywcz
				+ ", jhwcl=" + jhwcl + ", sywcz=" + sywcz + ", jsyzzb="
				+ jsyzzb + ", sjdpjz=" + sjdpjz + ", jjzzzb=" + jjzzzb
				+ ", qnpjz=" + qnpjz + ", jjzzzb1=" + jjzzzb1 + ", qntqz="
				+ qntqz + ", jtqzzb=" + jtqzzb + ", jdjh=" + jdjh + ", jdlj="
				+ jdlj + ", jdwcl=" + jdwcl + ", qntqjdwc=" + qntqjdwc
				+ ", jqntqzzb=" + jqntqzzb + ", sjdz=" + sjdz + ", jsjdzzb="
				+ jsjdzzb + ", ndjhz=" + ndjhz + ", ndljwcz=" + ndljwcz
				+ ", ndwcl=" + ndwcl + ", qntqlj=" + qntqlj + ", jqntqljzzb="
				+ jqntqljzzb + ", rowid=" + rowid + "]";
	}

}
