package com.employee.management.dao;

import com.employee.management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Long>{

}
