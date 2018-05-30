package com.testApi.Course.Plan2.service;

import com.fasterxml.jackson.core.JsonParser;
import com.testApi.Course.Plan2.model.Course;
import com.testApi.Course.Plan2.model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class CourseService{

    private StudentService studentService = new StudentService();
    private List<Course> courses;

/*
    private List<Course> courses = new ArrayList<>(Arrays.asList(
            new Course("c1","java","monday","teacher1","classroom1",studentService.getStudentsJava()),
            new Course("c2","c","tuesday","teacher2","classroom2",studentService.getStudentsC()),
            new Course("c3","c++","wednesday","teacher3","classroom3",studentService.getStudentsCPlus())
    ));
*/
    public CourseService() {
        courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File("Courses.txt"));
            while (fileScanner.hasNext()) {
                String c = fileScanner.nextLine();
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(c);

                JSONObject courseData = (JSONObject) obj;
                String courseId = (String) courseData.get("id");
                String courseName = (String) courseData.get("name");
                String courseTime = (String) courseData.get("time");
                String courseTeachers = (String) courseData.get("teachers");
                String courseClassroom = (String) courseData.get("classroom");

                JSONArray studentArrayData = (JSONArray) courseData.get("students");

                for (int i = 0; i < studentArrayData.size(); i++) {
                    JSONObject studentObjectData = (JSONObject) studentArrayData.get(i);
                    String studentId = (String) studentObjectData.get("id");
                    String studentName = (String) studentObjectData.get("name");
                    String studentEmail = (String) studentObjectData.get("email");

                    Student student = new Student(studentId,studentName,studentEmail);
                    students.add(student);
                }

                Course course = new Course(courseId,courseName,courseTime,courseTeachers,courseClassroom,students);
                courses.add(course);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

        public List<Course> getCourses(){
            saveAllCourses();
            return courses;
        }

     public Course getCourseByCourseId(String courseId) throws IOException {

         System.out.println("getCourseByCourseID");
         Course c = new Course();
         for(Course course : courses){
             if(course.getId().equals(courseId))
                 c = course;
         }
         //Save the course
           // saveOneCourse(c);
         return c;
     }
     //To check how many courses a student have chosen
    public List<Course> getCourseByStudentId (String studentId){

         System.out.println("getCourseByStudentID");
         if(studentId.equals("")){
             return courses;
         }

         List<Course> cs = new ArrayList<>();
         for(Course course : courses){
             for(Student student : course.getStudents())
                if(student.getId().contains(studentId))
                    cs.add(course);
         }

         return cs;
     }

     public List<Course> getCourseByCourseAndStudentId(String courseId,String studentId){
         System.out.println("getCourseByCourseIdAndStudentId");
         if(courseId.equals(""))
             return courses;
         else {
             List<Course> cs = new ArrayList<>();
             if(studentId.equals("")){
                 for(Course course : courses)
                     if(course.getId().equals(courseId))
                         cs.add(course);

             }else{
                 for(Course course : courses)
                     for(Student student : course.getStudents())
                         if(course.getId().equals(courseId) && student.getId().equals(studentId))
                             cs.add(course);
             }
             return cs;
         }
     }

    public void addCourse(Course course) {
         courses.add(course);
    }

    public void updateCourse(String courseId,Course course) {
         for(int i=0;i<courses.size();i++){
            if (courses.get(i).getId().equals(courseId))
                courses.set(i,course);
         }
    }

    public void removeCourse(String courseId) {
         for(Course c : courses){
             if(c.getId().equals(courseId))
                 courses.remove(c);
         }
    }

    public void saveAllCourses() {

        try {
            FileWriter fileWriter = new FileWriter("Courses.txt");
            //loop each course
            for(Course  course : courses){
                JSONObject jsonObjectCourse = new JSONObject();
                jsonObjectCourse.put("id",course.getId());
                jsonObjectCourse.put("name",course.getName());
                jsonObjectCourse.put("time",course.getTime());
                jsonObjectCourse.put("teachers",course.getTeachers());
                jsonObjectCourse.put("classroom",course.getClassroom());
                //loop for students inside a course
                JSONArray jsonArrayStudents = new JSONArray();
                //for(int i = 0; i<studentService.getStudents().size();i++) {
                    for(Student student: studentService.getStudents()) {
                        JSONObject studentInsideCourse = new JSONObject();
                        studentInsideCourse.put("id", student.getId());
                        studentInsideCourse.put("name",student.getName());
                        studentInsideCourse.put("email", student.getEmail());
                        jsonArrayStudents.add(studentInsideCourse);
                    }
                //}
                jsonObjectCourse.put("students",jsonArrayStudents);
                fileWriter.write(jsonObjectCourse.toJSONString()+"\n");
            }
fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



     /*     File file = new File("CourseSelected.txt");
        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream output = new ObjectOutputStream(fo);
        output.writeObject(course);

        output.close();
        fo.close();
      FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("Course.txt",true);
            fileWriter.write("test");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

}
