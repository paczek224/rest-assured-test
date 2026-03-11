package com.example.commons.actions;

import com.example.commons.controllers.BaseController;
import com.example.commons.specs.SpecStrategy;
import io.restassured.response.Response;

import java.util.Map;

public class BaseActions {
    private final BaseController controller;

    public BaseActions(BaseController controller) {
        this.controller = controller;
    }

    public BaseActions(SpecStrategy specStrategy, String serviceName) {
        controller = new BaseController(specStrategy, serviceName);
    }

    public Response callPostMethod(String endpoint, String body) {
        return controller.callPostMethod(endpoint, body);
    }

    public Response callPostMethod(String endpoint) {
        return controller.callPostMethod(endpoint);
    }

    public Response callPostMethod(String endpoint, String body, Map<String, Object> queryParams) {
        return controller.callPostMethod(endpoint, body, queryParams);
    }

    public Response callPostMethod(String endpoint, Map<String, String> formParams) {
        return controller.callPostMethodFormParams(endpoint, formParams);
    }

    public Response callGetMethod(String endpoint) {
        return controller.callGetMethod(endpoint);
    }

    public Response callDeleteMethod(String endpoint) {
        return controller.callDeleteMethod(endpoint);
    }

    public Response callGetMethod(String endpoint, Map<String, Object> queryParams) {
        return controller.callGetMethod(endpoint, queryParams);
    }
}
