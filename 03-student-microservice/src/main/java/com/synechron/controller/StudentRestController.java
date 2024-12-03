package com.synechron.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.synechron.exceptions.StudentNotFoundException;
import com.synechron.feignproxy.CourseServiceProxy;
import com.synechron.model.Course;
import com.synechron.model.Student;
import com.synechron.service.StudentService;

@RestController
public class StudentRestController {
	@Autowired
	private StudentService studentService;
	
    @Autowired
    private CourseServiceProxy proxy;
	
	// http://localhost:9095/students/101
	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable("studentId") Integer studentId) throws StudentNotFoundException 
	{
		Student student = studentService.findStudentById(studentId);
        List<Course> courses = proxy.getCoursesByStudentId(studentId);
		student.setCourse(courses);
		return student;
	}
}










