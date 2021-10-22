package com.prod.backend.dao;


import com.prod.backend.model.UserSkill;

import java.util.List;

public interface UserSkillRepo {
    UserSkill getById(int e_id);

    public List<UserSkill> findAllSkills();

    public boolean deleteUserSkill(long id);
}
