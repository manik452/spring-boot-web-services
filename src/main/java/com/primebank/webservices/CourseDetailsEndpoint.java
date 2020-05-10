package com.primebank.webservices;

import com.mamarbari.testdemo.*;
import com.primebank.webservices.courses.Course;
import com.primebank.webservices.courses.CourseService;
import com.primebank.webservices.courses.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    private CourseService courseService;

    @PayloadRoot(namespace = "http://mamarbari.com/testdemo", localPart = "GetCourseDetailsRequest")
    public @ResponsePayload
    GetCourseDetailsResponse getCourse(@RequestPayload GetCourseDetailsRequest courseDetailsRequest) {
        GetCourseDetailsResponse getCourseDetailsResponse = new GetCourseDetailsResponse();
        Course course = courseService.getCourseById(courseDetailsRequest.getId().longValue());
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(BigInteger.valueOf(course.getId()));
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getAuthor());
        getCourseDetailsResponse.setCourseDetails(courseDetails);
        return getCourseDetailsResponse;
    }

    @PayloadRoot(namespace = "http://mamarbari.com/testdemo", localPart = "DeleteCourseDetailsRequest")
    public @ResponsePayload
    DeleteCourseDetailsResponse deleteCourse(@RequestPayload DeleteCourseDetailsRequest courseDetailsRequest) {
        StatusEnum status = courseService.deleteCourseByid(courseDetailsRequest.getId());

        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(mapStatus(status));
        return response;
    }

    private Status mapStatus(StatusEnum status) {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new CourseException("Invalid Curse input");
        }

        return status == StatusEnum.FAILURE ? Status.FAILURE : Status.SUCCESS;
    }
}
