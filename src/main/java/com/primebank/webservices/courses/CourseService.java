package com.primebank.webservices.courses;

import org.apache.catalina.util.ToStringUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseService {

    private static List<Course> courselist = new ArrayList<>();

    static {
        courselist.add(new Course(1,"Java","manik"));
        courselist.add(new Course(2,"Network","monir"));
    }
    public List<Course> getCourseAll(){
        return this.courselist;
    }

    public Course getCourseById(long id){
        List<Course> courseList =  courselist.stream().filter(course -> course.getId() == id).collect(Collectors.toList());
      // filter(Course course => course.getId() == id);
       return courseList.get(0);
    }

    public StatusEnum deleteCourseByid(int ID) {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "Java", "Manik"));
        courseList.add(new Course(2, "Networking", "munir"));
        return StatusEnum.SUCCESS;
    }

}
