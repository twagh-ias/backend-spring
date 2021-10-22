package com.prod.backend.dao;

import com.prod.backend.model.Emp;
import com.prod.backend.model.UserSkill;
import com.prod.backend.rowmapper.EmpMapper;
import com.prod.backend.rowmapper.UserSkillMapper;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserSkillRepoImpl implements UserSkillRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String get_user_skill_query="SELECT e_id, p_skills, p_proficiency_level, a_skills, a_proficiency_level" +
            " from user_skills where e_id=?";

    //select e.e_id, e.employee_name, s.p_skills, s.a_skills, s.aspired_skills from toolkit.employee e join toolkit.user_skills s on e.e_id = s.e_id;

    private static final String get_all_skills="SELECT * FROM user_skills";

    @Override
    public UserSkill getById(int e_id) {
        return jdbcTemplate.queryForObject(get_user_skill_query,
                new Object[]{e_id},new UserSkillMapper());
    }

    @Override
    public List<UserSkill> findAllSkills(){
        List<UserSkill> skills_all=jdbcTemplate.query(get_all_skills,new UserSkillMapper());;
        return skills_all;
    }

    @Override
    public boolean deleteUserSkill(long e_id) {
        String delete_query = "delete from user_skills where e_id = ?";
        return jdbcTemplate.update(delete_query, new Object[]{e_id}) > 0;
    }
}
