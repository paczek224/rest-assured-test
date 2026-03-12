package com.example.commons.utils;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;

public class ResponseAssert<T> extends AbstractAssert<ResponseAssert<T>, ResponseWrapper<T>> {

    public ResponseAssert(ResponseWrapper<T> actual) {
        super(actual, ResponseAssert.class);
    }

    public static <T> ResponseAssert<T> assertThat(ResponseWrapper<T> response) {
        return new ResponseAssert<>(response);
    }

    public  ObjectAssert<T> toObjectAssert() {
        return Assertions.assertThat(actual.getOrElseThrow());
    }

    public ResponseAssert<T> hasStatus(int expectedStatus) {
        isNotNull();

        int actualStatus = actual.statusCode();

        if (actualStatus != expectedStatus) {
            failWithMessage("Expected status code <%s> but was <%s>", expectedStatus, actualStatus);
        }

        return this;
    }

    public ResponseAssert<T> hasNoBody() {
        isNotNull();

        if (!ResponseUtils.isEmpty(actual.getResponse())) {
            failWithMessage("Expected response body to be empty but was <%s>", actual.getResponse().getBody().asString());
        }

        return this;
    }

    public ResponseAssert<T> hasStringField(String field, String expected) {
        String actualValue = ResponseUtils.extractString(actual.getResponse(), field);

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

    public ResponseAssert<T> hasBooleanField(String field, boolean expected) {
        boolean actualValue = ResponseUtils.extractBoolean(actual.getResponse(), field);

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

    public ResponseAssert<T> hasNumberField(String field, int expected) {
        int actualValue = ResponseUtils.extractInt(actual.getResponse(), field);

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
