package com.example.commons.utils;

import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import lombok.experimental.UtilityClass;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import static io.restassured.filter.log.ResponseLoggingFilter.logResponseIfStatusCodeMatches;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@UtilityClass
public class EndpointProvider {

    public String getServiceProviderForEnvironment(String service) {
        String serviceProvider = MessageFormat.format("{0}.{1}", getEnv(), service);
        return ResourceBundle.getBundle("serviceProvider").getString(serviceProvider);
    }

    public String getEnv() {
        return getEnvironmentProvider().getString("environment");
    }

    public Integer connectionTimeoutInMilliseconds() {
        return Integer.parseInt(getEnvironmentProvider().getString("http.connection.timeout.ms"));
    }

    public Integer socketTimeoutInMilliseconds() {
        return Integer.parseInt(getEnvironmentProvider().getString("http.socket.timeout.ms"));
    }

    public Filter getDefaultRequestLoggingFilterDetail() {
        return new RequestLoggingFilter(LogDetail.valueOf(getEnvironmentProvider().getString("defaultRequestLoggingFilterDetail")));
    }

    public Filter getDefaultResponseLogger() {
        int statusCode = Integer.parseInt(getEnvironmentProvider().getString("defaultResponseLoggingFilterMinStatusCode"));
        return logResponseIfStatusCodeMatches(greaterThanOrEqualTo(statusCode));
    }

    private static ResourceBundle getEnvironmentProvider() {
        return ResourceBundle.getBundle("environmentProvider");
    }
}
