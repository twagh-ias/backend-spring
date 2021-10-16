package com.prod.backend.service;

import com.prod.backend.model.Emp;

import java.util.List;

public interface EmployeeService {
    Emp getById(int e_id);

    public List<Emp> findAllEmp();
}
