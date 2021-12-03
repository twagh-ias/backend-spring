package com.prod.backend.service;

import com.prod.backend.model.Emp;

import java.util.HashMap;
import java.util.List;

public interface EmployeeService {
    Emp getById(int e_id);

    public List<Emp> findAllEmp();

    public void save(Emp emp);

    public void deleteEmp(long id);

    public void update(Emp emp,long e_id);

    HashMap<Integer, String> validate(String required_skill, int min_req_rating, int complexity);
}
