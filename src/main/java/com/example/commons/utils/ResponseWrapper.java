package com.example.commons.utils;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Optional;

public class ResponseWrapper<E> {

    @Getter
    private final Response response;
    private final E entity;

    public ResponseWrapper(Response response, TypeRef<E> typeRef) {
        this.response = response;
        this.entity = ResponseUtils.isEmpty(response) ? null : response.as(typeRef);
    }

    public int statusCode() {
        return response.statusCode();
    }

    public Optional<E> getEntity() {
        return Optional.ofNullable(entity);
    }

    public E getOrElseThrow() {
        return getEntity().orElseThrow(() -> new IllegalStateException("Entity is not present"));
    }
}
