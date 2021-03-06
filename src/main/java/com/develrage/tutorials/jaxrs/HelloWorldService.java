package com.develrage.tutorials.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        StringBuilder output = new StringBuilder();

        output.append(String.format("Jersey say: %s!\nSo nice!", msg));

        Helper.appendAppVersion(output);

        return Response.status(200).entity(output.toString()).build();
    }
}
