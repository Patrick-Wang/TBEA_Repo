package com.tbea.ic.operation.model.dao.hr.employee;

import com.tbea.ic.operation.model.entity.hr.Employee;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

public interface EmployeeDao  extends AbstractReadWriteDao<Employee>{

	Employee getByCode(String code);

}
