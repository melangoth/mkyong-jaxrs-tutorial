package com.develrage.tutorials.jaxrs;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by develrage
 */
public class ServletTest {
    static final Logger log = LoggerFactory.getLogger(ServletTest.class);
    private static Server server;

    @BeforeClass
    public static void startEmbeddedServer() throws Exception {
        log.info("Starting Embedded Jetty...");
        // resource config
        ResourceConfig config = new ResourceConfig(UserRestService.class);

        // embedded server
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8081).build();
        server = JettyHttpContainerFactory.createServer(baseUri, config);

        server.start();
        //server.join();
        log.info("Started Embedded Jetty.");
    }

    @Test
    public void helloTest() throws InterruptedException {
        log.info("Hello JAXRS Tutorial Test!");
        assert true;
    }
}
