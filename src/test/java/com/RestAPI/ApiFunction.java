package com.RestAPI;

import java.io.FileReader;
import java.util.Map;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.documentContext;
import org.apache.http.client.methods.RequestBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

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
	public void setValueinJsonPayload(String PayloadName,Map<String,String>param) {
		try {	
			JSONParser parser= new JSONParser();
			
			Object obj= parser.parse(new FileReader(PayloadName));
			JSONObject jsonobject= (JSONObject)obj;
			
			String fromfile= jsonobject.toJSONString();
			//Configuration configuration=Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider()).mappingProvider(new JacksonMappingProvider())
			//		.build();
			DocumentContext json=JsonPath.using(configuration).parse(fromfile);
			Map<String,String> linkmap= new LinkedHashMap<>(param);
			Object key=param.keySet().iterator().next();
			linkmap.remove(key);
			for(Map.Entry<String,String> mp :param.entrySet()){
			
			json.set(mp.getKey(),mp.getValue());
		}
			
			String body=json.jsonString();
		catch(Exception e) {
			e.getLocalizedMessage();}
	}

}
