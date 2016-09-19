package com.tbea.ic.operation.model.entity.jygk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "jygk_zbxx")
public class ZBXX  extends AbstractReadWriteEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer sequence;
	private Integer parent;

	private List<ZBXX> children = new ArrayList<ZBXX>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}


	@Column(name = "parent_id")
	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	public List<ZBXX> getChildren() {
		return children;
	}

	public void setChildren(List<ZBXX> children) {
		this.children = children;
	}
	
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	public ZBXX clone(){
		ZBXX zbxx = new ZBXX();
		zbxx.parent = parent;
		zbxx.sequence = sequence;
		zbxx.name = name;
		zbxx.setId(getId());
		List<ZBXX> childs = new ArrayList<ZBXX>();
		for (ZBXX zb : children){
			childs.add(zb.clone());
		}
		zbxx.setChildren(childs);
		return zbxx;
	}
}
