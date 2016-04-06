package com.training.hibernate;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.training.hibernate.dto.*;
import org.hibernate.HibernateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.training.hibernate.model.*;

import com.training.hibernate.services.*;

public class MainServlet extends HttpServlet{
	private PersonService personService = new PersonService();
	
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		List<PersonDto> personDtos = null;
		Set<RoleDto> roleDtos = null;
		if (request.getParameter("search") != null){
			if (request.getParameter("lastName").isEmpty() && request.getParameter("firstName").isEmpty()
			 && request.getParameter("middleName").isEmpty() && request.getParameter("roles").isEmpty()){
				personDtos = personService.getAllPersons();
    			
			}
			else{
				personDtos = personService.searchPerson(request.getParameter("lastName"),request.getParameter("firstName"),
					request.getParameter("middleName"),request.getParameter("roles"));
			}
			request.setAttribute("persons",personDtos);
			request.setAttribute("roles",personService.getRoles());
			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		}
		else{
			personDtos = personService.getAllPersons();
			request.setAttribute("persons", personService.getAllPersons());
			request.setAttribute("roles",personService.getRoles());
			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		}
    	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		//add ************************************************
		if (request.getParameter("add") != null) {
    		request.getRequestDispatcher("/WEB-INF/views/person.jsp").forward(request, response);
    	}

    	//update***************************************************
    	if (request.getParameter("update") != null){
    		if (request.getParameterMap().containsKey("personId")){
    			try{
    				PersonDto personDto = personService.getPersonById(Integer.parseInt(request.getParameter("personId")));
    				request.setAttribute("person", personDto);
    			}catch(HibernateException e){
    				e.printStackTrace();
    				request.setAttribute("error","Unable to retrieve person");
    			}
    		}
    		request.getRequestDispatcher("/WEB-INF/views/person.jsp").forward(request, response);
    	}

    	//save*****************************************************
    	if(request.getParameter("save") != null){
    		PersonDto personDto = personService.createPersonDto(request.getParameter("personId"), request.getParameter("firstName"),
					request.getParameter("middleName"), request.getParameter("lastName"),
					request.getParameter("gender"), request.getParameter("birthdate"), request.getParameter("employed"),
					request.getParameter("gwa"), request.getParameter("street"), request.getParameter("houseNo"),
					request.getParameter("barangay"), request.getParameter("subdivision"), request.getParameter("city"),
					request.getParameter("zipcode"), request.getParameterValues("contactType"),
					request.getParameterValues("contactValue"), request.getParameterValues("contactId"),
					request.getParameterValues("role"),request.getParameterValues("savedContactValue"),request.getParameterValues("savedContactType"));

			boolean isRequired = personService.isRequired(request.getParameter("firstName"),
					request.getParameter("middleName"), request.getParameter("lastName"),
					request.getParameter("gender"), request.getParameter("employed"), request.getParameter("street"),
					request.getParameter("barangay"), request.getParameter("subdivision"),
					request.getParameter("city"), request.getParameter("zipcode"),
					request.getParameterValues("contactType"), request.getParameterValues("contactValue"),
					request.getParameterValues("role"));

			boolean isNumber = personService.isNumber(request.getParameter("houseNo"));
			boolean isDate = personService.isDate(request.getParameter("birthdate"));
			boolean isDecimal = personService.isDecimal(request.getParameter("gwa"));

			if (isRequired && isNumber && isDate && isDecimal){
				String message = "";
				try{
					message = personService.saveOrUpdatePerson(personDto);
				}catch(HibernateException e){
					e.printStackTrace();
				}
				response.sendRedirect("/index?message="+message);
			}
			else{
				List<String> errors = new ArrayList<>();
				if(!isRequired){
					errors.add("Missing required fields");
				}
				if(!isDate){
					errors.add("Invalid date format");
				}
				if(!isNumber){
					errors.add("Invalid House no");
				}
				if(!isDecimal){
					errors.add("Invalid gwa");
				}
				request.setAttribute("errors",errors);
				request.setAttribute("person",personDto);
				request.getRequestDispatcher("/WEB-INF/views/person.jsp").forward(request, response);
			}		
		}


		//delete *********************************************************
		if (request.getParameter("delete") != null){
			for(String id : request.getParameterValues("personId")){
				personService.deletePerson(Integer.parseInt(id));
			}
			response.sendRedirect("/index?message=Person deleted");
		}
	}
	
	public void destroy() {
		// do nothing.
	}
}	