package gluecode;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.TextFilesUtility;

public class StepDefs4
{
	Shared sh;
	ArrayList<String> result;
	public StepDefs4(Shared x)
	{
		this.sh=x;
		result=new ArrayList<String>();
	}
	
	@When("submit request for id {int} via DELETE method")
	public void method1(int id)
	{
		sh.res=sh.req.delete(""+id); //argument type is String
	}
	
	@Then("status code is {int}")
	public void method2(int esc)
	{
		if(sh.res.getStatusCode()==esc)
		{
			sh.s.log("Test passed");
			Assert.assertTrue(true);
		}
		else
		{
			sh.s.log("Test failed because expected status code is "+esc+
					" and actual status code is "+sh.res.getStatusCode());
			Assert.assertTrue(false);
		}
	}
	
	@When("submit request via DELETE to service by taking data from text file")
	public void method12() throws Exception
	{
	    String fp="src\\test\\resources\\sources\\testdata.txt";
	    int ln=1;
	    String[] pieces;
	    while((pieces=TextFilesUtility.getValuesOfLine(fp,ln))!=null)
	    {
	    	sh.res=sh.req.when().delete(pieces[0]); //id is path parameter
	    	if(sh.res.statusCode()==204 && pieces[1].equals("valid"))
	    	{
	    		result.add("passed");
	    	}
	    	else if(sh.res.statusCode()==404 && pieces[1].equals("invalid"))
	    	{
	    		result.add("passed");
	    	}
	    	else if(sh.res.statusCode()==400 && pieces[1].equals("blank"))
	    	{
	    		result.add("passed");
	    	}
	    	else
	    	{
	    		result.add("failed");
	    		System.out.println(sh.res.getStatusCode());
	    	}
	    	ln++; //for next line of text in text file
	    }
	}

	@Then("validate response as per data in text file")
	public void method13()
	{
	    int flag=0;
	    SoftAssert sa=new SoftAssert();
	    for(int i=0; i<result.size();i++)
	    {
	    	if(result.get(i).equals("failed"))
	    	{
	    		flag=1;
	    		sh.s.log("DELETE failed for "+(i+1)+" line of text in text file");
	    		sa.assertTrue(false);
	    	}
	    }
	    if(flag==0)
	    {
	    	sh.s.log("DELETE passed for all lines of text in text file");
	    	sa.assertTrue(true);
	    }
	    sa.assertAll(); //Mandatory at last
	}
}
