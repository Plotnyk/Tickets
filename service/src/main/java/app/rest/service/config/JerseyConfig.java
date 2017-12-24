package app.rest.service.config;

import app.config.ComponentFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * REST web-service configuration for Jersey
 * @author Plotnyk
 * */
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        super(ComponentFeature.class);
        packages("app.rest");
    }
}
