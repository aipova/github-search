package aipova.githubsearch;

import android.app.Application;

import aipova.githubsearch.dao.GitHubApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aipova on 09.02.17.
 */

public class App extends Application {

    private static GitHubApi gitHubApi;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeGitHubApi();
    }

    private void initializeGitHubApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GitHubApi.GITHUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubApi = retrofit.create(GitHubApi.class);
    }

    public static GitHubApi getGitHubApi() {
        return gitHubApi;
    }
}
