package com.prod.backend.dao;
import com.prod.backend.model.UserSkill;
import com.prod.backend.rowmapper.UserSkillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class UserSkillRepoImpl implements UserSkillRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String get_user_skill_query="SELECT e_id, employee_name, p_skills, p_proficiency_level, p_self_rating, p_manager_rating, p_rating_delta, a_skills," +
            " a_self_rating, a_proficiency_level,comments from user_skills where e_id=?";

    private static final String get_all_skills="SELECT * FROM user_skills";

    private static final String update_skill_query="UPDATE user_skills set p_skills=?, a_skills=?, aspired_skills=?, p_self_rating=?, a_self_rating=?, comments=? where e_id=?";

    private static final String insert_new_userSkill="INSERT INTO user_skills(p_skills,a_skills,aspired_skills,p_self_rating,a_self_rating,comments) values(?,?,?,?,?,?)";

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

    public void insertUserSkill(UserSkill userSkill){
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR};
        Object[] args={userSkill.getP_skills(),userSkill.getA_skills(),userSkill.getAspired_skills(),userSkill.getP_self_rating(),userSkill.getA_self_rating()};
        jdbcTemplate.update(insert_new_userSkill,args,types);
    }

    @Override
    public int update(UserSkill userSkill, long e_id) {
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.LONGVARCHAR};
        Object[] args={userSkill.getP_skills(),userSkill.getA_skills(),userSkill.getAspired_skills(),userSkill.getP_self_rating(),userSkill.getA_self_rating(),userSkill.getComments(),e_id};
        return jdbcTemplate.update(update_skill_query,args,types);
    }
}