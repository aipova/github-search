package aipova.githubsearch

import aipova.githubsearch.dao.GitHubApi
import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by aipova on 09.02.17.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeGitHubApi()
    }

    private fun initializeGitHubApi() {
        val retrofit = Retrofit.Builder().baseUrl(GitHubApi.GITHUB_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Injection.gitHubApi = retrofit.create(GitHubApi::class.java)
    }
}
