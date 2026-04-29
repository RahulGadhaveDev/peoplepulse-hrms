package com.hrms.serviceImpl;

import com.hrms.entity.Department;
import com.hrms.repository.DepartmentRepository;
import com.hrms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository ;

    @Override
    public Department postDepartment(Department department) {
        Department postDepartment = departmentRepository.save(department);
        return postDepartment;
    }

    @Override
    public List<Department> departmentList() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
    }

    @Override
    public Department departmentById(int id) {
        Department departmentById = departmentRepository.findById(id).orElseThrow(()->new RuntimeException("department not found"+id));
        return departmentById;
    }

    @Override
    public Department updateDepartment(Department department ,int id) {
        Department exciting = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department Not found"+id));
        exciting.setDepartmentName(department.getDepartmentName());

        Department updatedDepartment = departmentRepository.save(exciting);
        return updatedDepartment;
    }

    @Override
    public List<Department> search(String name) {
        List<Department> departmentList = departmentRepository.findAll();
        List<Department> departments = departmentList.stream().filter(dept -> dept.getDepartmentName() != null && dept.getDepartmentName().equalsIgnoreCase(name)).toList();
        return departments;
    }


    @Override
    public String departmentDeleteById(int id) {
        departmentRepository.deleteById(id);
        return "department delete successfully";
    }


}
