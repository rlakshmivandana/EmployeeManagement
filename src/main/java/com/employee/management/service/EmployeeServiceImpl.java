package com.employee.management.service;

import java.util.List;
import java.util.Optional;

import com.employee.management.dao.AddressDao;
import com.employee.management.dao.DepartmentDao;
import com.employee.management.dao.EmployeeDao;
import com.employee.management.entity.Address;
import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import com.employee.management.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	AddressDao addressDao;

	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public String saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		Address addr = emp.getAddress();
		addressDao.save(addr);
		employeeDao.save(emp);
		return "saved successfully";
	}

	@Override
	public List<Employee> getEmpList() {
		// TODO Auto-generated method stub
		List<Employee> empList = employeeDao.findAll();
		return empList;
	}
	
	
	@Override
	public Employee getEmpById(Long id) {
		// TODO Auto-generated method stub
		return employeeDao.findById(id).orElseThrow (()-> new EntityNotFoundException ( "Employee", id ) );
	}

	@Override
	public String saveDepartment(Department department) {
		departmentDao.save ( department );
		return "saved successfully";
	}

	@Override
	public List<Department> getDepartment() {
		return departmentDao.findAll ();
	}

	@Override
	public List<Employee> getEmpByName(String name) {
		return employeeDao.findByName(name);
	}

	@Override
	public String deleteEmployee(Long id) {
		employeeDao.deleteById ( id );
		return "deleted successfully";
	}
}

