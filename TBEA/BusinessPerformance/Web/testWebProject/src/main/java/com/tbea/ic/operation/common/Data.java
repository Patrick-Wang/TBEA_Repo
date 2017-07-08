package com.tbea.ic.operation.common;

public class Data {
	Integer id;
    String value;
	public Data(Integer id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	public Data() {
		super();
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
