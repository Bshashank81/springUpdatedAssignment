package com.assignment.springboot.thymeleaf.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.springboot.thymeleaf.entity.Student;
import com.assignment.springboot.thymeleaf.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
private StudentService studentService;
	
	public StudentController(StudentService theStudentService) {
		studentService = theStudentService;
	}
	
	//add mapping for /list
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		//get students from database
		List<Student> theStudents = studentService.findAll();
		
		//add to the spring model
		theModel.addAttribute("students",theStudents);
		
		return "students/list-students";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Student theStudent = new Student();
		
		theModel.addAttribute("student",theStudent);
		
		return "students/student-form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student theStudent) {
		
		//save the student
		studentService.save(theStudent);
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/students/list";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
									Model theModel) {
		
		//get the student from the service
		Student theStudent = studentService.findById(theId);
		
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("student",theStudent);
		
		//send over to our form
		return "students/student-form";
		
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		
		//delete the student
		studentService.deleteById(theId);
		
		//redirect to /students/list
		return "redirect:/students/list";
		
	}
}
	