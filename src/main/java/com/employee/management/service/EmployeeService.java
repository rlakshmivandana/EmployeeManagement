package com.employee.management.service;

import java.util.List;

import com.employee.management.dto.ResponseDto;
import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;

public interface EmployeeService {

	ResponseDto saveEmployee(Employee emp);

	ResponseDto getEmpList();

	ResponseDto getEmpById(Long id);

	ResponseDto saveDepartment(Department department);

	ResponseDto getDepartment();

	ResponseDto getEmpByName(String name);

	String deleteEmployee(Long id);
}
