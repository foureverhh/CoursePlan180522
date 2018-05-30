package com.testApi.Course.Plan2.model;

import java.io.Serializable;
import java.util.List;

public class Course implements  Serializable {

    private String id;
    private String name;
    private String time;
    private String teachers;
    private String classroom;
    private List<Student> students;

    public Course(String id, String name, String time, String teachers, String classroom,List<Student> students) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.teachers = teachers;
        this.classroom = classroom;
        this.students = students;
    }

    public Course() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

   public void setStudents(List<Student> students) {
        this.students = students;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getTeachers() {
        return teachers;
    }

    public String getClassroom() {
        return classroom;
    }

   public List<Student> getStudents() {
        return students;
    }


}
