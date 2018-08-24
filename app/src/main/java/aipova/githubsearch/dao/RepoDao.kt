package aipova.githubsearch.dao

import aipova.githubsearch.Injection
import java.io.IOException

/**
 * Created by aipova on 07.02.17.
 */

class RepoDao {

    @Throws(IOException::class)
    fun getRepoModelsFromApi(searchQuery: String, page: Int): List<RepoModel>? {
        val result = Injection.gitHubApi
            .getRepositories(searchQuery, "stars", "desc", page.toString(), "10").execute()
        return if (result.body() != null && result.body()!!.items != null) result.body()!!.items else emptyList()
    }
}
