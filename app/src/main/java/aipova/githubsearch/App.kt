package aipova.githubsearch

import aipova.githubsearch.data.RepoDataSource
import aipova.githubsearch.data.source.GitHubApi
import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeGitHubApi()
    }

    private fun initializeGitHubApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(GitHubApi.GITHUB_API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Injection.gitHubApi = retrofit.create(GitHubApi::class.java)
        Injection.repoDao = RepoDataSource(Injection.gitHubApi)
    }
}
