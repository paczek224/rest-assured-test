package com.example.commons.utils;

import lombok.experimental.UtilityClass;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@UtilityClass
public class EndpointProvider {

    public String getServiceProviderForEnvironment(String service) {
        String serviceProvider = MessageFormat.format("{0}.{1}", getEnvironmentProvider(), service);
        return ResourceBundle.getBundle("serviceProvider").getString(serviceProvider);
    }

    public String getEnvironmentProvider() {
        return ResourceBundle.getBundle("environmentProvider").getString("environment");
    }
}
