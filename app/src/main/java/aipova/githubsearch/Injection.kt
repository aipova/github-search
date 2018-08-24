package aipova.githubsearch

import aipova.githubsearch.data.RepoDataSource
import aipova.githubsearch.data.source.GitHubApi

object Injection {
    lateinit var gitHubApi: GitHubApi
    lateinit var repoDao: RepoDataSource
}