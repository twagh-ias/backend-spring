package com.prod.backend.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class Emp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  String e_id;

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    @NotBlank(message = "Name cannot be blank!")
    private String employee_name;

    private String department;

    private String org_level;

    private String certifications;

    private String projects;

    private String total_exp;

    private String ad_tech_exp;

    private String slack_time;

    private String team;

    @NotBlank(message = "Email cannot be blank!")
    private String email;

//    @JoinTable(name="user_skills",
//    joinColumns = @JoinColumn(name="e_id"),
//    inverseJoinColumns = @JoinColumn(name="e_id"))
//    private Set<UserSkill> skills=new HashSet<>();
//
//    public Set<UserSkill> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(Set<UserSkill> skills) {
//        this.skills = skills;
//    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrg_level() {
        return org_level;
    }

    public void setOrg_level(String org_level) {
        this.org_level = org_level;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getTotal_exp() {
        return total_exp;
    }

    public void setTotal_exp(String total_exp) {
        this.total_exp = total_exp;
    }

    public String getAd_tech_exp() {
        return ad_tech_exp;
    }

    public void setAd_tech_exp(String  ad_tech_exp) {
        this.ad_tech_exp = ad_tech_exp;
    }

    public String getSlack_time() {
        return slack_time;
    }

    public void setSlack_time(String slack_time) {
        this.slack_time = slack_time;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
