package com.tbea.ic.operation.reportframe.el.em;





public abstract class NamedEM implements ExtendMethod{

	private String name;

	public NamedEM(String name) {
		super();
		this.name = name;
	}


	@Override
	public boolean check(String name) {
		return this.name.equals(name);
	}

}
