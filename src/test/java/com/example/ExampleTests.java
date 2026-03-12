package com.example;

import com.example.commons.actions.ResourcesActions;
import com.example.commons.utils.ResponseAssert;
import com.example.models.Post;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExampleTests {

    private final ResourcesActions resourcesActions = new ResourcesActions();

    @Test
    public void shouldBeAbleToAddPosts() {
        //given
        List<Post> posts = ResponseAssert.assertThat(resourcesActions.getAllPosts())
                .hasStatus(200)
                .extractList(Post.class);

        //when
        Post requestBody = Post.builder().build();
        Response response = resourcesActions.addPost(requestBody);

        //then
        ResponseAssert.assertThat(response)
                .hasStatus(201)
                .hasStringField("title", requestBody.getTitle())
                .hasStringField("body", requestBody.getBody())
                .hasNumberField("userId", requestBody.getUserId())
                .as(Post.class)
                .describedAs("Number of posts should be increased by 1")
                .extracting(Post::getId).isEqualTo(posts.size() + 1);
    }

    @Test
    public void shouldBeAbleToDeletePosts() {
        //given
        Integer createdPostId = ResponseAssert.assertThat(resourcesActions.addPost(Post.builder().build()))
                .extract(Post.class)
                .getId();

        //when
        Response response = resourcesActions.deletePost(createdPostId);

        //then
        ResponseAssert.assertThat(response)
                .hasStatus(200)
                .hasNoBody();

        //and
        ResponseAssert.assertThat(resourcesActions.getPostById(createdPostId))
                .hasStatus(404)
                .hasNoBody();

        //and
        ResponseAssert.assertThat(resourcesActions.getAllPosts())
                .hasStatus(200)
                .asList(Post.class)
                .withFailMessage("Post with id <%s> is NOT deleted", createdPostId)
                .noneMatch(post -> createdPostId.equals(post.getId()))
                .as("All posts ids should be unique")
                .map(Post::getId)
                .doesNotHaveDuplicates();
    }
}
