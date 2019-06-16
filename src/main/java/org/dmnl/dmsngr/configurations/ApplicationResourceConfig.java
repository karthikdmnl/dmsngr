package org.dmnl.dmsngr.configurations;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationResourceConfig extends ResourceConfig {

    public ApplicationResourceConfig() {
        register(new ApplicationAbstractBinder());
        packages(true, "org.dmnl.dmsngr");
    }
}
