package org.brandt.camelTest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;


/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
    	//errorHandler(deadLetterChannel("bean:errorHandler?method=printError"));
    	errorHandler(deadLetterChannel("mock:error"));
        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
    	/*
        from("file:src/data?noop=true")        
        	    .choice()
                .when(xpath("/person/city = 'London'"))
                    .log("UK message")
                    .to("file:target/messages/uk")
                .otherwise()
                    .log("Other message")
                    .to("file:target/messages/others");
        */   	
    	
        CsvDataFormat csv = new CsvDataFormat();
        csv.setDelimiter(";");
        from("file:src/csv?noop=true")        	
        	.unmarshal(csv)
        	.to("bean:entryhandler?method=printContent")
        	.log("${body}")
        	.to("direct:a");     
        
        from("direct:a")
         .log("${body}");
        
     
     
    }

}
