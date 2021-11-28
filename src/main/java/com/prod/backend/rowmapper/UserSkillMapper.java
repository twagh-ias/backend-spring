package com.prod.backend.rowmapper;

import com.prod.backend.model.UserSkill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSkillMapper implements RowMapper<UserSkill> {
    @Override
    public UserSkill mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserSkill u = new UserSkill();
        u.setE_id(rs.getString("e_id"));
        u.setEmployee_name(rs.getString("employee_name"));
        u.setP_skills(rs.getString("p_skills"));
        u.setP_proficiency_level(rs.getString("p_proficiency_level"));
        u.setP_self_rating(rs.getString("p_self_rating"));
        u.setP_manager_rating(rs.getString("p_manager_rating"));
        u.setP_rating_delta(rs.getString("p_rating_delta"));
        u.setA_skills(rs.getString("a_skills"));
        u.setA_self_rating(rs.getString("a_self_rating"));
        u.setA_proficiency_level(rs.getString("a_proficiency_level"));
        u.setComments(rs.getString("comments"));
        return u;
    }
}
