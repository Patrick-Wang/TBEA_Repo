package com.tbea.ic.operation.common;

public abstract class RequestHandler<T> {
	RequestHandler<T> next;
	public RequestHandler<T> add(RequestHandler<T> next){
		this.next = next;
		return next;
	}
	
	private boolean callNext(T request){
		if (next != null && next.handle(request)){
			return true;
		}
		return false;
	}
	
	public boolean handle(T request){
		if (onHandle(request)){
			return true;
		}else{
			return callNext(request);
		}
	}

	abstract protected boolean onHandle(T request);
//	abstract protected void onReset();
	
//	public void reset(){
//		this.onReset();
//		if (next != null){
//			next.reset();
//		}
//	}

	
}
