package app.config;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Register DI bindings
 * @author Plotnyk
 * */
public class ComponentFeature implements Feature {
    @Override
    public boolean configure(FeatureContext featureContext) {
        featureContext.register(new ComponentBinder());
        return true;
    }
}
