package com.prod.backend.service;


import com.prod.backend.dao.EmpRepo;
import com.prod.backend.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmpRepo empRepo;

    @Override
    public Emp getById(int e_id)
    {
        return empRepo.getById(e_id);
    }

}
