package org.zhadaev.vdcomtest.webapp.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.zhadaev.vdcomtest.webapp.model.Person;

@Component
public class RestMvcConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Person.class);
        config.setDefaultPageSize(1000);
    }
}
