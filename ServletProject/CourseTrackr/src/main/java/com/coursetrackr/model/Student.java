package com.coursetrackr.model;

public class Student{
    private int id; private String name; private String registrationNumber; private String username; private String email;
    public Student(){}
    public Student(int id,String name,String reg,String username,String email){this.id=id;this.name=name;this.registrationNumber=reg;this.username=username;this.email=email;}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getRegistrationNumber(){return registrationNumber;} public void setRegistrationNumber(String r){this.registrationNumber=r;}
    public String getUsername(){return username;} public void setUsername(String u){this.username=u;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
}
