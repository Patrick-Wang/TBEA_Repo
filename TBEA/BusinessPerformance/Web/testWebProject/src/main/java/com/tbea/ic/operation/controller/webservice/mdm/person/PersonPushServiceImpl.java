package com.tbea.ic.operation.controller.webservice.mdm.person;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;

import com.tbea.ic.operation.model.entity.hr.Employee;
import com.tbea.ic.operation.reportframe.component.AbstractConfigLoader;
import com.tbea.ic.operation.service.hr.EmployeeService;
import com.tbea.mdm.ws.MDMResponse;
import com.tbea.mdm.ws.RecordStatus;

@Controller
@WebService
public class PersonPushServiceImpl implements PersonPushService {

	@Autowired
	EmployeeService employeeService;

	protected static String mdmResPath;
	static {
		try {
			mdmResPath = new URI(AbstractConfigLoader.class.getClassLoader().getResource("")
					.getPath()).getPath().substring(1)
					+ "META-INF/mdm/";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	Document respDoc;
	
	@Autowired
	public void init(){
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			respDoc = builder.parse(new File(mdmResPath + "response.xml"));
			
		}catch(Exception e){
			
		}
	}
	
	Document getDocument(){
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.newDocument();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public MDMResponse push(String batch, List<Employee> employees) {
		employeeService.saveEmployee(batch, employees);
		MDMResponse resp = new MDMResponse();
		resp.setCode("S");
		resp.setDesc("成功");
		resp.setBatchNo(batch);
		List<RecordStatus> records = new ArrayList<RecordStatus>();
		for (Employee employee : employees){
			RecordStatus rec = new RecordStatus();
			rec.setErrorText("");
			rec.setStatus("0");
			rec.setUuid(employee.getMdmDataUuid());
			records.add(rec);
		}
		resp.setRecords(records);
		return resp;
	}

	

} 