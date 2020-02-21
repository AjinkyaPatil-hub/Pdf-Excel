package com.springboot.pdfexcel.pdfexcel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.pdfexcel.pdfexcel.model.Employee;
import com.springboot.pdfexcel.pdfexcel.service.EmployeeService;

@Controller  
public class PdfExcelController {

	@Autowired
	private EmployeeService empService;

	@Autowired
	private ServletContext context;
	
	@GetMapping(value="/")
	public String allEmployees(Model model) {
		
		List<Employee> employess = empService.getAllEmployees();
		model.addAttribute("employess", employess);
		
		return "view/employees";
	}
	
	
	@GetMapping(value="/createPdf")
	public void createPdf(HttpServletRequest req , HttpServletResponse res) {
		
		List<Employee> employess = empService.getAllEmployees();
		boolean isFlag = empService.createPdf(employess,context,req,res);
		
		if(isFlag) {
			//names should be same as mention in the service class
			String fullPath =req.getServletContext().getRealPath("/resources/reports/"+"employees"+".pdf");
			fileDownload(fullPath,res,"employees.pdf");
		
		}
	}

	@GetMapping(value="/createExcel")
	public void createExcel(HttpServletRequest req , HttpServletResponse res) {
		
		List<Employee> employess = empService.getAllEmployees();
		boolean isFlag = empService.createExcel(employess,context,req,res);
		if(isFlag) {
			String fullPath  = req.getServletContext().getRealPath("/resources/reports/"+"employees"+".xls");
			fileDownload(fullPath, res, "employess.xls");
		}
	
	}
	
	

	private void fileDownload(String fullPath, HttpServletResponse res, String fileName) {
			File file = new File(fullPath);
			final int BUFFER_SIZE = 4096;
			if(file.exists()) {
				try {
					FileInputStream fis = new FileInputStream(file);
					String mimeType = context.getMimeType(fullPath);
					res.setContentType(mimeType);
					res.setHeader("content-disposition", "attachment;filename="+fileName);
					OutputStream ois = res.getOutputStream();
					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;
					while((bytesRead = fis.read(buffer)) !=-1) {
						ois.write(buffer,0,bytesRead);
					}
					
					fis.close();
					ois.close();
					file.delete();
					
					
				}catch (Exception e) {
					e.printStackTrace();
					
				}
			}
			
	}
	

}
