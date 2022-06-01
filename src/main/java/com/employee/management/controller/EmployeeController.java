package com.employee.management.controller;

import java.util.List;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import com.employee.management.service.DepartmentServiceImpl;
import com.employee.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentServiceImpl departmentServiceImpl;

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @PostMapping("/department")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        logger.info ( "inside createDepartment " );
        return ResponseEntity.ok(employeeService.saveDepartment(department));
    }

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getDepartment() {
        logger.info ( "inside getDepartment " );
        List<Department> deptList = employeeService.getDepartment();
        if(deptList == null){
            return new ResponseEntity<List<Department>>( HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(employeeService.getDepartment());
    }

    @PostMapping("/employee")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        logger.info ( "inside createEmployee " );
        Department deptDetails = departmentServiceImpl.findById(employee.getDept ().getId ());
        employee.setDept ( deptDetails );
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getEmployee(){
        logger.info ( "inside getEmployee " );
        List<Employee> empList = employeeService.getEmpList();
        if(empList == null){
            return new ResponseEntity<List<Employee>>( HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(empList);

    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        logger.info ( "inside getEmployeeById ");
        Employee empList = employeeService.getEmpById(id);
        if(empList == null){
            return new ResponseEntity<Employee>( HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(empList);

    }

    @GetMapping("/employee/search/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable("name") String name){
        logger.info ( "inside getEmployeeByName " );
        List<Employee> empList = employeeService.getEmpByName(name);
        if(empList == null){
            return new ResponseEntity<List<Employee>>( HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(empList);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }


}



