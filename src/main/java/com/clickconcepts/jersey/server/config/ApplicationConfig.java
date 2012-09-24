package com.clickconcepts.jersey.server.config;

import com.sun.jersey.api.core.PackagesResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/server")
public class ApplicationConfig extends PackagesResourceConfig {

    public ApplicationConfig() {
        super("com.clickconcepts.jersey.server.resources");

    }
}