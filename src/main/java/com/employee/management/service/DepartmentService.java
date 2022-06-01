package com.employee.management.service;

import com.employee.management.entity.Department;
import org.springframework.stereotype.Service;

public interface DepartmentService {

    Department findById(Long id);
}
