package com.employee.management.service;

import java.util.List;

import com.employee.management.dto.ResponseDto;
import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;

public interface EmployeeService {
	
	String saveEmployee(Employee emp);

	List<Employee> getEmpList();

	Employee getEmpById(Long id);

	ResponseDto saveDepartment(Department department);

	List<Department> getDepartment();

	List<Employee> getEmpByName(String name);

	String deleteEmployee(Long id);
}
