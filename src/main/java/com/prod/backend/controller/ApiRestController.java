package com.prod.backend.controller;

import com.prod.backend.dao.EmpRepo;
import com.prod.backend.dao.UserSkillRepo;
import com.prod.backend.model.Emp;
import com.prod.backend.model.UserSkill;
import com.prod.backend.service.EmployeeService;
import com.prod.backend.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toolkit/")
public class ApiRestController {
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

    @PostMapping("/addEmp")
    public void addEmployee(Emp emp){
        employeeService.save(emp);
    }

    @DeleteMapping("/deleteEmp/{id}")
    public void deleteEmp(@PathVariable int id){
        employeeService.deleteEmp(id);
    }

    @DeleteMapping("/deleteUserSkills/{id}")
    public void deleteUserSkill(@PathVariable int id){
        userSkillService.deleteUserSkill(id);
    }

    @PutMapping("/updateEmp/{id}")
    public void updateEmp(Emp emp, @PathVariable long id){
        employeeService.update(emp,id);
    }
}
