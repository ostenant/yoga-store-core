package org.ostenant.yoga.store.core.bean.product;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Type")
public class Type implements Serializable {

	private static final long serialVersionUID = 1994383466242844004L;

	private Integer id;

	private String name;

	private Integer parentId;

	private String note;

	private Boolean isDisplay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", parentId=" + parentId
				+ ", note=" + note + ", isDisplay=" + isDisplay + "]";
	}

}
