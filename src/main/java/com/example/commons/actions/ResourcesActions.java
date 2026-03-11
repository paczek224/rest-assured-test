package com.example.commons.actions;

import com.example.commons.controllers.ResourcesController;
import com.example.commons.paths.ResourcesPaths;
import com.example.commons.specs.DefaultSpecStrategy;
import com.example.commons.utils.Serialize;
import com.example.models.Post;
import io.restassured.response.Response;

public class ResourcesActions extends BaseActions {

    public ResourcesActions() {
        super(new ResourcesController(new DefaultSpecStrategy()));
    }

    public Response addPost(Post post) {
        return callPostMethod(ResourcesPaths.postsPath(), Serialize.toJson(post));
    }

    public Response deletePost(int postId) {
        return callDeleteMethod(ResourcesPaths.postPath(postId));
    }

    public Response getAllPosts() {
        return callGetMethod(ResourcesPaths.postsPath());
    }

    public Response getPostById(int postId) {
        return callGetMethod(ResourcesPaths.postPath(postId));
    }

    public Response getUsers() {
        return callGetMethod(ResourcesPaths.usersPath());
    }

    public Response getUser(int userId) {
        return callGetMethod(ResourcesPaths.userPath(userId));
    }
}
