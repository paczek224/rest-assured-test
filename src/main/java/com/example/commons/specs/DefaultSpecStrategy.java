package com.example.commons.specs;

import com.example.commons.utils.EndpointProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DefaultSpecStrategy extends BaseSpec implements SpecStrategy {

    public DefaultSpecStrategy() {
        super(List.of(
                EndpointProvider.getDefaultRequestLoggingFilterDetail(),
                EndpointProvider.getDefaultResponseLogger()));
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
