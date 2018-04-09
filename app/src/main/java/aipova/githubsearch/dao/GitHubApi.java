package aipova.githubsearch.dao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aipova on 09.02.17.
 */

public interface GitHubApi {
    public static final String GITHUB_API_URL = "https://api.github.com/";

    @GET("/search/repositories")
    Call<RepoSearchResult> getRepositories(@Query("q") String q, @Query("sort") String sort,
                                           @Query("order") String order, @Query("page") String page,
                                           @Query("per_page") String perPage);
}
