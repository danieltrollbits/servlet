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
    		PersonDto personDto  = personDto = createPersonDto(request.getParameter("personId"), request.getParameter("firstName"),
					request.getParameter("middleName"), request.getParameter("lastName"),
					request.getParameter("gender"), request.getParameter("birthdate"), request.getParameter("employed"),
					request.getParameter("gwa"), request.getParameter("street"), request.getParameter("houseNo"),
					request.getParameter("barangay"), request.getParameter("subdivision"), request.getParameter("city"),
					request.getParameter("zipcode"), request.getParameterValues("contactType"),
					request.getParameterValues("contactValue"), request.getParameterValues("contactId"), request.getParameterValues("role"));

			boolean isValid = validateRequiredFields(request.getParameter("firstName"), request.getParameter("middleName"), request.getParameter("lastName"),
					request.getParameter("gender"), request.getParameter("birthdate"), request.getParameter("employed"),
					request.getParameter("gwa"), request.getParameter("street"), request.getParameter("houseNo"),
					request.getParameter("barangay"), request.getParameter("subdivision"), request.getParameter("city"),
					request.getParameter("zipcode"), request.getParameterValues("contactType"),
					request.getParameterValues("contactValue"), request.getParameterValues("role"));

			if (isValid){
				String message = "";
				try{
					message = personService.saveOrUpdatePerson(personDto);
				}catch(HibernateException e){
					e.printStackTrace();
				}
				response.sendRedirect("/index?message="+message);
			}
			else{
				request.setAttribute("message","Missing required fields / Check date format (MMMM dd, yyyy)");
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

	public PersonDto createPersonDto(String id, String firstName, String middleName, String lastName, String gender, String birthdate,
		String employed, String gwa, String street, String houseNo, String barangay, String subdivision,
		String city, String zipCode, String[] contactTypeList, String[] contactValueList, String[] contactId,
		String[] roleList){
		
		PersonDto personDto = new PersonDto();
		if(!id.isEmpty()){
			personDto.setId(Integer.parseInt(id));
		}
		DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
		Date date = null;
		try{
			date = df.parse(birthdate);
		}catch(ParseException e){
			e.printStackTrace();
		}
		personDto.setFirstName(firstName);
		personDto.setMiddleName(middleName);
		personDto.setLastName(lastName);
		personDto.setGender(Gender.valueOf(gender.toUpperCase()));
		personDto.setBirthdate(date);
		if(employed.equals("yes")){
			personDto.setEmployed(true);	
		}
		else{
			personDto.setEmployed(false);
		}
		personDto.setGwa(Float.parseFloat(gwa));
		Set<RoleDto> roleDtos = new HashSet<>();
		if(roleList != null){
			for(String role : roleList){
				RoleDto roleDto = new RoleDto(role);
				roleDtos.add(roleDto);
			}
			personDto.setRoleDtos(roleDtos);	
		}
		
		//address
		AddressDto addressDto = new AddressDto();
		addressDto.setStreet(street);
		addressDto.setBarangay(barangay);
		addressDto.setSubdivision(subdivision);
		addressDto.setCity(city);
		addressDto.setZipCode(zipCode);
		addressDto.setHouseNo(Integer.parseInt(houseNo));
		personDto.setAddressDto(addressDto);
		Set<ContactDto> contactDtos = new HashSet<>();
		if(contactTypeList != null && contactValueList != null){
			for (int i=0; i<contactTypeList.length; i++){
				ContactDto contactDto = new ContactDto();
				if(contactId != null || contactId != 0){
					if(contactId[i] != null){
						contactDto.setId(Integer.parseInt(contactId[i]));	
					}
				}
				contactDto.setType(Type.valueOf(contactTypeList[i].toUpperCase()));
				contactDto.setValue(contactValueList[i]);
				contactDtos.add(contactDto);
			}	
		}
		personDto.setContactDtos(contactDtos);
		return personDto;
	}

	public boolean validateRequiredFields(String firstName, String middleName, String lastName, String gender,
		String birthdate, String employed, String gwa, String street, String houseNo, String barangay,
		String subdivision, String city, String zipCode, String[] contactTypeList, String[] contactValueList, String[] roleList){
		boolean isValid = false;

		if(firstName.isEmpty() && middleName.isEmpty() && lastName.isEmpty() && gender.isEmpty() && birthdate.isEmpty() &&
			gwa.isEmpty() && street.isEmpty() && houseNo.isEmpty() && barangay.isEmpty() && city.isEmpty() &&
			zipCode.isEmpty() && roleList.length == 0){
			isValid = false;
		}
		else if(!birthdate.isEmpty()){
			DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
			Date date = null;
			try{
				date = df.parse(birthdate);
				isValid = true;
			}catch(ParseException e){
				e.printStackTrace();
				isValid = false;
			}
		}
		else if(!houseNo.matches("\\d+")){
			isValid = false;
		}
		else{
			isValid = true;
		}
		return isValid;
	}
	
	public void destroy() {
		// do nothing.
	}
}	