package com.training.hibernate;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.training.hibernate.dto.PersonDto;
import com.training.hibernate.dto.RoleDto;
import com.training.hibernate.model.Gender;
import com.training.hibernate.model.Type;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
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
			if(request.getParameter("personId").isEmpty()){
				personDto.setId(Integer.parseInt(request.getParameter("personId")));
			}
			else{
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date date = null;
				try{
					date = df.parse(request.getParameter("birthdate"));
				}catch(ParseException e){
					e.printStackTrace();
				}	
				personDto.setFirstName(request.getParameter("firstName"));
				personDto.setMiddleName(request.getParameter("middleName"));
				personDto.setLastName(request.getParameter("lastName"));
				personDto.setGender(Gender.valueOf(request.getParameter("gender").toUpperCase()));
				personDto.setBirthdate(date);
				if(request.getParameter("employed").equals("yes")){
					personDto.setEmployed(true);	
				}
				else{
					personDto.setEmployed(false);
				}
				personDto.setGwa(Float.parseFloat(request.getParameter("gwa")));
				Set<RoleDto> roles = new HashSet<>();
				for(String role : request.getParameterValues("role")){
					RoleDto roleDto = new RoleDto(role);
					roles.add(roleDto);
				}
				personDto.setRoles(roles);
			}
			personService.updatePerson(personDto);
			request.getRequestDispatcher("/WEB-INF/views/add_person.jsp").forward(request, response);
		}
	}
	
	public void destroy() {
		// do nothing.
	}
}	