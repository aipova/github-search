package aipova.githubsearch.data

import aipova.githubsearch.data.source.GitHubApi
import java.io.IOException

class RepoDao(private val gitHubApi: GitHubApi) {

    @Throws(IOException::class)
    fun getRepoModelsFromApi(searchQuery: String, page: Int): List<Repo> {
        val result = gitHubApi.getRepositories(q = searchQuery, page = page.toString()).execute()
        return if (result.body() != null) result.body()!!.items else emptyList()
    }
}
