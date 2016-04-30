package com.develrage.tutorials.rest;

import java.io.IOException;
import java.util.Properties;

public class Helper {
    public static void appendAppVersion(StringBuilder output) {
        output.append(String.format("\nAppVersion: %s", getAppVersion()));
    }

    private static String getAppVersion() {
        String appversion = "app.version.not-found";

        final Properties props = new Properties();
        try {
            props.load(Helper.class.getClassLoader().getResourceAsStream("appversion.properties"));
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
