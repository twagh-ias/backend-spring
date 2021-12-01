package com.prod.backend.dao;

import com.prod.backend.model.Emp;
import com.prod.backend.rowmapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class EmpRepoImpl implements EmpRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String cert;

    private static final String get_emp_query="SELECT employee_name,email,department,org_level,team,total_exp," +
            "ad_tech_exp,slack_time,certifications,role,projects from employee where e_id=?";

    private static final String get_emp_all="select * from employee";

    private static final String insert_query="insert into employee(employee_name,team,designation,role,email,org_level,projects,department,total_exp,ad_tech_exp,slack_time,certifications) values(?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String update_query = "update employee set employee_name = ?, email = ?,total_exp = ?,ad_tech_exp = ?," +
            "slack_time = ?,certifications = ?,team = ?, designation = ?, role = ?, org_level = ?, projects = ?,department = ? where e_id = ?";

    private static final String delete_skill_query="delete from user_skills where e_id=?";
    private static final String delete_emp_query = "delete from employee where e_id = ?";

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
                    e.setCertifications(Collections.singletonList(rs.getString("certifications")));
                    //e.setCertifications((ArrayList<String>) rs.getArray("certifications"));
                    e.setRole(rs.getString("role"));
                    e.setProjects(rs.getString("projects"));
                    return e;
                });
    }

    @Override
    public List<Emp> findAllEmp(){
        List<Emp> empall=jdbcTemplate.query(get_emp_all,new EmpMapper());;
        return empall;
    }

    @Override
    public void save(Emp emp) {
        String delim = ",";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < emp.getCertifications().size() - 1)
        {
            sb.append(emp.getCertifications().get(i));
            sb.append(delim);
            i++;
        }
        sb.append(emp.getCertifications().get(i));
        cert = sb.toString();
        System.out.println(cert);


        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Object[] args={emp.getEmployee_name(),emp.getTeam(),emp.getDesignation(),emp.getRole(),emp.getEmail(),emp.getOrg_level(),emp.getProjects(),emp.getDepartment(),emp.getTotal_exp(),emp.getAd_tech_exp(),emp.getSlack_time(),cert};
        String insert_query_skills="insert into user_skills(employee_name) values("+"'"+emp.getEmployee_name()+"'"+")";
          jdbcTemplate.update(insert_query,args,types);
          jdbcTemplate.update(insert_query_skills);



    }

    @Override
    public void delete(long e_id) {

        boolean b = jdbcTemplate.update(delete_skill_query, new Object[]{e_id}) > 0;
        boolean c= jdbcTemplate.update(delete_emp_query, new Object[]{e_id}) > 0;
        if(b= Boolean.parseBoolean(null))
        {
            new ResponseEntity<String>("User doesn't exist", HttpStatus.OK);
        }
        else
        {
            new ResponseEntity<String>("User successfully deleted", HttpStatus.CONFLICT);
        }

    }

    @Override
    public int update(Emp emp,long e_id) {
        String delim = ",";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < emp.getCertifications().size() - 1)
        {
            sb.append(emp.getCertifications().get(i));
            sb.append(delim);
            i++;
        }
        sb.append(emp.getCertifications().get(i));
        cert = sb.toString();
        System.out.println(cert);

        Object[] params = {emp.getEmployee_name(),emp.getEmail(),emp.getTotal_exp(),emp.getAd_tech_exp(),emp.getSlack_time(),cert,emp.getTeam(),emp.getDesignation(),emp.getRole(),emp.getOrg_level(),emp.getProjects(),emp.getDepartment(),e_id};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.LONGVARCHAR};
        return jdbcTemplate.update(update_query,params,types);
    }
}
