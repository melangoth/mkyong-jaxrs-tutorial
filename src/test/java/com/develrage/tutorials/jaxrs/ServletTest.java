package com.develrage.tutorials.jaxrs;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by develrage
 */
public class ServletTest {
    public static final String TEST_HOST = "http://localhost";
    public static final int TEST_PORT = 8081;
    public static final String TEST_PATH = "/jaxrs-tutorial";
    public static final String TEST_USERS_SERVICE = "/users-service";
    public static final String TEST_HELLO_SERVICE = "/hello-service";
    static final Logger log = LoggerFactory.getLogger(ServletTest.class);
    private static Server server;

    public static void main(String[] args) throws Exception {
        startEmbeddedServer();
    }

    @BeforeClass
    public static void startEmbeddedServer() throws Exception {
        log.info("Starting Embedded Jetty...");
        // resource config
        ResourceConfig usersResourceConfig = new ResourceConfig(UserRestService.class);
        ResourceConfig helloResourceConfig = new ResourceConfig(HelloWorldService.class);

        // servlets
        ServletHolder usersServlet = new ServletHolder(new ServletContainer(usersResourceConfig));
        ServletHolder helloServlet = new ServletHolder(new ServletContainer(helloResourceConfig));

        // embedded server, root path with one resourceconfig
//        URI baseUri = UriBuilder.fromUri(TEST_HOST).port(TEST_PORT).build();
//        server = JettyHttpContainerFactory.createServer(baseUri, config);

        // embedded server, multiple resourceconfig on desired paths
        server = new Server(TEST_PORT);
        ServletContextHandler context = new ServletContextHandler(server, String.format("%s/*", TEST_PATH));
        context.addServlet(usersServlet, String.format("%s/*", TEST_USERS_SERVICE));
        context.addServlet(helloServlet, String.format("%s/*", TEST_HELLO_SERVICE));

        server.start();
        //server.join();
        log.info("Started Embedded Jetty.");
    }

    @Test
    public void junitTest() {
        log.info("Junit Test!");
        assert true;
    }

    @Test
    public void helloTest() throws Exception {
        log.info("Hello JAXRS Tutorial Test!");
        URL url = new URL(String.format("%s:%d%s%s/hello/mgh", TEST_HOST, TEST_PORT, TEST_PATH, TEST_HELLO_SERVICE));
        HttpURLConnection conn = null;

        log.info(String.format("Sending resquest to: %s", url.toString()));
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");

            log.debug(String.format("Response code: %d", conn.getResponseCode()));
            assert conn.getResponseCode() == 200;

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output.append("\n" + line);
            }

            log.debug(String.format("\n--- Server response content: ---%s\n---", output));
            assert output.toString().contains("So nice!");
        } finally {
            conn.disconnect();
        }
    }
}
