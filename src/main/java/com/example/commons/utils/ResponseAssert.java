package com.example.commons.utils;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssert;

import java.util.List;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response> {

    public ResponseAssert(Response actual) {
        super(actual, ResponseAssert.class);
    }

    public static ResponseAssert assertThat(Response response) {
        return new ResponseAssert(response);
    }
    
    public <T> ObjectAssert<T> as(Class<T> clazz) {
        return Assertions.assertThat(extract(clazz));
    }

    public <T> ListAssert<T> asList(Class<T> clazz) {
        return asList(".", clazz);
    }

    public <T> ListAssert<T> asList(String path, Class<T> clazz) {
        return Assertions.assertThat(actual.jsonPath().getList(path, clazz));
    }

    public <T> T extract(Class<T> clazz) {
        isNotNull();
        return actual.as(clazz);
    }

    public <T> List<T> extractList(Class<T> clazz) {
        isNotNull();
        return actual.jsonPath().getList(".", clazz);
    }

    public ResponseAssert hasStatus(int expectedStatus) {
        isNotNull();

        int actualStatus = actual.statusCode();

        if (actualStatus != expectedStatus) {
            failWithMessage("Expected status code <%s> but was <%s>", expectedStatus, actualStatus);
        }

        return this;
    }

    public ResponseAssert hasNoBody() {
        isNotNull();

        if (!ResponseUtils.isEmpty(actual)) {
            failWithMessage("Expected response body to be empty but was <%s>", actual.getBody().asString());
        }

        return this;
    }

    public ResponseAssert hasStringField(String field, String expected) {
        String actualValue = ResponseUtils.extractString(actual, field);

        if (!actualValue.equals(expected)) {
            failWithMessage(
                    "Expected <%s> to be <%s> but was <%s>",
                    field,
                    expected,
                    actualValue
            );
        }

        return this;
    }

    public ResponseAssert hasBooleanField(String field, boolean expected) {
        boolean actualValue = ResponseUtils.extractBoolean(actual, field);

        if (actualValue != expected) {
            failWithMessage(
                    "Expected <%s> to be <%s> but was <%s>",
                    field,
                    expected,
                    actualValue
            );
        }

        return this;
    }

    public ResponseAssert hasNumberField(String field, int expected) {
        int actualValue = ResponseUtils.extractInt(actual, field);

        if (actualValue != expected) {
            failWithMessage(
                    "Expected <%s> to be <%s> but was <%s>",
                    field,
                    expected,
                    actualValue
            );
        }

        return this;
    }
}
