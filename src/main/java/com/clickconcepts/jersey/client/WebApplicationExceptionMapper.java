package com.clickconcepts.jersey.client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    public Response toResponse(WebApplicationException exception) {
        System.out.println("HERE");
        return exception.getResponse();
    }
}