package com.coursetrackr.model;

public class Course{
    private int id; private String code; private String title;
    public Course(){} public Course(int id,String code,String title){this.id=id;this.code=code;this.title=title;}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getCode(){return code;} public void setCode(String code){this.code=code;}
    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
}
