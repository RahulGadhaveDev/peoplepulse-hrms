package com.hrms.controller;

import com.hrms.entity.Department;
import com.hrms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService ;

    @PostMapping("/post")
    public ResponseEntity<Department> postDepartment(@RequestBody Department department) {
        Department postDepartment = departmentService.postDepartment(department);
        return new ResponseEntity<>(postDepartment , HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> departmentList() {
        List<Department> departmentList = departmentService.departmentList();
        return new ResponseEntity<>(departmentList , HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Department> departmentById(@PathVariable int id) {
        Department departmentById = departmentService.departmentById(id);
        return new ResponseEntity<>(departmentById , HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department ,@PathVariable int id){
        Department updatedDepartment = departmentService.updateDepartment(department,id);
        return new ResponseEntity<>(updatedDepartment , HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> departmentDeleteById(@PathVariable int id) {
        String msg = departmentService.departmentDeleteById(id);
        return new ResponseEntity<>(msg , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Department>> search(@RequestParam String name){
        List<Department> search = departmentService.search(name);
        return new ResponseEntity<>(search , HttpStatus.OK);
    }
}
