package aipova.githubsearch.dao

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aipova on 09.02.17.
 */

interface GitHubApi {

    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") q: String, @Query("sort") sort: String,
        @Query("order") order: String, @Query("page") page: String,
        @Query("per_page") perPage: String
    ): Call<RepoSearchResult>

    companion object {
        val GITHUB_API_URL = "https://api.github.com/"
    }
}
