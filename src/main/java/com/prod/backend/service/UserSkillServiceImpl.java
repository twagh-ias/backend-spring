package com.prod.backend.service;

import com.prod.backend.dao.UserSkillRepo;
import com.prod.backend.model.Emp;
import com.prod.backend.model.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserSkill> findAllSkills(){
        return userSkillRepo.findAllSkills();
    }

    @Override
    public boolean deleteUserSkill(long id) {
        return userSkillRepo.deleteUserSkill(id);
    }

    public void insertUserSkill(UserSkill userSkill){
        userSkillRepo.insertUserSkill(userSkill);
    }

    public int update(UserSkill userSkill,long e_id){
        return userSkillRepo.update(userSkill,e_id);
    }
}
