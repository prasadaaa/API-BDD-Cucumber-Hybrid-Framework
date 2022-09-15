package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyPojo
{
	//Private properties
	@JsonProperty("userId") 
    private int uid;
    
    @JsonProperty("title") 
    private String t;
    
    @JsonProperty("body") 
    private String b;
    
    @JsonProperty("id") 
    private int i;
    
    //public setters for fields in Request payload
    public void setUserId(int x)
    { 
		 this.uid=x; 
	} 
    
    public void setTitle(String x) 
    { 
		 this.t=x; 
	} 
   
    public void setBody(String x) 
    { 
		 this.b=x; 
	} 
    
    //public getters for fields in Response payload
    public int getUserId()
    { 
		 return(this.uid); 
	} 
    
    public String getTitle() 
    { 
    	return(this.t); 
	} 
   
    public String getBody() 
    { 
    	return(this.b); 
	} 
    
    public int getId() 
    { 
    	return(this.i); 
	}
}
