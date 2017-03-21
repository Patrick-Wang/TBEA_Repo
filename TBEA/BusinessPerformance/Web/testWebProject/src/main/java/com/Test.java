package com;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.tbea.ic.operation.service.report.HBWebService;

import net.sf.json.JSONArray;

public class Test {

	public static void main(String[] args) throws Exception {
		
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance(); 
        org.apache.cxf.endpoint.Client client = dcf.createClient("http://172.28.8.147:8080/service/LBEBusiness?wsdl"); 
                //sayHello 为接口中定义的方法名称   张三为传递的参数   返回一个Object数组 
        Object[] objects=client.invoke("sayHello", "张三");    
        //输出调用结果 
        System.out.println(objects[0].toString()); 
		
		HBWebService hbws = new HBWebService();
		
		List<String> list = new ArrayList<String>();
		list.add("company_name");
		list.add("issue_happen_date");
		list.add("product_type");
		list.add("production_num");
		list.add("production_model");
		list.add("issue_type");
		list.add("sub_issue_type");
		list.add("category_code");
		list.add("material_quality_phenomenon");
		list.add("detail");
		list.add("material_happen_phase");
		list.add("material_count");
		list.add("measurement_units");
		list.add("suppier_id");
		list.add("suppier");
		list.add("issue_process");
		list.add("responsibility_department");
		list.add("material_treatment_measure");
		list.add("onsite_treatmen_measure");
		list.add("onsite_treatment_result");
		list.add("causa_analysis");
		list.add("assessment");
		list.add("filling_personnel");
		
		List<Object[]> result = hbws.getHBNbzlqk(list);
//		System.out.println("getHBSjzb++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		List<List<Object>> result = hbws.getHBSjzb(new ArrayList<String>(), Date.valueOf("2016-9-1"));
		for (Object[] r: result){
			System.out.println(JSONArray.fromObject(r).toString());
		}
//		System.out.println("getHBCpqy++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		result = hbws.getHBCpqy(new ArrayList<String>(), Date.valueOf("2016-9-1"));
//		for (List<Object> r: result){
//			System.out.println(JSONArray.fromObject(r).toString());
//		}
//		System.out.println("getHBScqy++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		result = hbws.getHBScqy(new ArrayList<String>(), Date.valueOf("2016-9-1"));
//		for (List<Object> r: result){
//			System.out.println(JSONArray.fromObject(r).toString());
//		}
//		System.out.println("getHBClwcqk++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		result = hbws.getHBClwcqk(new ArrayList<String>(), Date.valueOf("2016-9-1"));
//		for (List<Object> r: result){
//			System.out.println(JSONArray.fromObject(r).toString());
//		}
//		Connection conn = null;
//		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();//加入oracle的驱动，“”里面是驱动的路径
//		   
//		  String url = "jdbc:oracle:thin:@172.28.11.11:1521:ORCL2";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称
//
//		  String UserName = "IUFO_ZB_DL";// 数据库用户登陆名 ( 也有说是 schema 名字的 )
//
//		  String Password = "IUFO_ZB_DL";// 密码
//
//		  conn = DriverManager.getConnection(url, UserName, Password);
		
		
//		EasyCalendar ec = new EasyCalendar();
//		ec.setYear(2000);
//		ec.setMonth(12);
//		ec.setDay(31);
//		
//		for (int i = 0; i < 20; ++i){
//			System.out.println(ec.getFormat());
//			System.out.println(ec.getWeek());
//			ec = ec.getNextYear();
//		}
//		ec.setYear(2016);
//		ec.setMonth(1);
//		ec.setDay(2);
//		System.out.println(ec.getFormat());
//		System.out.println(ec.getWeek());
//		ec.setYear(2015);
//		ec.setMonth(12);
//		ec.setDay(30);
//		System.out.println(ec.getFormat());
//		System.out.println(ec.getWeek());
//		Path dir = Paths.get("D:\\新建文本文档.txt");
//		
//		WatchService watcher = FileSystems.getDefault().newWatchService();
//		dir.register(watcher, ENTRY_MODIFY);
//		   WatchKey key = watcher.take();
//          List ev = key.pollEvents(); 
//           Path dir = keys.get(key);
//		Iterator<Pair<Kind, String>> it = new WatchDir(dir, true);
//		while (it.hasNext()){
//			System.out.println(it.next());
//		}
//		ELParser elp = new ELParser(new ObjectLoader(){
//
//			@Override
//			public Object onGetObject(String key) {
//				// TODO Auto-generated method stub
//				if (key.equals("usk")){
//					return new abc[]{new abc(), new abc(), new abc()};
//				}
//				if (key.equals("kk")){
//					return new abc[]{new abc(), new abc(), new abc()};
//				}
//				
//				if (key.equals("ff")){
//					return new abc[]{new abc(), new abc(), new abc()};
//				}
//				
//				return null;
//			}
//
//			@Override
//			public boolean hasObject(String key) {
//				// TODO Auto-generated method stub
//				return true;
//			}
//			
//		});
//		List<ELExpression> els = elp.parser("${usk[kk[1].bac[1]].bac[0].test['152'].asdfasdf.packAsList}");
//		Object val = els.get(0).value();
//		System.out.println(val);
	}

}
