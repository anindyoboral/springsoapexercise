package be.abis.exercise.ws.endpoint;


import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.ws.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseServiceEndpoint {

    private static final String NAMESPACE_URI="http://abis.be/courses";

    @Autowired
    ObjectFactory objectFactory;

    @Autowired
    CourseRepository courseRepository;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "courseByShortTitleRequest")
    @ResponsePayload
    public CourseByShortTitleResponse findCourseByShortTitle(@RequestPayload CourseByShortTitleRequest courseByShortTitleRequest){
        String title = courseByShortTitleRequest.getShortTitle();
        System.out.println("finding course " + title);
        CourseByShortTitleResponse courseByShortTitleResponse = objectFactory.createCourseByShortTitleResponse();
        courseByShortTitleResponse.setCourse(courseRepository.findCourseByShortTitle(title));
       return courseByShortTitleResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "coursesByNumberOfDaysRequest")
    @ResponsePayload
    public CoursesByNumberOfDaysResponse findCoursesByNumberOfDays(@RequestPayload CoursesByNumberOfDaysRequest coursesByNumberOfDaysRequest){
        int days= coursesByNumberOfDaysRequest.getNumberOfDays();
        List<Course> foundCourses = courseRepository.findCoursesByNumberOfDays(days);
        Courses courses = new Courses();
        courses.setCourse(foundCourses);
        CoursesByNumberOfDaysResponse coursesByNumberOfDaysResponse =objectFactory.createCoursesByNumberOfDaysResponse();
        coursesByNumberOfDaysResponse.setCourses(courses);
        return coursesByNumberOfDaysResponse;
    }






}
