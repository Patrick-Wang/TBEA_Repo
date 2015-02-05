package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.List;

public class DataNode {
	private Data data;
	private DataNode parent;
	private List<DataNode> subNodes = new ArrayList<DataNode>();
	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}
	/**
	 * @return the parent
	 */
	public DataNode getParent() {
		return parent;
	}
	/**
	 * @return the subNodes
	 */
	public List<DataNode> getSubNodes() {
		return subNodes;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Data data) {
		this.data = data;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(DataNode parent) {
		this.parent = parent;
	}
	/**
	 * @param subNodes the subNodes to set
	 */
	public void setSubNodes(List<DataNode> subNodes) {
		this.subNodes = subNodes;
	}
}
