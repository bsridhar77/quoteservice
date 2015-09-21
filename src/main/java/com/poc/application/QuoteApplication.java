package com.poc.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.poc.resource.QuoteResource;


public class QuoteApplication extends Application<QuoteConfiguration> {
	    public static void main(String[] args) throws Exception {
	        new QuoteApplication().run(args);
	    }

	    @Override
	    public String getName() {
	        return "AgentApplication";
	    }

	    @Override
	    public void initialize(Bootstrap<QuoteConfiguration> bootstrap) {
	        // nothing to do yet
	    }

	    @Override
	    public void run(QuoteConfiguration configuration,
	                    Environment environment) {
	        final QuoteResource resource = new QuoteResource( configuration.getTemplate());
	        environment.jersey().register(resource);
	    }
	
	
}
