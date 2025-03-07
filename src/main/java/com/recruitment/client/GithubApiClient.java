package com.recruitment.client;

import com.recruitment.model.Branch;
import com.recruitment.model.Repo;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient()
@Produces(MediaType.APPLICATION_JSON)
public interface GithubApiClient {
    @GET
    @Path("/users/{user}/repos")
    Uni<List<Repo>> getUserRepos(@PathParam("user") String user);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    Uni<List<Branch>> getRepoBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}
