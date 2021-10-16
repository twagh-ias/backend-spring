package com.prod.backend.service;

import com.prod.backend.dao.UserSkillRepo;
import com.prod.backend.model.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    @Autowired
    UserSkillService userSkillService;

    @Autowired
    UserSkillRepo userSkillRepo;

    @Override
    public UserSkill getById(int e_id)
    {
        return userSkillRepo.getById(e_id);
    }
}
