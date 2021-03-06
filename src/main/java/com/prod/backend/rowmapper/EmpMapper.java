package com.prod.backend.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.prod.backend.model.Emp;
import org.springframework.jdbc.core.RowMapper;

public class EmpMapper implements RowMapper<Emp> {

    @Override
    public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
        Emp e = new Emp();
        e.setE_id(rs.getString("e_id"));
        e.setEmployee_name(rs.getString("employee_name"));
        e.setEmail(rs.getString("email"));
        e.setDepartment(rs.getString("department"));
        e.setOrg_level(rs.getString("org_level"));
        e.setTeam(rs.getString("team"));
        e.setTotal_exp(rs.getString("total_exp"));
        e.setAd_tech_exp(rs.getString("ad_tech_exp"));
        e.setSlack_time(rs.getString("slack_time"));
        //e.setCertifications((ArrayList<String>) rs.getArray("certifications"));
        //e.setCertifications((ArrayList<String>) rs.getArray("certifications"));
        e.setRole(rs.getString("role"));
        e.setProjects(rs.getString("projects"));
        return e;
    }

}