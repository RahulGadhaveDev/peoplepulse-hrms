package com.hrms.service;

import com.hrms.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department postDepartment(Department department);

    List<Department> departmentList();

    Department departmentById(int id);

    String departmentDeleteById(int id);

    Department updateDepartment(Department department ,int id);

    List<Department> search(String name);
}

