package com.example.commons.specs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.mapper.ObjectMapperType;
import lombok.Data;

import java.util.List;

@Data
public abstract class BaseSpec {

    protected final List<Filter> filters;

    public RequestSpecBuilder getRequestSpecBuilder(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addFilters(filters)
                .setConfig(getBaseConfig());
    }

    public RestAssuredConfig getBaseConfig() {
        return RestAssuredConfig.config()
                .objectMapperConfig(getObjectMapperConfig())
                .logConfig(LogConfig.logConfig().enablePrettyPrinting(false));
    }

    private ObjectMapperConfig getObjectMapperConfig() {
        return new ObjectMapperConfig(ObjectMapperType.JACKSON_2).jackson2ObjectMapperFactory(
                (x, y) -> {
                    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    return objectMapper;
                }
        );
    }
}
