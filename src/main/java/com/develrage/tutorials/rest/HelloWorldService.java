package com.develrage.tutorials.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Properties;

@Path("/hello")
public class HelloWorldService {

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        String output = String.format("Jersey say: %s!\nSo nice!\nApp version: %s", msg, getAppVersion());

        return Response.status(200).entity(output).build();
    }

    private String getAppVersion() {
        String appversion = "app.version.not-found";

        final Properties props = new Properties();
        try {
            props.load(HelloWorldService.class.getClassLoader().getResourceAsStream("appversion.properties"));
            if (props.containsKey("appversion")) {
                appversion = props.getProperty("appversion");
            }
        } catch (IOException ioe) {
            appversion = "IOException";
        } catch (NullPointerException npe) {
            appversion = "NullPointerException";
        } catch (Exception e) {
            appversion = "Exception";
        }

        return appversion;
    }
}
