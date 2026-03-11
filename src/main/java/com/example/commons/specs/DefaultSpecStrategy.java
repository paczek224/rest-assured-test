package com.example.commons.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hamcrest.Matchers;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DefaultSpecStrategy extends BaseSpec implements SpecStrategy {

    public DefaultSpecStrategy() {
        super(List.of(
                new RequestLoggingFilter(LogDetail.URI),
                ResponseLoggingFilter.logResponseIfStatusCodeMatches(Matchers.greaterThanOrEqualTo(500))));
    }

    public DefaultSpecStrategy(List<Filter> filters) {
        super(filters);
    }

    @Override
    public RequestSpecification buildRequestSpecification(String baseUri) {
        RequestSpecBuilder requestSpecBuilder = getRequestSpecBuilder(baseUri)
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setUrlEncodingEnabled(false);
        return requestSpecBuilder.build();
    }
}
