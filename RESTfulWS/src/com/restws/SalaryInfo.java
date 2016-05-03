package com.restws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
	@Path here defines class level path. Identifies the URI path that a resource class will serve requests for.
*/
@Path("SalaryService")
public class SalaryInfo {

	/*
		@GET 		: here defines, this method will process HTTP GET requests.
		@Path		: here defines METHOD LEVEL PATH. Identifies the URI path that a resource class method will serve requests for.
		@Produces	: here defines the MEDIA TYPE(s) that the methods of a resource class can produce.
		@PathParam	: injects the value of URI parameter that defined in   @Path   expression, into the method.
	*/
	@GET
	@Path("/joiningdate/{year}/{month}/{day}")
	@Produces(MediaType.TEXT_XML)
	public String userName(@PathParam("year") int year,
							@PathParam("month") int month, 
							@PathParam("day") int day) {

		String JOINING_DATE = month + "/" + day + "/" + year;
		return "<User>" + "<Name>" + JOINING_DATE + "</Name>" + "</User>";
	}
	
	
	
	
	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(
			@PathParam("year") int year,
			@PathParam("month") int month, 
			@PathParam("day") int day) {
 
	   String date = year + "/" + month + "/" + day;
 
	   return Response.status(200)
		.entity("getUserHistory is called, year/month/day : " + date)
		.build();
 
	}

	@GET
	@Path("/age/{dob}")
	@Produces(MediaType.TEXT_XML)
	public String userAge(@PathParam("dob") String dob) {
		System.out.println("Date of Birth is = " + dob);
		Date dobirth = convertStringtoDate(dob);
		Date cDate = new Date();
		int age = 0;
		//System.out.println(cDate-dobirth);
		long current = cDate.getTime();
		long old = dobirth.getTime();
		long diff = (current - old);
		int days = (int)(diff / (1000 * 60 * 60 * 24));
		int years = days / 365 ;
		int day = days % 365;
		return "<User>" + "<Age>" + years +" and " +day + "</Age>" + "</User>";
	}

	private Date convertStringtoDate(String dob) {
		Date dat = null;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		try {
			dat = sdf.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dat;
	}
}
