package aipova.githubsearch.data.source

import aipova.githubsearch.data.RepoSearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
        @Query("page") page: String,
        @Query("per_page") perPage: String = "10"
    ): Observable<RepoSearchResult>

    companion object {
        const val GITHUB_API_URL = "https://api.github.com/"
    }
}
