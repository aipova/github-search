package aipova.githubsearch

import aipova.githubsearch.data.RepoDao
import aipova.githubsearch.data.source.GitHubApi

object Injection {
    lateinit var gitHubApi: GitHubApi
    lateinit var repoDao: RepoDao
}