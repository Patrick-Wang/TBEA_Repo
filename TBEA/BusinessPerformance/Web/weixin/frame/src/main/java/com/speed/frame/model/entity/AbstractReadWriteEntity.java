package com.speed.frame.model.entity;

import javax.persistence.Column;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

public abstract class AbstractReadWriteEntity extends AbstractEntity {

	protected AbstractReadWriteEntity() {
		super();
	}

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
}
