package com.tbea.ic.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node<T>{
	
	public static interface Visitor<T>{
		public boolean visit(Node<T> node);
	}
	
	
	Node<T> parent;
	List<Node<T>> children = new ArrayList<Node<T>>();
	T data;
	
	public int depth(){
		int depth = 1;
		Node<T> p = parent;
		while(null != p){
			p = p.parent;
			++depth;
		}
		return depth;
	}
	
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<Node<T>> getChildren(){
		return children;
	}
	
	public Node<T> getParent(){
		return parent;
	}
	
	public Node <T> append(Node<T> child){
		children.add(child);
		child.setParent(this);
		return this;
	}
	
	
	private boolean firstRootAccept(Visitor<T> visitor){
		if (null != visitor && visitor.visit(this)){
			Iterator<Node<T>> it = children.iterator();
			while(it.hasNext()){
				if (!it.next().firstRootAccept(visitor)){
					return false;
				}
			}
			return true;
		}
		return false;
	} 
	
	public void accept(Visitor<T> visitor){
		firstRootAccept(visitor);
	} 
	
	private boolean lastRootAccept(Visitor<T> visitor){
		if (null != visitor){
			Iterator<Node<T>> it = children.iterator();
			while(it.hasNext()){
				if (!it.next().lastRootAccept(visitor)){
					return false;
				}
			}
			
			if (!visitor.visit(this)){
				return false;
			}
			return true;
		}
		return false;
	} 
	
	public void reverseAccept(Visitor<T> visitor){
		lastRootAccept(visitor);
	} 
}
