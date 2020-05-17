package com.RestAPI;

import java.util.Map;

import org.apache.http.client.methods.RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiFunction {

	public enum RequestMethod{
		POST,GET
	}

	public static RequestSpecBuilder requestbuilder;


	public void getHeaders(Map<String,String>headers) {
		try {
			requestbuilder.addHeaders(headers);
		}
		catch(Exception e) {
			e.getLocalizedMessage();}
	}


	public void getBaseURI(String uri) {
		try {
			requestbuilder=new RequestSpecBuilder();	
			requestbuilder.setBaseUri(uri);
		}
		catch(Exception e) {
			e.getLocalizedMessage();}
	}



	public void getBasePath(String endpoint) {
		try {	
			requestbuilder.setBasePath(endpoint);
		}
		catch(Exception e) {
			e.getLocalizedMessage();}
	}


	public void addQueryParams(Map<String,String>queryparam) {
		try {	
			requestbuilder.addQueryParams(queryparam);
		}
		catch(Exception e) {
			e.getLocalizedMessage();}
	}

	public void addPathParams(Map<String,String>queryparam) {
		try {	
			requestbuilder.addParams(queryparam);
		}
		catch(Exception e) {
			e.getLocalizedMessage();}
	}


	public void setBasicAuth(Map<String,String>param) {
		try {	
			PreemptiveBasicAuthScheme basicAuth= new PreemptiveBasicAuthScheme();
			basicAuth.setUserName(param.get("userName"));
			basicAuth.setPassword(param.get("userName"));
			requestbuilder.setAuth(basicAuth);
		}
		catch(Exception e) {
			e.getLocalizedMessage();}
	}


	public void submitRequest(String request,String API) {
		RequestSpecBuilder spec= requestbuilder;
		RestAssured.useRelaxedHTTPSValidation();

		if(request.equals(String.valueOf(RequestMethod.GET))) {
			RequestSpecification request1=RestAssured.given().spec(spec.build());
			Response response=request1.when().get();


		}


	}

}
