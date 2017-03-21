package com.tbea.ic.operation.service.hr;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.hr.EmployeeDao;
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
			Employee employee = employeeDao.getByCode(employees.get(i).getCode());
			if(employee != null){
				employees.get(i).setId(employee.getId());
			}
			employeeDao.merge(employees.get(i));
		}
		return true;
	}

}
