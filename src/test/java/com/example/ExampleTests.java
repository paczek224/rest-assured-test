package com.example;

import com.example.commons.actions.ResourcesActions;
import com.example.commons.utils.ResponseAssert;
import com.example.commons.utils.ResponseUtils;
import com.example.models.Post;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExampleTests {

    private final ResourcesActions resourcesActions = new ResourcesActions();

    @Test
    public void shouldBeAbleToAddPosts() {
        //given
        List<Post> posts = ResponseUtils.map(resourcesActions.getAllPosts(), new TypeRef<>() { });

        //when
        Post requestBody = Post.builder().build();
        Response response = resourcesActions.addPost(requestBody);

        //then
        ResponseAssert.assertThat(response)
                .hasStatus(201)
                .hasStringField("title", requestBody.getTitle())
                .hasStringField("body", requestBody.getBody())
                .hasNumberField("userId", requestBody.getUserId())
                .hasNumberField("id", posts.size() + 1);

    }

    @Test
    public void shouldBeAbleToDeletePosts() {
        //given
        Integer createdPostId = ResponseUtils.map(resourcesActions.addPost(Post.builder().build()), Post.class).getId();

        //when
        Response response = resourcesActions.deletePost(createdPostId);

        //then
        ResponseAssert.assertThat(response)
                .hasStatus(200)
                .hasNoBody();

        //and
        ResponseAssert.assertThat(resourcesActions.getPostById(createdPostId))
                .hasStatus(404);

        //and
        List<Post> allPosts = ResponseUtils.map(resourcesActions.getAllPosts(), new TypeRef<>() { });
        Assertions.assertThat(allPosts)
                .withFailMessage("Post with id <%s> should be deleted", createdPostId)
                .noneMatch(post -> createdPostId.equals(post.getId()));
    }
}
