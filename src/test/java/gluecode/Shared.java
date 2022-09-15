package gluecode;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Shared
{
	//Common objects which are needed for all step definition classes
	public RequestSpecification req; //related to rest-assured
	public Response res; //related to rest-assured
	public Scenario s; //related to cucumber-java
	public static String name;
	
	//before for every Scenario including "Background:" steps
	@Before
	public void method1(Scenario scobj) //"scobj" is object of currently running scenarion
	{
		s=scobj;
		s.log("Hi, "+s.getName()+" is going to run");
	}
	
	//after for every scenario
	@After
	public void method2(Scenario scobj)
	{
		s=scobj;
		s.log(s.getName()+" is "+s.getStatus().name());
	}	
}

