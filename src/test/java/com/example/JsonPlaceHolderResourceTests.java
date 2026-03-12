package com.example;

import com.example.commons.actions.ResourcesActions;
import com.example.commons.utils.ResponseAssert;
import com.example.commons.utils.ResponseWrapper;
import com.example.models.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JsonPlaceHolderResourceTests {

    private final ResourcesActions resourcesActions = new ResourcesActions();

    @Test
    public void shouldBeAbleToAddPosts() {
        //given
        List<Post> posts = resourcesActions.getAllPosts().getOrElseThrow();

        //when
        Post requestBody = Post.builder().build();
        ResponseWrapper<Post> response = resourcesActions.addPost(requestBody);

        //then
        ResponseAssert.assertThat(response)
                .hasStatus(201)
                .hasStringField("title", requestBody.getTitle())
                .hasStringField("body", requestBody.getBody())
                .hasNumberField("userId", requestBody.getUserId())
                .toObjectAssert()
                .describedAs("Number of posts should be increased by 1")
                .extracting(Post::getId).isEqualTo(posts.size() + 1);
    }

    @Test
    public void shouldBeAbleToDeletePosts() {
        //given
        ResponseWrapper<Post> addedPost = resourcesActions.addPost(Post.builder().build());
        Integer createdPostId = addedPost.getOrElseThrow().getId();

        //when
        ResponseWrapper<Post> response = resourcesActions.deletePost(createdPostId);

        //then
        ResponseAssert.assertThat(response)
                .hasStatus(200)
                .hasNoBody();

        //and
        ResponseAssert.assertThat(resourcesActions.getPostById(createdPostId))
                .hasStatus(404)
                .hasNoBody();

        //and
        ResponseWrapper<List<Post>> allPosts = resourcesActions.getAllPosts();

        ResponseAssert.assertThat(allPosts).hasStatus(200);

        Assertions.assertThat(allPosts.getOrElseThrow())
                .withFailMessage("Post with id <%s> is NOT deleted", createdPostId)
                .noneMatch(post -> createdPostId.equals(post.getId()))
                .as("All posts ids should be unique")
                .map(Post::getId)
                .doesNotHaveDuplicates();
    }
}
