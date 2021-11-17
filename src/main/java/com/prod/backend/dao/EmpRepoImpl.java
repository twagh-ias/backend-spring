package com.prod.backend.dao;

import com.prod.backend.model.Emp;
import com.prod.backend.rowmapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class EmpRepoImpl implements EmpRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String get_emp_query="SELECT employee_name,email,department,org_level,team,total_exp," +
            "ad_tech_exp,slack_time,certifications from employee where e_id=?";

//    private static final String get_emp_query1="select e.email, e.employee_name, s.p_skills, s.a_skills, s.aspired_skills" +
//            " from toolkit.employee e join toolkit.user_skills s on e.e_id = s.e_id";

    private static final String get_emp_all="select * from employee";

    private static final String insert_query="insert into employee(employee_name,team,designation,role,email,org_level,projects,department,total_exp,ad_tech_exp,slack_time,certifications) values(?,?,?,?,?,?,?,?,?,?,?,?)";

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

    @Override
    public List<Emp> findAllEmp(){
        List<Emp> empall=jdbcTemplate.query(get_emp_all,new EmpMapper());;
        return empall;
    }

    @Override
    public void save(Emp emp) {
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Object[] args={emp.getEmployee_name(),emp.getTeam(),emp.getDesignation(),emp.getRole(),emp.getEmail(),emp.getOrg_level(),emp.getProjects(),emp.getDepartment(),emp.getTotal_exp(),emp.getAd_tech_exp(),emp.getSlack_time(),emp.getCertifications()};

          jdbcTemplate.update(insert_query,args,types);
    }

    @Override
    public boolean delete(long e_id) {
        String delete_query = "delete from employee where e_id = ?";
        return jdbcTemplate.update(delete_query, new Object[]{e_id}) > 0;
    }

    @Override
    public int update(Emp emp,long e_id) {
        String update_query = "update employee set employee_name = ?, email = ?,total_exp = ?,ad_tech_exp = ?," +
                "slack_time = ?,certifications = ?,team = ?, designation = ?, role = ?, org_level=?,projects=?,department=? where e_id = ?";
        Object[] params = {emp.getEmployee_name(),emp.getEmail(),emp.getTotal_exp(),emp.getAd_tech_exp(),emp.getSlack_time(),emp.getCertifications(),emp.getTeam(),emp.getDesignation(),emp.getRole(),emp.getOrg_level(),emp.getProjects(),emp.getDepartment(),e_id};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.LONGVARCHAR};
        return jdbcTemplate.update(update_query,params,types);
    }
}
