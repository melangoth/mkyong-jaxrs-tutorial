package com.develrage.tutorials.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRestService {

    @GET
    public Response getUser() {
        StringBuilder output = new StringBuilder();

        output.append("getUser is called");
        Helper.appendAppVersion(output);

        return Response.status(200).entity(output.toString()).build();
    }

    @GET
    @Path("{name}")
    public Response getUserByName(@PathParam("name") String name) {
        StringBuilder output = new StringBuilder();

        output.append("getUserByName is called");
        Helper.appendAppVersion(output);

        return Response.status(200).entity(output.toString()).build();
    }

    @GET
    @Path("/vip")
    public Response getUserVip() {
        StringBuilder output = new StringBuilder();

        output.append("getUserVip is called");
        Helper.appendAppVersion(output);

        return Response.status(200).entity(output.toString()).build();
    }
}
