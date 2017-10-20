package com.speed.frame.model.entity;

import javax.persistence.Column;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

public class AbstractEntity {

	public AbstractEntity() {
		super();
	}

	private int id = 0;

	private int entityVersion = 0;

	@Version
	@Column(name = "VERSION")
	@XmlTransient
	public int getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(int entityVersion) {
		this.entityVersion = entityVersion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean checkConsistency() {
		return true;
	}
}
