package com.testApi.Course.Plan2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.testApi.Course.Plan2.model.Student;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class StudentService {

    private List<Student> students;
 /*   private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student("s1","student1","student1@gmail.com"),
            new Student("s2","student2","student2@gmail.com"),
            new Student("s3","student3","student3@gmail.com"),
            new Student("s4","student4","student4@gmail.com"),
            new Student("s6","student6","student6@gmail.com")
    ));*/


   public  StudentService (){
       System.out.println("run");
       //Read java string from txt file
     students = new ArrayList<>();
       try {
         Scanner  fileScanner = new Scanner(new File("Students.txt"));
           while(fileScanner.hasNextLine()){
               String origin = fileScanner.nextLine();
               System.out.println("Origin String is "+origin);
               //Parse java string to json
               JSONParser parser = new JSONParser();
               try{
                   Object obj = parser.parse(origin);
                   JSONObject studentData = (JSONObject) obj;
                   String id = (String) studentData.get("id");
                   String name = (String) studentData.get("name");
                   String email = (String) studentData.get("email");
                   Student student = new Student(id,name,email);
                   students.add(student);

               } catch (ParseException e) {
                   e.printStackTrace();
               }
           }
            fileScanner.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }


   }



    public List<Student> getStudents(){
        saveAllStudents();
        return students;
    }

   private List<Student> studentsJava =new ArrayList<>(Arrays.asList(
           new Student("s2","student 2","student2@gmail.com"),
           new Student("s4","student 4","student4@gmail.com")
   ));

   public List<Student> getStudentsJava(){
      return studentsJava;
   }

    private List<Student>  studentsC = new ArrayList<>(Arrays.asList(
            new Student("s1","student 1","student1@gmail.com"),
            new Student("s3","student 3","student3@gmail.com")
    ));

    public List<Student> getStudentsC(){
        return studentsC;
    }

    private List<Student>  studentsCPlus =new ArrayList<>(Arrays.asList(
            new Student("s1","student 1","student1@gmail.com"),
            new Student("s2","student 2","student2@gmail.com"),
            new Student("s3","student 3","student3@gmail.com")
    ));

    public List<Student> getStudentsCPlus(){
        return studentsCPlus;
    }

    public Student getStudentById(String id){
        Student s = new Student();
        for(Student student : students) {
            if (student.getId().equals(id))
                s = student;
        }
        //Save the found student
        saveOneStudentByFindId(s);
        return s;
    }

    public void addStudent(Student student) {
        students.add(student);
        saveOneStudentByPost(student);
        saveAllStudents();
    }

    public void updateStudent(String studentId,Student student) {
       for(int i = 0; i< students.size(); i++){
           Student s = students.get(i);
           if(s.getId().equals(studentId))
               students.set(i,student);
               saveOneStudentByUpdate(s);
       }
       saveAllStudents();

    }

    public void removeStudent(String studentId) {
        for(Student student : students){
            if(student.getId().equals(studentId))
                students.remove(student);
        }
        saveAllStudents();
    }

    public void saveOneStudentByFindId(Student student){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",student.getId());
        jsonObject.put("name",student.getName());
        jsonObject.put("email",student.getEmail());

        try (FileWriter fileWriter = new FileWriter("SingleStudentBySearch.txt")) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveAllStudents();
    }

    public void saveOneStudentByPost(Student student){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",student.getId());
        jsonObject.put("name",student.getName());
        jsonObject.put("email",student.getEmail());

        try (FileWriter fileWriter = new FileWriter("SingleStudentByPost.txt")) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveAllStudents();
    }

    public void saveOneStudentByUpdate(Student student){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",student.getId());
        jsonObject.put("name",student.getName());
        jsonObject.put("email",student.getEmail());

        try (FileWriter fileWriter = new FileWriter("SingleStudentByUpdate.txt")) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveAllStudents();
    }

    public void saveAllStudents(){
            try {
                FileWriter fileWriter = new FileWriter("Students.txt");
                JSONObject jsonObject  = new JSONObject();
                for(Student student: students) {
                    jsonObject.put("id", student.getId());
                    jsonObject.put("name", student.getName());
                    jsonObject.put("email", student.getEmail());
                    fileWriter.write(jsonObject.toJSONString()+"\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

