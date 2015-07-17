package com.tbea.javaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class getData {
			
			//��ȡ�û���Ϣ
			public UserBean getUserInfo(String username,String password,Connection conn) throws Exception{
				UserBean userbean=new UserBean();
				
				Statement stmt = null;
				ResultSet res = null;
					// ��ѯ��ݿ�
					//conn = new ConnDB().getConnection();
					stmt = conn.createStatement();
					res = stmt.executeQuery("select * from phoneuser where imei='"+username+"' and pwd='"+password+"';");
					// ��ݽ���ж�
					while (res.next()) {						
						userbean.setImei(res.getString(1));	
						userbean.setSimcard(res.getString(2));	
						userbean.setUserid(res.getString(3));	
						userbean.setPwd(res.getString(4));	
						userbean.setMenuqx(res.getString(5));
						userbean.setCompanyID(res.getString(6));							
						}	
					if (res != null) {
						res.close();
						res = null;
					}
					if (stmt != null) {
						stmt.close();
						stmt = null;
					}
					
				return userbean;
				
			}
			//�޸�����
			public boolean changeUserPwd(String newPwd,UserBean bean,Connection conn) throws Exception{
				boolean bool = false;
				PreparedStatement ps = null;	
				try {					
				ps = conn.prepareStatement("update phoneuser set pwd='"+newPwd+"' where userid = '" + bean.getUserid() + "' and pwd = '"+bean.getPwd()+"'");
				int i = ps.executeUpdate();
				if (i == 1) {
					bool = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return bool;
			}
			// ���Ӧ���˿���Ϣ
			public List<YSZKBean> getYSZKXXInfo(String qybh,Connection conn) throws Exception{
				List<YSZKBean> YSZKList=new ArrayList<YSZKBean>();
				
				Statement stmt = null;
				ResultSet res = null;
					// ��ѯ��ݿ�
					//conn = new ConnDB().getConnection();
					stmt = conn.createStatement();
					res = stmt.executeQuery("select * from lt_bb_mas where qybh in("+qybh+");");
					// ��ݽ���ж�
					while (res.next()) {
						YSZKBean yszkbean = new YSZKBean();
						yszkbean.setQybh(res.getString(1));
						yszkbean.setQymc(res.getString(2));	
						yszkbean.setYsye(res.getString(3));	
						yszkbean.setYqk(res.getString(4));							
						yszkbean.setRhk(res.getString(5));	
						yszkbean.setYhk(res.getString(6));
						yszkbean.setRqy(res.getString(7));						
						yszkbean.setYqy(res.getString(8));	
						yszkbean.setSjrq(res.getString(9));	
						YSZKList.add(yszkbean);
						}									
				return YSZKList;
			}
			
			// ����ڻ���ϸ
			public List<QHMXBean> getQHMXInfo(String companyqx,String company,String cllb,Connection conn) throws Exception{
				List<QHMXBean> QHMXList=new ArrayList<QHMXBean>();
				Statement stmt = null;
				ResultSet res = null;
				java.util.Date ud = new Date();
				java.sql.Date today = new java.sql.Date(ud.getTime());
					// ��ѯ��ݿ�
					//conn = new ConnDB().getConnection();
					stmt = conn.createStatement();
					res = stmt.executeQuery("select * from dbo.f_tb2014_qhccmx("+companyqx+",'"+company+"',"+cllb+",'"+today+"');");
					// ��ݽ���ж�
					while (res.next()) {
						QHMXBean qhmxbean = new QHMXBean();
						qhmxbean.setQybh(res.getString(1));
						qhmxbean.setQymc(res.getString(2));
						qhmxbean.setType(res.getString(3));	
						qhmxbean.setCcl(res.getString(4));	
						qhmxbean.setCcjj(res.getString(5));	
						qhmxbean.setPrice(res.getString(6));	
						qhmxbean.setYk(res.getString(7));
						qhmxbean.setYkje(res.getString(8));
						String date="";
						if(qhmxbean.getType()!=null&&!qhmxbean.getType().equals("")){
							date=qhmxbean.getType().substring(2);
						}
						qhmxbean.setDate(date);
						QHMXList.add(qhmxbean);
						}								
				return QHMXList;
			}			
						
			//����¶�ָ��
			public List<YDZBBean> getYDZBInfo(int nf,int yf,String qybh,int zblx,Connection conn) throws Exception{
				 List<YDZBBean> YDZBList=new ArrayList<YDZBBean>();
					Statement stmt = null;
					ResultSet res = null;
					// ��ѯ��ݿ�
					//conn = new ConnDB().getConnection();
					stmt = conn.createStatement();
					res = stmt.executeQuery("exec dbo.p_jysj2014_sjzbhzcx "+nf+","+yf+",'"+qybh+"',"+zblx+";");
					// ��ݽ���ж�
					while (res.next()) {
			        	 YDZBBean ydzbbean=new YDZBBean();
			        	 ydzbbean.setXh(res.getString(1));
			        	 ydzbbean.setZblx(res.getString(2));
			        	 ydzbbean.setZbmc(res.getString(3));
			        	 ydzbbean.setByjh(res.getString(4));
			        	 ydzbbean.setBywc(res.getString(5));
			        	 ydzbbean.setJhwcl(res.getString(6));
			        	 ydzbbean.setSywc(res.getString(7));
			        	 ydzbbean.setJsyzzb(res.getString(8));
			        	 ydzbbean.setQntq(res.getString(13));
			        	 ydzbbean.setJqntqzzb(res.getString(14));
			        	 ydzbbean.setJdjh(res.getString(15));
			        	 ydzbbean.setJdlj(res.getString(16));
			        	 ydzbbean.setJdjhwcl(res.getString(17));
			        	 ydzbbean.setNdjh(res.getString(22));
			        	 ydzbbean.setNdlj(res.getString(23));
			        	 ydzbbean.setNdjhwcl(res.getString(24));
			        	 ydzbbean.setQntqlj(res.getString(25));
			        	 ydzbbean.setJqntqljzzb(res.getString(26));
			        	 YDZBList.add(ydzbbean);
			          }			        
		         return YDZBList;		
			}
			//��˾Ȩ���ж�
			public int getCompanyQx(String companyQx,int qybh,Connection conn) throws Exception{			

			    Statement stmt = null;
				ResultSet res = null;
				int result=0;
				// ��ѯ��ݿ�
				//conn = new ConnDB().getConnection();
				stmt = conn.createStatement();
				res = stmt.executeQuery("select dbo.DataAuth("+companyQx+","+qybh+") as result;");
				// ��ݽ���ж�
				if (res.next()) {
					result = res.getInt(1);
					}		
				return result;
			}	
			
			public int getCompanyQx(String companyQx,int qybh,Statement stmt) throws Exception{			

			   
				ResultSet res = null;
				int result=0;
				// ��ѯ��ݿ�
				//conn = new ConnDB().getConnection();
				
				res = stmt.executeQuery("select dbo.DataAuth("+companyQx+","+qybh+") as result;");
				// ��ݽ���ж�
				if (res.next()) {
					result = res.getInt(1);
					}		
				return result;
			}
			//��ѯ��֤��ʱЧ��
			public UserBean checkVerification(String dateNow,String verificationCode,Connection conn)throws Exception{
				Statement stmt = null;
				ResultSet res = null;
				UserBean user=new UserBean();				
				stmt = conn.createStatement();
				res = stmt.executeQuery("select userid,username from phoneUser where verificationCode = '"+verificationCode+"' and datediff(hh,verificateDate,'"+dateNow+"')<=24");
				// ��ݽ���ж�
				if (res.next()) {
					user.setUserid(res.getString(1));
					user.setUsername(res.getString(2));
					}							
				return user;
			}
			//�״ε�½�����û���Ϣ
			public boolean initialUpdateUserInfo(String phoneNum,String nPwd,String IMEI,Connection conn) throws Exception{
				boolean bool = false;
				PreparedStatement ps = null;	
				try {					
				ps = conn.prepareStatement("update phoneuser set pwd='"+nPwd+"', imei="+IMEI+" where userid = '" + phoneNum + "';");
				int i = ps.executeUpdate();
					if (i == 1) {
						bool = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return bool;			
			}
			//�û���¼IMEI��֤
			public String checkIMEI(String IMEI,Connection conn)throws Exception{
				  Statement stmt = null;
					ResultSet res = null;
					String result="";
					// ��ѯ��ݿ�
					stmt = conn.createStatement();
					res = stmt.executeQuery("select username from phoneuser where imei= '"+IMEI+"';");
					// ��ݽ���ж�
					if (res.next()) {
						result = res.getString(1);
						}							
					return result;
			}
			//��֤�ɹ���ɾ����֤��
			public boolean deleteVerificationCode(String IMEI,Connection conn)throws Exception{
				boolean flag = false;
				PreparedStatement ps = null;	
				try {					
				ps = conn.prepareStatement("update phoneuser set verificationCode='',verificateDate=null where imei = '" + IMEI + "';");
				int i = ps.executeUpdate();
					if (i == 1) {
						flag = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 				
				return flag;
			}
			//�ж���֤���Ƿ����
			public String findVerificationCode(String randomNum,Connection conn)throws Exception{
				 Statement stmt = null;
					ResultSet res = null;
					String result="";
					// ��ѯ��ݿ�
					stmt = conn.createStatement();
					res = stmt.executeQuery("select verificationCode from phoneuser where verificationCode= '"+randomNum+"';");
					// ��ݽ���ж�
					if (res.next()) {
						result = res.getString(1);
						}							
					return result;
			}
			//�����û���Ϣ
			public boolean insertUser(String username,String userid,String menuqx,String companyqx, String verificationCode,String verificationDate,Connection conn)throws Exception{
				boolean flag = false;				   
				PreparedStatement ps = null;
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date=formatter.parse(verificationDate);
				java.sql.Timestamp time=new java.sql.Timestamp(date.getTime());
				try {					
				ps = conn.prepareStatement("insert into phoneuser(username,userid,menuqx,verificationCode,verificateDate,companyqx)values('"+username+"','"+userid+"','"+menuqx+"','"+verificationCode+"','"+time+"','"+companyqx+"');");
				int i = ps.executeUpdate();
					if (i == 1) {
						flag = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 				
				return flag;
			}
}
