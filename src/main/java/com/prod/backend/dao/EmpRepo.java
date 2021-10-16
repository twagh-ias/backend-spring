package com.prod.backend.dao;

import com.prod.backend.model.Emp;

public interface EmpRepo {
    Emp getById(int e_id);
}
