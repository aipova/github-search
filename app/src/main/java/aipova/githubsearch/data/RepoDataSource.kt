package aipova.githubsearch.data

import aipova.githubsearch.data.source.GitHubApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepoDataSource(private val gitHubApi: GitHubApi) {
    interface Callbacks {
        fun onResult(repos: List<Repo>)
        fun onError()
    }

    fun searchRepos(searchQuery: String, page: Int): Observable<RepoSearchResult> {
        return gitHubApi.getRepositories(q = searchQuery, page = page.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
