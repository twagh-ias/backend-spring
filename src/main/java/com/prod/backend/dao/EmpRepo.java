package com.prod.backend.dao;

import com.prod.backend.model.Emp;

import java.util.List;

public interface EmpRepo {
    Emp getById(int e_id);

    public List<Emp> findAllEmp();
}
