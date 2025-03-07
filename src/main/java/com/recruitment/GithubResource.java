package com.recruitment;

import com.recruitment.service.GithubService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/github")
public class GithubResource {

    @Inject
    GithubService githubService;

    @GET
    @Path("/{username}/repos")
    public Uni<Response> getUserRepos(@PathParam("username") String username) {
        return githubService.getRepos(username)
                .onItem().transform(repos -> Response.ok(repos).build());
    }
}
