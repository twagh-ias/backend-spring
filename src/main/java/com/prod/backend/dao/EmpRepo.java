package com.prod.backend.dao;

import com.prod.backend.model.Emp;

import java.util.List;

public interface EmpRepo {
    Emp getById(int e_id);

    public List<Emp> findAllEmp();

    public void save(Emp emp);

    public void delete(long id);

    public int update(Emp emp,long e_id);

    String validate(String required_skill, int min_req_rating, int complexity);
}
