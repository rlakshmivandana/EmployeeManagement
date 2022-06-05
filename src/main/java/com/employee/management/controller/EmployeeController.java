package com.employee.management.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.employee.management.dto.ResponseDto;
import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import com.employee.management.exception.EntityNotFoundException;
import com.employee.management.service.DepartmentServiceImpl;
import com.employee.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentServiceImpl departmentServiceImpl;

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @PostMapping("/department")
    public ResponseEntity<ResponseDto> createDepartment(@RequestBody Department department) {
        logger.info ( "inside createDepartment " );
        ResponseDto responseDto =  ResponseDto.builder ().build();

        try {
            responseDto = employeeService.saveDepartment ( department );
        }catch(Exception e){
            responseDto = ResponseDto.builder ().status ( false ).message ( "error in saving department" ).build ();
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/department")
    public ResponseEntity<ResponseDto> getDepartment() {
        logger.info ( "inside getDepartment " );
        ResponseDto responseDto =  ResponseDto.builder ().build();
        try {
           responseDto = employeeService.getDepartment();
        }catch(Exception e){
             responseDto = ResponseDto.builder ().status ( false ).message ( "error in retrieving department" ).build ();
        }
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/employee")
    public ResponseEntity<ResponseDto> createEmployee(@RequestBody Employee employee) {
        logger.info ( "inside createEmployee " );
        ResponseDto responseDto =  ResponseDto.builder ().build();
        Department deptDetails = departmentServiceImpl.findById(employee.getDept ().getId ());
        employee.setDept ( deptDetails );
        try {
            responseDto = employeeService.saveEmployee(employee);
        }catch(Exception e){
            responseDto = ResponseDto.builder ().status ( false ).message ( "error in saving employee" ).build ();
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/employee")
    public ResponseEntity<ResponseDto> getEmployee(){
        logger.info ( "inside getEmployee " );
        ResponseDto responseDto =  ResponseDto.builder ().build();
        try {
            responseDto = employeeService.getEmpList();
        }catch(Exception e){
            throw new EntityNotFoundException ( "list", "list" );
        }
        return ResponseEntity.ok(responseDto);

    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<ResponseDto> getEmployeeById(@PathVariable("id") Long id){
        logger.info ( "inside getEmployeeById ");
        ResponseDto responseDto =  ResponseDto.builder ().build();
        try {
            responseDto = employeeService.getEmpById(id);
        }catch(Exception e){
            throw new EntityNotFoundException ( "id", id );
        }
        return ResponseEntity.ok(responseDto);

    }

    @GetMapping("/employee/search/{name}")
    public ResponseEntity<ResponseDto> getEmployeeByName(@PathVariable("name") String name){
        logger.info ( "inside getEmployeeByName ");
        ResponseDto responseDto =  ResponseDto.builder ().build();
        try {
            responseDto = employeeService.getEmpByName(name);
        }catch(Exception e){
            throw new EntityNotFoundException ( "name", name );
        }
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        logger.info ( "inside delete method ");
        ResponseDto responseDto =  ResponseDto.builder ().build();
        try {
            employeeService.deleteEmployee(id);
        }catch(Exception e){
           throw new EntityNotFoundException ( "id", id );
        }
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }


}



