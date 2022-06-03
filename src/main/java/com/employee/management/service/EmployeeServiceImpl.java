package com.employee.management.service;

import java.util.List;
import java.util.Optional;

import com.employee.management.dao.AddressDao;
import com.employee.management.dao.DepartmentDao;
import com.employee.management.dao.EmployeeDao;
import com.employee.management.dto.ResponseDto;
import com.employee.management.entity.Address;
import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
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
	public ResponseDto saveEmployee(Employee emp) {
		ResponseDto responseDto = ResponseDto.builder ().build ();
		Address addr = emp.getAddress();
		addressDao.save(addr);
		employeeDao.save(emp);
		responseDto.setMessage ( "saved successfully" );
		responseDto.setStatus ( true );
		responseDto.setData (emp);
		return responseDto;
	}

	@Override
	public ResponseDto getEmpList() {
		// TODO Auto-generated method stub
		ResponseDto responseDto = ResponseDto.builder ().build ();

		List<Employee> empList = employeeDao.findAll();
		if(empList == null){
			responseDto = ResponseDto.builder ().status (false).message ( "Employee List is empty" ).build ();
			return responseDto;
		}
		responseDto.setMessage ( "retrieved successfully" );
		responseDto.setStatus ( true );
		responseDto.setData (empList);
		return responseDto;
	}
	
	
	@Override
	public ResponseDto getEmpById(Long id) {
		// TODO Auto-generated method stub
		ResponseDto responseDto = ResponseDto.builder ().build ();
		Optional<Employee> emp = employeeDao.findById(id);
		if(!emp.isPresent ()){
			responseDto = ResponseDto.builder ().status (false).message ( "Employee List is empty" ).build ();
			return responseDto;
		}
		responseDto.setMessage ( "retrieved successfully" );
		responseDto.setStatus ( true );
		responseDto.setData (emp);
		return responseDto;
	}

	@Override
	public ResponseDto saveDepartment(Department department) {
        ResponseDto responseDto = ResponseDto.builder ().build ();

        departmentDao.save ( department );
        responseDto.setMessage ( "saved successfully" );
        responseDto.setStatus ( true );
        responseDto.setData (department);

        return responseDto;
	}

	@Override
	public ResponseDto getDepartment() {
		ResponseDto responseDto = ResponseDto.builder ().build ();

		List<Department> deptList = departmentDao.findAll ();
		if(deptList == null){
			responseDto = ResponseDto.builder ().status (false).message ( "Department List is empty" ).build ();
			return responseDto;
		}
		responseDto.setMessage ( "retrieved successfully" );
		responseDto.setStatus ( true );
		responseDto.setData (deptList);
		return responseDto;
	}

	@Override
	public ResponseDto getEmpByName(String name) {
		ResponseDto responseDto = ResponseDto.builder ().build ();
		List<Employee> emp = employeeDao.findByName(name);
		if(emp ==null){
			responseDto = ResponseDto.builder ().status (false).message ( "Employee doesn't exists by nam" ).build ();
			return responseDto;
		}
		responseDto.setMessage ( "retrieved employee details by name successfully" );
		responseDto.setStatus ( true );
		responseDto.setData (emp);
		return responseDto;
	}

	@Override
	public String deleteEmployee(Long id) {
		employeeDao.deleteById ( id );
		return "deleted successfully";
	}
}

