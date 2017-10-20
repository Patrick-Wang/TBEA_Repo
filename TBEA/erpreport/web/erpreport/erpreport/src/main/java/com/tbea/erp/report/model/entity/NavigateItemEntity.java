package com.tbea.erp.report.model.entity;

import com.speed.frame.model.entity.AbstractReadWriteEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "CUX_NAVIGATEITEM_T")
public class NavigateItemEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer parent;
	String title;
	String icon;
	Integer spread; //0, 1
	String url;
	Integer type;//, --0 top, 1 left


	List<NavigateItemEntity> children = new ArrayList<NavigateItemEntity>();

	public Integer getParent() {
		return parent;
	}

	@Transient
	public List<NavigateItemEntity> getChildren() {
		return children;
	}

	public void setChildren(List<NavigateItemEntity> children) {
		this.children = children;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSpread() {
		return spread;
	}

	public void setSpread(Integer spread) {
		this.spread = spread;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
