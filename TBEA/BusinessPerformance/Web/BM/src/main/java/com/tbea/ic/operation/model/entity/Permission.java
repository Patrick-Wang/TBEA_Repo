package com.tbea.ic.operation.model.entity;

public class Permission {
	Integer id;
	Boolean seasonPredict = false;

	/**
	 * @return the seasonPredict
	 */
	public Boolean getSeasonPredict() {
		return seasonPredict;
	}

	/**
	 * @param seasonPredict the seasonPredict to set
	 */
	public void setSeasonPredict(Boolean seasonPredict) {
		this.seasonPredict = seasonPredict;
	}
}
