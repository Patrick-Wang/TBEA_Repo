package  com.tbea.ic.scanner.net.entity;

import java.util.List;

public class User {
	String userid;
	String pwd;
	List<DataNode> rights;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public List<DataNode> getRights() {
		return rights;
	}
	public void setRights(List<DataNode> rights) {
		this.rights = rights;
	}
	
}
