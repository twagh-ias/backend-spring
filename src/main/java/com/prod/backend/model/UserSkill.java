package com.prod.backend.model;

public class UserSkill {
    private String e_id;
    private String p_skills;
    private String p_proficiency_level;
    private String a_skills;
    private String a_proficiency_level;

    public String getP_skills() {
        return p_skills;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public void setP_skills(String p_skills) {
        this.p_skills = p_skills;
    }

    public String getP_proficiency_level() {
        return p_proficiency_level;
    }

    public void setP_proficiency_level(String p_proficiency_level) {
        this.p_proficiency_level = p_proficiency_level;
    }

    public String getA_skills() {
        return a_skills;
    }

    public void setA_skills(String a_skills) {
        this.a_skills = a_skills;
    }

    public String getA_proficiency_level() {
        return a_proficiency_level;
    }

    public void setA_proficiency_level(String a_proficiency_level) {
        this.a_proficiency_level = a_proficiency_level;
    }
}
