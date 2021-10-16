package com.prod.backend.controller;

import com.prod.backend.dao.EmpRepo;
import com.prod.backend.dao.UserSkillRepo;
import com.prod.backend.model.Emp;
import com.prod.backend.model.UserSkill;
import com.prod.backend.service.EmployeeService;
import com.prod.backend.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/home/{id}")
    public Emp getEmp(@PathVariable int id){
        return employeeService.getById(id);
    }

    @GetMapping("/home2/{id}")
    public UserSkill getUserSkill(@PathVariable int id){
        return userSkillService.getById(id);
    }
}
