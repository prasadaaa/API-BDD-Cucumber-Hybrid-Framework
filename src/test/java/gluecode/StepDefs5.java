package gluecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojos.MyPojo;
import utilities.ExcelFilesUtility;

public class StepDefs5 
{
	Shared sh;
	ArrayList<String> result;
	public StepDefs5(Shared x)
	{
		this.sh=x;
		result=new ArrayList<String>();
	}
	
	@When("submit request for id {int} via PUT method")
	public void method1(int id,DataTable dt)
	{
		List<Map<String,String>> l=dt.asMaps();
		String temp=l.get(0).get("data");
		String[] pieces=temp.split(",");
		MyPojo obj=new MyPojo();
		obj.setTitle(pieces[0].trim());
		obj.setBody(pieces[1].trim());
		obj.setUserId(Integer.parseInt(pieces[2].trim()));
		sh.req.body(obj);
		sh.res=sh.req.put(""+id);
	}

	@When("submit request via PUT to service by taking data from excel file")
	public void method2() throws Exception
	{
		////connect to excel file
		ExcelFilesUtility eu=new ExcelFilesUtility("src\\test\\resources\\sources\\testdata.xlsx");
		eu.openSheet("Sheet1");
		int nour=eu.getRowsCount();
		int nouc=eu.getColumnscount();
		//create result column
		eu.createResultColumn(nouc);
		//Data driven(from 2nd row(index is 1), because 1st row has names of columns)
		for(int i=1;i<nour;i++)
		{
			//put data to be updated in JSON format for request body parameters
			MyPojo obj=new MyPojo();
			obj.setTitle(eu.getCellValue(i,1));
			obj.setBody(eu.getCellValue(i,2));
			try
			{
				obj.setUserId(Integer.parseInt(eu.getCellValue(i,3)));
			}
			catch(Exception ex)
			{
				obj.setUserId(0);
			}
			sh.req.header("Content-Type","application/json");
			sh.req.body(obj); 
			//submit request through PUT method
			sh.res=sh.req.put(eu.getCellValue(i,0)); //id as path parameter
			//Validate response
			if(sh.res.statusCode()==200 && eu.getCellValue(i,4).equals("allValid"))
			{
				result.add("passed");
				eu.setCellValue(i,nouc,sh.s.getName()+" is passed");
			}
			else if(sh.res.statusCode()==404 && eu.getCellValue(i,4).equals("wrongID"))
			{
				result.add("passed");
				eu.setCellValue(i,nouc,sh.s.getName()+" is passed");
			}
			else if(sh.res.statusCode()==400 && eu.getCellValue(i,4).equals("wrongUserID"))
			{
				result.add("passed");
				eu.setCellValue(i,nouc,sh.s.getName()+" is passed");
			}
			else
			{
				result.add("failed");
				eu.setCellValue(i,nouc,sh.s.getName()+" is failed");
			}
		} 
		//save and close excel
		eu.saveAndCloseExcel();
	}
    @Then("validate response as per data in excel file")
    public void method3()
	{
    	int flag=0;
		SoftAssert sa=new SoftAssert();
	    for(int i=0; i<result.size();i++)
	    {
	    	if(result.get(i).equals("failed"))
	    	{
	    		flag=1;
	    		sh.s.log("UPDATE failed for "+(i+1)+" line of data in excel file");
	    		sa.assertTrue(false);
	    	}
	    }
	    if(flag==0)
	    {
	    	sh.s.log("UPDATE passed for all lines of data in excel file");
	    	sa.assertTrue(true);
	    }
	    sa.assertAll(); //mandatory at end
	}
}





