package org.ostenant.yoga.store.core.query;

import java.io.Serializable;

public class KeyAndName implements Serializable {

	private static final long serialVersionUID = 8071726131028202394L;

	private String key;

	private String name;

	public KeyAndName() {
	}

	public KeyAndName(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "KeyAndName [key=" + key + ", name=" + name + "]";
	}

}
