package gluecode;

import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import pojos.MyPojo;
import utilities.PropertiesFileUtility;

public class StepDefs1
{
	Shared sh;
	public StepDefs1(Shared x)
	{
		this.sh=x;
	}
	
	@Given("uri of rest api in {string} environment")
	public void method1(String e) throws Exception
	{
		String u;
		if(e.equalsIgnoreCase("DEV"))
		{
			u=PropertiesFileUtility.getValueInPropertiesFile("devuri");
		}
		else if(e.equalsIgnoreCase("QA"))
		{
			u=PropertiesFileUtility.getValueInPropertiesFile("qauri");
		}	
		else
		{
			u=PropertiesFileUtility.getValueInPropertiesFile("stguri");
		}
		//assign uri to restAssured property
		RestAssured.baseURI=u;
	}
	
	@Given("define HTTP request")
	public void method2()
	{
		sh.req=RestAssured.given();
	}
	
	@When("submit request via HTTP GET method")
	public void method3()
	{
		sh.res=sh.req.when().get();
	}
	
	@Then("status code is {int} and content type is {string}")
	public void method4(int esc, String ect)
	{
		 try
		 {
			sh.res.then().statusCode(esc).contentType(ect);
		    sh.s.log("Test passed");  //cucumber report 
		    Assert.assertTrue(true); //TestNg report
		    
		 }
		 catch(Exception ex)
		 {
			sh.s.log("Test failed"); //cucumber report 
		    Assert.assertTrue(false); //TestNg report
		 }	
	}
	
	@When("submit request for id {string} via GET method")
	public void method5(String id)
	{
		sh.res=sh.req.get(id); //x as path parameter
	}
	
	@Then("status code is {string} and id is {string} in json response body")
	public void method6(String esc, String eid) throws Exception
	{
		int x=Integer.parseInt(esc);
		int y=Integer.parseInt(eid);
		ObjectMapper om=new ObjectMapper();
	    MyPojo mp=om.readValue(sh.res.getBody().asString(),MyPojo.class);
	    if(sh.res.getStatusCode()==x && mp.getId()==y)
	    {
	    	sh.s.log("Test passed");
	    	Assert.assertTrue(true);
	    }
	    else
	    {
	    	sh.s.log("Test failed");
	    	Assert.assertTrue(false);
	    }
	}
	
	
	
	
	
	

}
