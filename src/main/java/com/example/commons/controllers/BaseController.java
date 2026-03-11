package com.example.commons.controllers;

import com.example.commons.specs.SpecStrategy;
import com.example.commons.utils.EndpointProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BaseController {

    public RequestSpecification spec;

    public BaseController(SpecStrategy specStrategy, String serviceName) {
        this.spec = specStrategy.buildRequestSpecification(EndpointProvider.getServiceProviderForEnvironment(serviceName));
    }

    public Response callPostMethod(String endpoint, String body) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .body(body)
                .post(endpoint);
    }

    public Response callPostMethod(String endpoint) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .post(endpoint);
    }

    public Response callPostMethod(String endpoint, String body, Map<String, Object> queryParams) {
        return RestAssured.given()
                .spec(spec)
                .queryParams(queryParams)
                .when()
                .body(body)
                .post(endpoint);
    }

    public Response callPostMethodFormParams(String endpoint, Map<String, String> formParams) {
        return RestAssured.given()
                .spec(spec)
                .formParams(formParams)
                .when()
                .post(endpoint);
    }

    public Response callGetMethod(String endpoint) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .get(endpoint);
    }

    public Response callDeleteMethod(String endpoint) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .delete(endpoint);
    }

    public Response callGetMethod(String endpoint, Map<String, Object> queryParams) {
        return RestAssured.given()
                .spec(spec)
                .queryParams(queryParams)
                .when()
                .get(endpoint);
    }

    public Response callPutMethod(String endpoint, String body) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .body(body)
                .put(endpoint);
    }

    public Response callPutMethod(String endpoint, String body, Map<String, Object> queryParams) {
        return RestAssured.given()
                .spec(spec)
                .queryParams(queryParams)
                .when()
                .body(body)
                .put(endpoint);
    }

    public Response callPatchMethod(String endpoint, String body) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .body(body)
                .patch(endpoint);
    }

    public Response callPatchMethod(String endpoint, String body, Map<String, Object> queryParams) {
        return RestAssured.given()
                .spec(spec)
                .queryParams(queryParams)
                .when()
                .body(body)
                .patch(endpoint);
    }
}
