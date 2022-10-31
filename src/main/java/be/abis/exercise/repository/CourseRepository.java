package be.abis.exercise.repository;

import be.abis.exercise.ws.model.Course;

import java.util.List;

public interface CourseRepository {

    Course findCourseByShortTitle(String shortTitle);
    List<Course> findCoursesByNumberOfDays(int numberOfDays);

}
