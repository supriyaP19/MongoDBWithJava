package com.demo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardMongoDBMicroserviceApplication extends Application<DropwizardMongoDBMicroserviceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardMongoDBMicroserviceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardMongoDBMicroservice";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardMongoDBMicroserviceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardMongoDBMicroserviceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
