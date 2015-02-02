package com.tbea.ic.operation.model.entity.jygk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "jygk_dwxx")
public class DWXX extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = -6935890991996034702L;

	private String name;

	private DWXX parent;

	private List<DWXX> children = new ArrayList<DWXX>();

	private Set<ZBXX> sjzbxxs;

	private Set<ZBXX> jhzbxxs;

	@Id
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_ID")
	public DWXX getParent() {
		return parent;
	}

	public void setParent(DWXX parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	public List<DWXX> getChildren() {
		return children;
	}

	public void setChildren(List<DWXX> children) {
		this.children = children;
	}


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "jygk_dw_refer_zb_sjzb", joinColumns = { @JoinColumn(name = "dwid", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "zbid", referencedColumnName = "id") })
	public Set<ZBXX> getSjzbxxs() {
		return sjzbxxs;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "jygk_dw_refer_zb_jhzb", joinColumns = { @JoinColumn(name = "dwid", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "zbid", referencedColumnName = "id") })
	public Set<ZBXX> getJhzbxxs() {
		return jhzbxxs;
	}

	/**
	 * @param sjzbxxs the sjzbxxs to set
	 */
	public void setSjzbxxs(Set<ZBXX> sjzbxxs) {
		this.sjzbxxs = sjzbxxs;
	}

	/**
	 * @param jhzbxxs the jhzbxxs to set
	 */
	public void setJhzbxxs(Set<ZBXX> jhzbxxs) {
		this.jhzbxxs = jhzbxxs;
	}

}
