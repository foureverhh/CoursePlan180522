package com.testApi.Course.Plan2.controller;

import com.testApi.Course.Plan2.service.StudentService;
import com.testApi.Course.Plan2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService = new StudentService();


    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public List<Student> getStudents(){
        return studentService.getStudents();
        /*return Arrays.asList(
                new Student("s1","student 1","student1@gmail.com"),
                new Student("s2","student 2","student2@gmail.com"),
                new Student("s3","student 3","student3@gmail.com"),
                new Student("s4","student 4","student4@gmail.com")
        );*/
    }

    @RequestMapping(value = "/students/{id}",method = RequestMethod.GET)
    public Student findStudentById(@PathVariable String id){
        return studentService.getStudentById(id);
    }

    @RequestMapping(value = "/students",method = RequestMethod.POST)
    public void addStudent(@RequestBody Student student){
         studentService.addStudent(student);
    }

    @RequestMapping(value = "/students/{studentId}",method = RequestMethod.PUT)
    public void updateStudent(@RequestBody Student student, @PathVariable String studentId){
        studentService.updateStudent(studentId, student);
    }

    @RequestMapping(value = "/students/{studentId}",method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable String studentId){
        studentService.removeStudent(studentId);
    }
}
