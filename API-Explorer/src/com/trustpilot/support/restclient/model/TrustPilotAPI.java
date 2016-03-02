package com.trustpilot.support.restclient.model;

public class TrustPilotAPI
{
    private String name;      
	private String syntax;
    private String description;
    private String docLink;
    private String[] placeholders;
    private String type; //GET, POST, DELETE ?

    public TrustPilotAPI (String name, String apiSyntax, String description, String docLink, String type)
    {
    	if(name == null || name.isEmpty() || apiSyntax == null || apiSyntax.isEmpty() || docLink == null || docLink.isEmpty()) {
    		throw new RuntimeException("Invalid Trustpilot API Registration Attempt");
    	}
    		
        this.name = name;
        this.syntax = apiSyntax;
        this.description = description;
        this.docLink = docLink;
        this.type = type;
        //TODO: Compute placeholders and keep ready
    }
    

    
    private int[] countTotalParam(String syntax) {

    	String findStr = "}";
    	int lastIndex = 0;
    	int count = 0;

    	while(lastIndex != -1){

    	       lastIndex = syntax.indexOf(findStr,lastIndex);

    	       if( lastIndex != -1){
    	             count ++;
    	             lastIndex+=findStr.length();
    	      }
    	}
    	System.out.println(count);
    	return null;
    }
    
    public String getType() {
		return type;
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String apiSyntax) {
		this.syntax = apiSyntax;
	}

	public String getDocLink() {
		return docLink;
	}

	public void setDocLink(String docLink) {
		this.docLink = docLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
