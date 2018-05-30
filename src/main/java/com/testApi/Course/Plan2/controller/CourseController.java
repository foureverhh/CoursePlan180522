package com.testApi.Course.Plan2.controller;

import com.testApi.Course.Plan2.service.CourseService;
import com.testApi.Course.Plan2.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService = new CourseService();

  @RequestMapping(value = "/courses",method = RequestMethod.GET)
    public List<Course>  findCourses(){
        return courseService.getCourses();
    }

 //Get course by searching course id
    @RequestMapping(value = "/courses/{courseId}",method = RequestMethod.GET)
    public Course findCourseById(@PathVariable String courseId) throws IOException {
            return courseService.getCourseByCourseId(courseId);

    }

   //Check whether a student has course a course
    @RequestMapping(value = "/courses/{courseId}/{studentId}",method = RequestMethod.GET)
    public List<Course> findCourseByCourseIdAndStudentId(@PathVariable(value = "courseId") String courseId, @PathVariable(value = "studentId") String studentId){
        return courseService.getCourseByCourseAndStudentId(courseId,studentId);
    }
   /* @RequestMapping(value = "/courses",method = RequestMethod.GET)
    public List<Course> findCourseByStudentId(@RequestParam(value ="studentId",defaultValue = "") String studentId){
        return  courseService.getCourseByStudentId(studentId);
    }*/

    @RequestMapping(value ="/courses",method = RequestMethod.POST)
    public void addCourse (@RequestBody Course course){
        courseService.addCourse(course);
    }

    @RequestMapping(value = "/courses/{courseId}",method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course,@PathVariable String courseId){
        courseService.updateCourse(courseId,course);
    }

    @RequestMapping(value = "/courses/{courseId}",method = RequestMethod.DELETE)
    public void removeCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
    }
}
