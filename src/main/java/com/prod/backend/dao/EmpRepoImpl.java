package com.prod.backend.dao;

import com.prod.backend.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmpRepoImpl implements EmpRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String get_emp_query="SELECT employee_name,email,department,org_level,team,total_exp," +
            "ad_tech_exp,slack_time,certifications from employee where e_id=?";

//    private static final String get_emp_query1="select e.email, e.employee_name, s.p_skills, s.a_skills, s.aspired_skills" +
//            " from toolkit.employee e join toolkit.user_skills s on e.e_id = s.e_id";

    @Override
    public Emp getById(int e_id) {
        return jdbcTemplate.queryForObject(get_emp_query,
                new Object[]{e_id},
                (rs, rowNum) -> {
                    Emp e = new Emp();
                    e.setEmployee_name(rs.getString("employee_name"));
                    e.setEmail(rs.getString("email"));
                    e.setDepartment(rs.getString("department"));
                    e.setOrg_level(rs.getString("org_level"));
                    e.setTeam(rs.getString("team"));
                    e.setTotal_exp(rs.getString("total_exp"));
                    e.setAd_tech_exp(rs.getString("ad_tech_exp"));
                    e.setSlack_time(rs.getString("slack_time"));
                    e.setCertifications(rs.getString("certifications"));

                    return e;
                });
    }

}
