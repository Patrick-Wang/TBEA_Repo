package  com.javaBean;

public class UserBean {
	//�ֻ��
	private String imei;
	//sim����
	private String simcard;
	//�û���
	private String userid;
	//����
	private String pwd;
	//�˵�����Ȩ��
	private String menuqx;
	//��˾ID
	private String companyID;
	//��˾Ȩ��
	private String companyqx;
	//��¼�ɹ�flag
	private boolean loginFlag;
	public boolean isLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getSimcard() {
		return simcard;
	}
	public void setSimcard(String simcard) {
		this.simcard = simcard;
	}
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
	public String getMenuqx() {
		return menuqx;
	}
	public void setMenuqx(String menuqx) {
		this.menuqx = menuqx;
	}
	public String getCompanyqx() {
		return companyqx;
	}
	public void setCompanyqx(String companyqx) {
		this.companyqx = companyqx;
	}
	
}
