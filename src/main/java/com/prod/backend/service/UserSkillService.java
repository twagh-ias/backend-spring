package com.prod.backend.service;


import com.prod.backend.model.Emp;
import com.prod.backend.model.UserSkill;

import java.util.List;

public interface UserSkillService {

    UserSkill getById(int e_id);

    List<UserSkill> findAllSkills();

    boolean deleteUserSkill(long id);

    void insertUserSkill(UserSkill userSkill);

    int update(UserSkill userSkill,long e_id);
}
