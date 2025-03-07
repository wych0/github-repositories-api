package com.recruitment;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class GithubResourceTest {
    @Test
    void testGetUserRepos_HappyPath() {
        String username = "octocat";

        given()
                .when().get("/github/" + username + "/repos")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].repoName", not(emptyOrNullString()))
                .body("[0].ownerLogin", is(username))
                .body("[0].branches", not(empty()));
    }

}