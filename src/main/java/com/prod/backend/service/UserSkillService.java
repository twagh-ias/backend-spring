package com.prod.backend.service;


import com.prod.backend.model.Emp;
import com.prod.backend.model.UserSkill;

import java.util.List;

public interface UserSkillService {
    UserSkill getById(int e_id);

    public List<UserSkill> findAllSkills();
}
