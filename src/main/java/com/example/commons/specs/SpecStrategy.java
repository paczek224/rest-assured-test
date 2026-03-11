package com.example.commons.specs;

import io.restassured.specification.RequestSpecification;

public interface SpecStrategy {

    RequestSpecification buildRequestSpecification(String baseUri);
}
