package com.training.hibernate;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import com.training.hibernate.dto.PersonDto;
import com.training.hibernate.model.Gender;
import com.training.hibernate.model.Type;
import org.apache.commons.beanutils.BeanUtils;

import com.training.hibernate.services.*;

public class PersonServlet extends HttpServlet{
	private PersonService personService = new PersonService();
	
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	System.out.println(request.getParameter("add") + "---------------");
    	if (request.getParameter("add") != null) {
    		System.out.println("add--------------");
    		request.getRequestDispatcher("/WEB-INF/views/add_person.jsp").forward(request, response);
    	}
    	if (request.getParameter("update") != null){
    		System.out.println("update--------------");
    		if (request.getParameterMap().containsKey("personId")){
    			PersonDto personDto = personService.getPersonById(Integer.parseInt(request.getParameter("personId")));
    			request.setAttribute("person", personDto);
    			request.getRequestDispatcher("/WEB-INF/views/add_person.jsp").forward(request, response);
    		}
    	}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		if(request.getParameter("save") != null){
			PersonDto personDto = new PersonDto();
			System.out.println(request.getParameter("address"));
			/*BeanUtils.populate(personDto,request);
			if(request.getParameter("personId").isEmpty()){
				personDto.setId(Integer.parseInt(request.getParameter("personId")));
			}
			else{
				personDto.setFirstName(request.getParameter("firstName"));
				personDto.setMiddleName(request.getParameter("middleName"));
				personDto.setLastName(request.getParameter("lastName"));
				personDto.setGender(Gender.valueOf(request.getParameter("gender").toUpperCase()));
				personDto.setBirthdate(request.getParameter("birthdate"));
				personDto.setEmployed(request.getParameter("employed"));
				personDto.setGwa(request.getParameter("gwa"));
				personDto.setRoles(request.getParameterValues("role"));
			}
			personService.updatePerson(personDto);*/
		}
	}
	
	public void destroy() {
		// do nothing.
	}
}	