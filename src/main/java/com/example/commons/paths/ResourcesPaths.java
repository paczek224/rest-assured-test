package com.example.commons.paths;

import com.example.commons.utils.EndpointProvider;
import lombok.experimental.UtilityClass;

import java.text.MessageFormat;

@UtilityClass
public class ResourcesPaths {

    private final String RESOURCES_SERVICE_PATH = EndpointProvider.getServiceProviderForEnvironment("resources");

    public String toDosPath() {
        return MessageFormat.format("{0}/todos", RESOURCES_SERVICE_PATH);
    }

    public String postsPath() {
        return MessageFormat.format("{0}/posts", RESOURCES_SERVICE_PATH);
    }

    public String usersPath() {
        return MessageFormat.format("{0}/users", RESOURCES_SERVICE_PATH);
    }

    public String postPath(int postId) {
        return MessageFormat.format("{0}/{1}", postsPath(), postId);
    }

    public String userPath(int userId) {
        return MessageFormat.format("{0}/{1}", usersPath(), userId);
    }
}
