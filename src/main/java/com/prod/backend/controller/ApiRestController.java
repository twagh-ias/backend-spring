package com.prod.backend.controller;
import com.prod.backend.dao.EmpRepo;
import com.prod.backend.dao.UserSkillRepo;
import com.prod.backend.model.Emp;
import com.prod.backend.model.UserSkill;
import com.prod.backend.service.EmployeeService;
import com.prod.backend.service.UserSkillService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

@RestController
@RequestMapping("/toolkit/")
@CrossOrigin("http://localhost:3000/")
public class ApiRestController{
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmpRepo empRepo;

    @Autowired
    UserSkillService userSkillService;

    @Autowired
    UserSkillRepo userSkillRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

    @GetMapping("/home")
    public List<Emp> getAll(){
        return employeeService.findAllEmp();
    }

    @GetMapping("/home2")
    public List<UserSkill> getAllSkills(){
        return userSkillService.findAllSkills();
    }

    @GetMapping("/home/{id}")
    public Emp getEmp(@PathVariable int id){
        return employeeService.getById(id);
    }

    @GetMapping("/home2/{id}")
    public UserSkill getUserSkill(@PathVariable int id){
        return userSkillService.getById(id);
    }

    @GetMapping(value = "/home3/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> getAllDetails(@PathVariable int id){
        Emp e=employeeService.getById(id);
        UserSkill u=userSkillService.getById(id);
        JSONObject jsonobj=new JSONObject();
        try {
            jsonobj.put("employee",e);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        try {
            jsonobj.put("skills",u);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<JSONObject>(jsonobj,HttpStatus.OK);
    }

    @GetMapping(value = "/home4/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObject getAllDetails1(@PathVariable int id){
        Emp e=employeeService.getById(id);
        UserSkill u=userSkillService.getById(id);
        JSONObject jsonobj=new JSONObject();
        try {
            jsonobj.put("employee",e);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        try {
            jsonobj.put("skills",u);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonobj;
    }

    @PostMapping("/addEmp")
    public void addEmployee(Emp emp){
        employeeService.save(emp);
    }

    @DeleteMapping("/deleteEmp/{id}")
    public void deleteEmp(@PathVariable int id){
        employeeService.deleteEmp(id);
//        if (id == Integer.parseInt(null)) {
//            new ResponseEntity<String>("User doesn't exist", HttpStatus.CONFLICT);
//        } else {
//            new ResponseEntity<String>("User successfully deleted", HttpStatus.OK);
//
    }

    @PutMapping("/updateEmp/{id}")
    public void updateEmp(Emp emp, @PathVariable long id){
        employeeService.update(emp,id);
    }

    @PostMapping("/addUserSkills")
    public void addUserSkills(UserSkill userSkill){
        userSkillService.insertUserSkill(userSkill);
    }

    @PutMapping("updateUserSkills/{id}")
    public void updateUserSkills(UserSkill userSkill, @PathVariable long id){
        userSkillService.update(userSkill,id);
    }

    @DeleteMapping("/deleteUserSkills/{id}")
    public void deleteUserSkill(@PathVariable int id){
        userSkillService.deleteUserSkill(id);
    }
}
