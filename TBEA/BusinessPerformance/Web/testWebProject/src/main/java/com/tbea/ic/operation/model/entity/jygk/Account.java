package com.tbea.ic.operation.model.entity.jygk;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "jygk_account")
public class Account extends AbstractReadWriteEntity implements Serializable {

	public final static Account KNOWN_ACCOUNT_JYFZ = new Account();
	public final static Account KNOWN_ACCOUNT_GFGS = new Account();
	static{
		KNOWN_ACCOUNT_JYFZ.setName("KNOWN_ACCOUNT_JYFZ");
		KNOWN_ACCOUNT_JYFZ.setRole(1);
		KNOWN_ACCOUNT_GFGS.setName("KNOWN_ACCOUNT_GFGS");
		KNOWN_ACCOUNT_GFGS.setRole(0);
	}
	
	private static final long serialVersionUID = 1L;

	private String name;

	private String password;
	
	private int role;

	private Set<DWXX> dwxxs;
	
	private Integer deprecated;


	public Integer getDeprecated() {
		return deprecated;
	}

	public void setDeprecated(Integer deprecated) {
		this.deprecated = deprecated;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getRole(){
		return role;
	}
	
	public void setRole(int role){
		this.role = role;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		name = "jygk_dw_refer_account",
		joinColumns = { 
			@JoinColumn(name = "account_ID", referencedColumnName = "id") 
		}, 
		inverseJoinColumns = { 
			@JoinColumn(name = "dwid", referencedColumnName = "id") 
		})
	public Set<DWXX> getDwxxs() {
		return dwxxs;
	}
	
	public void setDwxxs(Set<DWXX> dwxxs) {
		this.dwxxs = dwxxs;
	}
	

}
