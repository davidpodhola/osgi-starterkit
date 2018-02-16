package com.demo.app.restapi.impl;


import bla1.Bla1ImplKt;

import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("status")
public class Status {

    @GET
    @Produces("text/plain")
    public String getStatus() {
    	LocalDateTime now = LocalDateTime.now();
        return "This is active Java code with server time " + now + " source from 11:43 with " + Bla1ImplKt.blablabla();
    }
}
