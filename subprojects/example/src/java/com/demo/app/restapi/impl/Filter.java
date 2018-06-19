package com.demo.app.restapi.impl;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import software.hsharp.api.helpers.cors.CORSFilter;

@Provider
public class Filter extends CORSFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        super.filter(requestContext, responseContext);
        responseContext.getHeaders().add("X-Custom-Info", "osgi-kotlin-backend-scaffold");
    }
}
