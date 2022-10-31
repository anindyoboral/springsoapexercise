package be.abis.exercise.repository;

import be.abis.exercise.ws.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryCourseRepository implements CourseRepository {
    List<Course> courseList = new ArrayList<Course>();

    public MemoryCourseRepository(){
        courseList.add(new Course("1","SQLBAS","SQL Fundamentals",2,375.0));
        courseList.add(new Course("2","SPRING","SPRING Fundamentals",3,450.0));
        courseList.add(new Course("3","SPRINGREST","Building REST APIs with Spring Boot ",3,500.0));
    }

    @Override
    public Course findCourseByShortTitle(String shortTitle) {
        return courseList.stream().filter(c->c.getShortTitle().equals(shortTitle)).findFirst().orElse(null);
    }

    @Override
    public List<Course> findCoursesByNumberOfDays(int numberOfDays) {
        return  courseList.stream().filter(c->c.getNumberOfDays()==numberOfDays).collect(Collectors.toList());
    }
}
