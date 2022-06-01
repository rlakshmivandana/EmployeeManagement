package com.employee.management.service;

import com.employee.management.dao.DepartmentDao;
import com.employee.management.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public Department findById(Long id) {
        return departmentDao.findById(id).get ();
    }
}
