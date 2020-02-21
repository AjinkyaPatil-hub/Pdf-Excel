package com.springboot.pdfexcel.pdfexcel.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pdfexcel.pdfexcel.model.Employee;
import com.springboot.pdfexcel.pdfexcel.service.EmployeeService;

@RestController
@RequestMapping(value="/employees")
public class EmployeeRestfullController {

	@Autowired
	private EmployeeService service;

	@GetMapping(value="/")
	private ResponseEntity getAllEmployees() {
		List<Employee> allEmployees = service.getAllEmployees();
		return new ResponseEntity(allEmployees, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}")
	public Employee getEmployee(@PathVariable ("id")int id) {
		 Employee employee = service.getEmployee(id);
		 if(employee !=null) {
			 return employee;
		 }else {
			 return new Employee();
		 }
	}
	
	@PostMapping(value = "/")
	public Employee  createOrupdate(@RequestBody Employee e) {
		Employee emp = service.createOrUpdateEmp(e);
		if(emp !=null) {
			return emp;
		}else {
			return null;
		}
	}
}
