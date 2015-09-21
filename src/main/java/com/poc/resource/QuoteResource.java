package com.poc.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.poc.error.ErrorBean;
import com.poc.model.Quote;




@Path("/quote")
@Produces(MediaType.APPLICATION_JSON)
public class QuoteResource {
	
	public final static Map<String, Quote> quotes = new TreeMap<String, Quote>();

	public static final String SENIOR_GROUP="SeniorGroup";
	
	public static final String SMALL_GROUP="SmallGroup";
	
	public static final String MEDIUM_GROUP="MediumGroup";
	
	public static final String LARGE_GROUP="LargeGroup";
	
	
	static {
		
		quotes.put("1", new Quote("1", SENIOR_GROUP,"2500","123"));
		quotes.put("2", new Quote("2", SMALL_GROUP,"1200","123"));
		quotes.put("3", new Quote("3", MEDIUM_GROUP,"455","123"));
		quotes.put("4", new Quote("4", LARGE_GROUP,"455","123"));

		quotes.put("5", new Quote("5", SENIOR_GROUP,"2500","456"));
		quotes.put("6", new Quote("6", SMALL_GROUP,"455","456"));
		quotes.put("7", new Quote("7", MEDIUM_GROUP,"455","456"));
		
		quotes.put("8", new Quote("8", SENIOR_GROUP,"455","789"));
		quotes.put("9", new Quote("9", MEDIUM_GROUP,"455","789"));
		
	}


	 private final String template;
	 
	 public QuoteResource(String template) {
	        this.template = template;
	    }
	 
    @GET
    @Path("/{id}")
    public Response getQuoteDetails(@PathParam("id") String quoteId) {
    	System.out.println("Received quoteId: " +quoteId);
    	
    	if(quotes.containsKey(quoteId)){
    		return Response.status(Response.Status.OK).entity(quotes.get(quoteId)).build();
    	}else{
    		return 	Response.status(Response.Status.NOT_FOUND).entity(new ErrorBean("001","Invalid Quote!")).build();
    				
    	}
    }

    

    
    @GET
    public Collection<Quote> listQuotes() {
    	return quotes.values();	 
    }
	
    
    
    @GET
    @Path("/all/agent/{id}")
    public   Collection<Quote> getQuotesByAgentId(@PathParam("id") String agentId) {
    	
    	return getQuotesForAgentId(agentId);
    	
    }
    
    
    private Collection<Quote>  getQuotesForAgentId(String agentId) {

    	 List<Quote> agentQuotes = new ArrayList<Quote>();
    	 
    	//loop a Map
    		for (Map.Entry<String, Quote> entry : quotes.entrySet()) {
    			
    			if(agentId.equalsIgnoreCase(entry.getValue().getAgentId())){
    				agentQuotes.add(entry.getValue());
    			}
    		} 
    	 return agentQuotes;
	}

	@GET
	@Path("/sg/agent/{id}")
    public  Collection<Quote> getSmallGroupsByAgentId(@PathParam("id") String agentId) {
    
		return getSmallGroupsQuotesForAgentId(agentId,SMALL_GROUP);
	}
    
	
	
	@GET
	@Path("/med/agent/{id}")
    public Collection<Quote> getMediumGroupQuotesByAgentId(@PathParam("id") String agentId) {
		return getMediumGroupsQuotesForAgentId(agentId,MEDIUM_GROUP); 
    }
    
    @GET
    @Path("/agent/{id}")
    public Collection<Quote> getQuotesByQuoteTypeAndAgentId(@QueryParam("type") String type,@PathParam("id") String agentId) {
    	return getByType(agentId,type); 
    }
	
    
    private Collection<Quote> getSmallGroupsQuotesForAgentId(String agentId,String type) {
	
    	return getByType(agentId,type);
	}

    private Collection<Quote>getMediumGroupsQuotesForAgentId(String agentId,String type) {
    	
    	return getByType(agentId,type);
	}
    
   /* private Collection<Quote> getLargeGroupsQuotesForAgentId(String agentId,String type) {
    	
    	return getByType(agentId,type);
	}
    
    
    private Collection<Quote> getSeniorQuotesForAgentId(String agentId) {
    	
    	return getByType(agentId,"Senior");
	}
    */
    private  Collection<Quote> getByType(String agentId, String type){
    	
    	 List<Quote> agentQuotes = new ArrayList<Quote>();
    	 
      	//loop a Map
      		for (Map.Entry<String, Quote> entry : quotes.entrySet()) {
      			
      			if(agentId.equalsIgnoreCase(entry.getValue().getAgentId()) && entry.getValue().getQuoteType().equalsIgnoreCase(type)){
      				agentQuotes.add(entry.getValue());	
      			}
      		} 
      		
      		return agentQuotes;
    }
    
	
    
}
