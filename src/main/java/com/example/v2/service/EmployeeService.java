package com.example.v2.service;

import com.example.v2.model.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployee();
    public Employee getEmployeById(long id);
}
