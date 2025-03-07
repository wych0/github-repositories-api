package com.recruitment.service;

import com.recruitment.client.GithubApiClient;
import com.recruitment.exception.ResourceNotFoundException;
import com.recruitment.dto.RepoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.util.List;

@ApplicationScoped
public class GithubService {

    @RestClient
    GithubApiClient githubApiClient;

    public Uni<List<RepoDTO>> getRepos(String user) {
        return githubApiClient.getUserRepos(user)
                .onItem().transformToMulti(repos -> Multi.createFrom().iterable(repos))
                .filter(repo -> !repo.isFork())
                .onItem().transformToUniAndMerge(repo -> githubApiClient.getRepoBranches(repo.getOwner().getLogin(), repo.getName())
                        .onItem().transform(branches -> new RepoDTO(
                                repo.getName(),
                                repo.getOwner().getLogin(),
                                branches
                        )))
                .collect().asList()
                .onFailure(ClientWebApplicationException.class)
                .transform((failure ->{
                    ClientWebApplicationException clientException = (ClientWebApplicationException) failure;
                    if(clientException.getResponse().getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                        throw new ResourceNotFoundException("GitHub user not found");
                    }

                    throw clientException;
                }));
    }
}
