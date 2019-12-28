package com.springboot.pdfexcel.pdfexcel.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springboot.pdfexcel.pdfexcel.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	boolean createPdf(List<Employee> employess, ServletContext context, HttpServletRequest req,
			HttpServletResponse res);

	boolean createExcel(List<Employee> employess, ServletContext context, HttpServletRequest req,
			HttpServletResponse res);

	Employee getEmployee(int id);

	Employee createOrUpdateEmp(Employee e);


}
