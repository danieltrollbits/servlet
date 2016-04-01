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
    				request.setAttribute("message","Unable to retrieve person");
    			}
    		}
    		request.getRequestDispatcher("/WEB-INF/views/person.jsp").forward(request, response);
    	}

    	//save*****************************************************
    	if(request.getParameter("save") != null){
			PersonDto personDto = new PersonDto();
			if(!request.getParameter("personId").isEmpty()){
				personDto.setId(Integer.parseInt(request.getParameter("personId")));
			}
				DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
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
				Set<RoleDto> roleDtos = new HashSet<>();
				for(String role : request.getParameterValues("role")){
					RoleDto roleDto = new RoleDto(role);
					roleDtos.add(roleDto);
				}
				personDto.setRoleDtos(roleDtos);
				//address
				AddressDto addressDto = new AddressDto();
				addressDto.setStreet(request.getParameter("street"));
				addressDto.setBarangay(request.getParameter("barangay"));
				addressDto.setSubdivision(request.getParameter("subdivision"));
				addressDto.setCity(request.getParameter("city"));
				addressDto.setZipCode(request.getParameter("zipcode"));
				addressDto.setHouseNo(Integer.parseInt(request.getParameter("houseNo")));
				personDto.setAddressDto(addressDto);
				String[] contactTypeList = request.getParameterValues("contactType");
				String[] contactValueList = request.getParameterValues("contactValue");
				Set<ContactDto> contactDtos = new HashSet<>();
				for (int i=0; i<contactTypeList.length; i++){
					ContactDto contactDto = new ContactDto();	
					contactDto.setType(Type.valueOf(contactTypeList[i].toUpperCase()));
					contactDto.setValue(contactValueList[i]);
					contactDtos.add(contactDto);
				}
				personDto.setContactDtos(contactDtos);

			String message = "";	
			if(request.getParameter("personId").isEmpty()){
				try{
					personService.addPerson(personDto);
					message = "Person added";
				}catch(HibernateException e){
					e.printStackTrace();
					message = "Failed to add person";
				}
			}
			else{
				try{
					personService.updatePerson(personDto);
					message = "Person updated";
				}catch(HibernateException e){
					e.printStackTrace();
					message = "Failed to update person";
				}	
			}
			// request.setAttribute("persons",getPersonDtos());
			response.sendRedirect("/index?message="+message);
		}
		//****************************************************************


		//delete *********************************************************
		if (request.getParameter("delete") != null){
			for(String id : request.getParameterValues("personId")){
				personService.deletePerson(Integer.parseInt(id));
			}
			// request.setAttribute("persons", getPersonDtos());
			response.sendRedirect("/index?message=Person deleted");
		}
	}
	
	public void destroy() {
		// do nothing.
	}
}	