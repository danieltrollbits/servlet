package com.training.hibernate;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import com.training.hibernate.dto.PersonDto;

import com.training.hibernate.services.*;

public class DisplayServlet extends HttpServlet{
	private PersonService personService = new PersonService();
	
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		List<PersonDto> personDtos = null;
		if (request.getParameter("search") != null){
			if (request.getParameter("lastName").isEmpty() && request.getParameter("firstName").isEmpty()
			 && request.getParameter("middleName").isEmpty() && request.getParameter("role").isEmpty()){
				personDtos = personService.getAllPersons();
    			
			}
			else{
				personDtos = personService.searchPerson(request.getParameter("lastName"),request.getParameter("firstName"),
					request.getParameter("middleName"),request.getParameter("role"));
			}
		}
		else{
			personDtos = personService.getAllPersons();
		}
		request.setAttribute("persons", personDtos);
    	request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		if (request.getParameter("delete") != null){
			for(String id : request.getParameterValues("personId")){
				personService.deletePerson(Integer.parseInt(id));
			}
			doGet(request, response);
		}
	}
	
	public void destroy() {
		// do nothing.
	}
}	