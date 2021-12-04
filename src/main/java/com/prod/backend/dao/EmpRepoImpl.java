package com.prod.backend.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prod.backend.model.Emp;
import com.prod.backend.rowmapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;

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
                    //e.setCertifications(Collections.singletonList(rs.getString("certifications")));
                    //e.setCertifications((ArrayList<String>) rs.getArray("certifications"));
                    e.setRole(rs.getString("role"));
                    e.setProjects(rs.getString("projects"));
                    return e;
                });
    }

    @Override
    public List<Emp> findAllEmp(){
        List<Emp> empall=jdbcTemplate.query(get_emp_all,new EmpMapper());
        return empall;
    }

    @Override
    public void save(Emp emp) {
//        String delim = ",";
//        StringBuilder sb = new StringBuilder();
//        int i = 0;
//        while (i < emp.getCertifications().size() - 1)
//        {
//            sb.append(emp.getCertifications().get(i));
//            sb.append(delim);
//            i++;
//        }
//        sb.append(emp.getCertifications().get(i));
//        cert = sb.toString();
//        System.out.println(cert);


        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Object[] args={emp.getEmployee_name(),emp.getTeam(),emp.getDesignation(),emp.getRole(),emp.getEmail(),emp.getOrg_level(),emp.getProjects(),emp.getDepartment(),emp.getTotal_exp(),emp.getAd_tech_exp(),emp.getSlack_time(),emp.getCertifications()};
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
//        String delim = ",";
//        StringBuilder sb = new StringBuilder();
//        int i = 0;
//        while (i < emp.getCertifications().size() - 1)
//        {
//            sb.append(emp.getCertifications().get(i));
//            sb.append(delim);
//            i++;
//        }
//        sb.append(emp.getCertifications().get(i));
//        cert = sb.toString();
//        System.out.println(cert);

        Object[] params = {emp.getEmployee_name(),emp.getEmail(),emp.getTotal_exp(),emp.getAd_tech_exp(),emp.getSlack_time(),emp.getCertifications(),emp.getTeam(),emp.getDesignation(),emp.getRole(),emp.getOrg_level(),emp.getProjects(),emp.getDepartment(),e_id};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.LONGVARCHAR};
        return jdbcTemplate.update(update_query,params,types);
    }

//    @Override
//    public HashMap<Integer, String> validate(String required_skill, int min_req_rating, int complexity) {
//        String validate_query = "select e.e_id,e.employee_name, e.slack_time,u.p_skills,u.a_skills,u.a_self_rating,u.p_manager_rating" +
//                " from employee as e,user_skills as u where e.e_id=u.e_id and (u.p_skills=? or u.a_skills=?) " +
//                "and (u.a_self_rating>=? or u.p_manager_rating>=?) " +
//                "and (e.slack_time>=?)";
//        Object[] params = {required_skill,required_skill, min_req_rating,min_req_rating, complexity};
//        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER, Types.INTEGER, Types.INTEGER};
//        HashMap<Integer,String> hashMap=jdbcTemplate.query(validate_query, params, types, (ResultSet rs) -> {
//            HashMap<Integer, String> hmap = new HashMap<>();
//            while (rs.next()) {
//                String details = rs.getInt("e_id") + "," + rs.getString("employee_name")+ ","+rs.getString("slack_time")+","+rs.getString("p_skills")+","+rs.getString("a_skills")+","+rs.getString("a_self_rating")+","+rs.getString("p_manager_rating");
////                String details = rs.getInt("e_id") + "," + rs.getString("employee_name")+ ","+rs.getString("slack_time")+","+rs.getString("p_skills")+","+rs.getString("a_skills")+","+rs.getString("a_self_rating")+","+rs.getString("p_manager_rating");
//                hmap.put(rs.getInt("e_id"), details);
//            }
//            return hmap;
//        });

        @Override
        public String validate(String required_skill, int min_req_rating, int complexity) {
//            String validate_query = "select e.e_id,e.employee_name, e.slack_time,u.p_skills,u.a_skills,u.a_self_rating,u.p_manager_rating" +
//                    " from employee as e,user_skills as u where e.e_id=u.e_id and (u.p_skills=? or u.a_skills=?) " +
//                    "and (u.a_self_rating>=? or u.p_manager_rating>=?) " +
//                    "and (e.slack_time>=?)";

            String validate_query2="select e.e_id,e.employee_name, e.slack_time,u.p_skills,u.a_skills,u.a_self_rating,u.p_manager_rating " +
                    "from employee as e,user_skills as u " +
                    "where e.e_id=u.e_id and (u.p_skills=? or u.a_skills=?) " +
                    "and (u.a_self_rating >= ? and u.p_manager_rating>=?) " +
                    "and (e.slack_time>=?)";
            Object[] params = {required_skill,required_skill, min_req_rating,min_req_rating, complexity};
            int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER, Types.INTEGER, Types.INTEGER};
            ArrayList<String> arrlist=jdbcTemplate.query(validate_query2, params, types, (ResultSet rs) -> {
                ArrayList<String> hmap = new ArrayList<>();
                while (rs.next()) {
//                    String details = rs.getInt("e_id") + "," + rs.getString("employee_name")+ ","+rs.getString("slack_time")+","+rs.getString("p_skills")+","+rs.getString("a_skills")+","+rs.getString("a_self_rating")+","+rs.getString("p_manager_rating");
                    String details = rs.getString("employee_name")+ ","+rs.getString("slack_time")+","+rs.getString("p_skills")+","+rs.getString("a_skills")+","+rs.getString("a_self_rating")+","+rs.getString("p_manager_rating");
                    hmap.add(details);
                }
                return hmap;
            });
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            String JSONObject1 = gson.toJson(arrlist);
            System.out.println(JSONObject1);
            return JSONObject1;
//            return arrlist;
    }
}
