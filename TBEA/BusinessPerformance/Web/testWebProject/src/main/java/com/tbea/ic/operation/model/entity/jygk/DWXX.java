package com.tbea.ic.operation.model.entity.jygk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "jygk_dwxx")
public class DWXX extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private DWXX parent;

	private List<DWXX> children = new ArrayList<DWXX>();

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

}
