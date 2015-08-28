package com.tbea.ic.carrier.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keywords")
public class KeyWords {
	
	@Id
	@Column(name = "text")
	String text;

	@Column(name = "fixed")
	String fixed;
	
	/**
	 * @return the key
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param key the key to set
	 */
	public void setText(String key) {
		this.text = key;
	}

	/**
	 * @return the fixed
	 */
	public String getFixed() {
		return fixed;
	}

	/**
	 * @param fixed the fixed to set
	 */
	public void setFixed(String fixed) {
		this.fixed = fixed;
	}
			
	
}
