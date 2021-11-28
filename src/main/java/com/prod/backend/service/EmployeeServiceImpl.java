package com.prod.backend.service;


import com.prod.backend.dao.EmpRepo;
import com.prod.backend.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Emp> findAllEmp(){
        return empRepo.findAllEmp();
    }

    @Override
    public void save(Emp emp) {
         empRepo.save(emp);
    }

    public void deleteEmp(long id){
       empRepo.delete(id);
    }

    public void update(Emp emp,long e_id){
        empRepo.update(emp, e_id);
    }

}
