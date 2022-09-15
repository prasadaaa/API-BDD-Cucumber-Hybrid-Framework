package gluecode;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs2
{
	Shared sh;
	public StepDefs2(Shared x)
	{
		this.sh=x;
	}
	
	@Then("status code is {string} as per {string} in json response body")
	public void method1(String esc, String criteria)
	{
		int x=Integer.parseInt(esc);
		if(criteria.equals("valid") && 
				sh.res.getStatusCode()==x)
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else if(criteria.equals("invalid") && 
				sh.res.getStatusCode()==x)
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else if(criteria.equals("blank") && 
				sh.res.getStatusCode()==x)
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else
		{
			sh.s.log("Test failed for criteria "+criteria+
					" because expected is "+x+" but actual is "+
					sh.res.getStatusCode());
			Assert.assertTrue(false); 
		}
	}
	
	@When("submit request via POST with:")
	public void method2(String x)
	{
		sh.req.header("Content-Type","application/json");
		sh.req.body(x);
		sh.res=sh.req.when().post();
	}
	
	@Then("status code is {string} as per {string} and content type is {string}")
	public void method3(String x, String y, String z) 
	{
		int esc=Integer.parseInt(x);
		String criteria=y;
		String ect=z;
		if(criteria.equals("unique") && 
				sh.res.getStatusCode()==esc && 
				sh.res.getContentType().contains(ect))
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else if(criteria.equals("duplicate") && 
				sh.res.getStatusCode()==esc && 
				sh.res.getContentType().contains(ect))
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else if(criteria.equals("wrong") && 
				sh.res.getStatusCode()==esc && 
				sh.res.getContentType().contains(ect))
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else
		{
			sh.s.log("Test failed for criteria "+criteria+
					" because expected is "+esc+" but actual is "+
					             sh.res.getStatusCode()+ 
					             " and "+sh.res.getContentType());
			Assert.assertTrue(false); 
		}
	}
}










