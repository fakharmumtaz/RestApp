package com.restclient;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserInfoClient {

	public static final String BASE_URI = "http://localhost:8080/RESTfulWS";
	public static final String PATH_NAME = "/UserInfoService/name/";
	public static final String PATH_AGE = "/UserInfoService/age/";

	public static final String PATH_DATE_OF_JOIN = "/SalaryService/joiningdate/";
	public static final String PATH_GETUSERHISTORY = "/SalaryService/";
	public static final String PATH_GETUSERAGE = "/SalaryService/age/";
	
	public static void main(String[] args) {

		String name = "Pavithra";
		int age = 25;
		
		int month = 2;
		int day = 22;
		int year = 1922;

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URI);

		WebResource nameResource = resource.path("rest").path(PATH_NAME + name);
		System.out.println("Client Response =\n"	+ getClientResponse(nameResource));
		System.out.println("Response =\n" + getResponse(nameResource) + "\n");

		WebResource ageResource = resource.path("rest").path(PATH_AGE + age);
		System.out.println("Client Response =\n"	+ getClientResponse(ageResource));
		System.out.println("Response =\n" + getResponse(ageResource) + "\n");
		
		WebResource dojResource = resource.path("rest").path(PATH_DATE_OF_JOIN + year + "/" + month + "/" + day);
		System.out.println("Client Response =\n"	+ getClientResponse(dojResource));
		System.out.println("Response =\n" + getResponse(dojResource)+ "\n");
		
		WebResource getUserHistoryResource = resource.path("rest").path(PATH_GETUSERHISTORY + year + "/" + month + "/" + day);
		System.out.println("Client Response =\n"	+ getClientResponse(getUserHistoryResource));
		System.out.println("Response =\n" + getResponse(getUserHistoryResource)+ "\n");
		
		String dob = year +""+ (month<10?"0"+month:month)+""+(day<10?"0"+day:day);
		System.out.println(dob);
		WebResource userAgeResource = resource.path("rest").path(PATH_GETUSERAGE + dob);
		System.out.println("Client Response =\n"	+ getClientResponse(userAgeResource));
		System.out.println("Response =\n" + getResponse(userAgeResource)+ "\n");
	}

	/**
	 * Returns client response. e.g : GET
	 * http://localhost:8080/DZoneRESTfulWS/rest/UserInfoService/name/Pavithra
	 * --or--
	 * http://localhost:8080/DZoneRESTfulWS/rest/UserInfoService/age/25
	 * returned a response status of 200 OK
	 *
	 * @param service
	 * @return
	 */
	private static String getClientResponse(WebResource resource) {
		return resource.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
	}

	/**
	 * Returns the response as XML e.g : <User><Name>Pavithra</Name></User>
	 * 
	 * @param service
	 * @return
	 */
	private static String getResponse(WebResource resource) {
		return resource.accept(MediaType.TEXT_XML).get(String.class);
	}
}