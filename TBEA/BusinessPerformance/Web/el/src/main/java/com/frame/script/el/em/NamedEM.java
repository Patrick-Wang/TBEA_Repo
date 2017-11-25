package com.frame.script.el.em;

public abstract class NamedEM implements ExtendMethod{

	protected String name;
	
	public NamedEM(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
