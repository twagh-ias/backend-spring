package com.prod.backend.service;

import com.prod.backend.model.Emp;

import java.util.List;

public interface EmployeeService {
    Emp getById(int e_id);

    public List<Emp> findAllEmp();

    public void save(Emp emp);

    public boolean deleteEmp(long id);

    public void update(Emp emp,long e_id);
}
