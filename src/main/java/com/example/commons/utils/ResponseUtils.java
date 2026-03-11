package com.example.commons.utils;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.util.Objects;

import static com.example.commons.utils.JsonUtils.getNode;

public class ResponseUtils {

    public static <T> T map(Response response, Class<T> clazz) {
        return response.as(clazz);
    }

    public static <T> T map(Response response, TypeRef<T> clazz) {
        return response.as(clazz);
    }

    @SneakyThrows
    public static String extractString(Response response, String field) {
        JsonNode node = Objects.requireNonNull(getNode(response, field), "Field <%s> is null".formatted(field));
        return node.asText();
    }

    @SneakyThrows
    public static boolean extractBoolean(Response response, String field) {
        JsonNode node = Objects.requireNonNull(getNode(response, field), "Field <%s> is null".formatted(field));
        return node.asBoolean();
    }

    @SneakyThrows
    public static int extractInt(Response response, String field) {
        JsonNode node = Objects.requireNonNull(getNode(response, field), "Field <%s> is null".formatted(field));
        return node.asInt();
    }

    public static boolean isEmpty(Response actual) {
        String body = actual.getBody().asString();
        return body != null && (body.isBlank() || body.equals("{}"));
    }
}
