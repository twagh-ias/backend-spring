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
        u.setP_skills(rs.getString("p_skills"));
        u.setP_proficiency_level(rs.getString("p_proficiency_level"));
        u.setA_skills(rs.getString("a_skills"));
        u.setA_proficiency_level(rs.getString("a_proficiency_level"));
        return u;
    }
}
