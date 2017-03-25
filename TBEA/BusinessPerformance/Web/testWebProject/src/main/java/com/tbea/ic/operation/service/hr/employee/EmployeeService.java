package com.tbea.ic.operation.service.hr.employee;

import java.util.List;

import com.tbea.ic.operation.model.entity.hr.Employee;

public interface EmployeeService {

	public boolean saveEmployee(String batch, List<Employee> employee);

}
