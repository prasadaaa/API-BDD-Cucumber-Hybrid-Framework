package gluecode;

import io.cucumber.java.en.Given;

public class Dummy
{
	Shared sh;
	public Dummy(Shared x)
	{
		this.sh=x;
	}
	
	@Given("I want to take a value")
	public void method1()
	{
		sh.name="abdul kalam";
	}
	
	@Given("I want to write a value")
	public void method2()
	{
		System.out.println(sh.name);
	}
	
	

}
