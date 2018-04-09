package aipova.githubsearch.dao;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import aipova.githubsearch.App;
import retrofit2.Response;

/**
 * Created by aipova on 07.02.17.
 */

public class RepoDao {
    private static final List<RepoModel> SAMPLE_REPO_LIST = Arrays.asList(
            new RepoModel("Interesting Repo", "Very interesting project"),
            new RepoModel("Not interesting Repo", "Not so interesting project"));

    public List<RepoModel> getSampleRepoModels() {
        return SAMPLE_REPO_LIST;
    }

    public List<RepoModel> getSampleRepoModelsFromApi() throws IOException {
        Response<RepoSearchResult> result = App.getGitHubApi()
                .getRepositories("todolist", "stars", "desc", "1", "10").execute();
        return result.body() != null && result.body().getItems() != null ? result.body().getItems() : Collections.<RepoModel>emptyList();
    }

    public List<RepoModel> getRepoModelsFromApi(String searchQuery, int page) throws IOException {
        Response<RepoSearchResult> result = App.getGitHubApi()
                .getRepositories(searchQuery, "stars", "desc", String.valueOf(page), "10").execute();
        return result.body() != null && result.body().getItems() != null ? result.body().getItems() : Collections.<RepoModel>emptyList();
    }
}
