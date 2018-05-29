package com.tbea.ic.operation.service.hr.employee;

import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.hr.employee.EmployeeDao;
import com.tbea.ic.operation.model.entity.hr.Employee;

@Service
@Transactional("transactionManager")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public boolean saveEmployee(String batch, List<Employee> employees) {
		Long updateTime = Calendar.getInstance().getTimeInMillis();
		for (int i = 0; i < employees.size(); ++i){
			employees.get(i).setUpdateTime(updateTime);
			employees.get(i).setMdmBatch(batch);
			Employee employee = employeeDao.getByCode(employees.get(i).getWorkNum());
			if(employee != null){
				employees.get(i).setId(employee.getId());
			}
            LoggerFactory.getLogger("WEBSERVICE").info("MDM push merge " + JSONArray.fromObject(employees.get(i)).toString());

            employeeDao.merge(employees.get(i));
		}
		return true;
	}

}
