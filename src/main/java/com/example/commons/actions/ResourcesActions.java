package com.example.commons.actions;

import com.example.commons.controllers.ResourcesController;
import com.example.commons.paths.ResourcesPaths;
import com.example.commons.specs.DefaultSpecStrategy;
import com.example.commons.utils.ResponseWrapper;
import com.example.commons.utils.Serialize;
import com.example.models.Post;
import com.example.models.User;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;

public class ResourcesActions extends BaseActions {

    public ResourcesActions() {
        super(new ResourcesController(new DefaultSpecStrategy()));
    }

    public ResponseWrapper<Post> addPost(Post post) {
        Response response = callPostMethod(ResourcesPaths.postsPath(), Serialize.toJson(post));
        return wrapResponse(response, new TypeRef<>() { });
    }

    public ResponseWrapper<Post> deletePost(int postId) {
        Response response = callDeleteMethod(ResourcesPaths.postPath(postId));
        return wrapResponse(response, new TypeRef<>() { });
    }

    public ResponseWrapper<List<Post>> getAllPosts() {
        Response response = callGetMethod(ResourcesPaths.postsPath());
        return wrapResponse(response, new TypeRef<>() { });
    }

    public ResponseWrapper<Post> getPostById(int postId) {
        Response response = callGetMethod(ResourcesPaths.postPath(postId));
        return wrapResponse(response, new TypeRef<>() { });
    }

    public ResponseWrapper<User> getUsers() {
        Response response = callGetMethod(ResourcesPaths.usersPath());
        return wrapResponse(response, new TypeRef<>() { });
    }

    public ResponseWrapper<User> getUser(int userId) {
        Response response = callGetMethod(ResourcesPaths.userPath(userId));
        return wrapResponse(response, new TypeRef<>() { });
    }

    private <E> ResponseWrapper<E> wrapResponse(Response response, TypeRef<E> typeRef) {
        return new ResponseWrapper<>(response, typeRef);
    }
}
