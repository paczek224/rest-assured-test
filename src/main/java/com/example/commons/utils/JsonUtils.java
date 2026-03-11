package com.example.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getNode(Response actual, String field) throws JsonProcessingException {
            JsonNode json = mapper.readTree(actual.getBody().asString());
            JsonNode node = json.at("/" + field.replace(".", "/"));
            return node.isMissingNode() ? null : node;
    }
}
