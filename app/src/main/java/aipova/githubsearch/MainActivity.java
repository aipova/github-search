package aipova.githubsearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aipova.githubsearch.dao.RepoDao;
import aipova.githubsearch.dao.RepoModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView reposRecyclerView;
    private ReposAdapter reposAdapter;
    private RepoDao repoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reposRecyclerView = (RecyclerView) findViewById(R.id.repo_recycler_view);
        repoDao = new RepoDao();
        reposAdapter = new ReposAdapter(new ArrayList<RepoModel>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        reposRecyclerView.setLayoutManager(layoutManager);
        reposRecyclerView.setItemAnimator(new DefaultItemAnimator());
        reposRecyclerView.setAdapter(reposAdapter);

        GetReposTask getReposTask = new GetReposTask();
        getReposTask.execute();
    }

    class GetReposTask extends AsyncTask<Void, Void, List<RepoModel>> {
        @Override
        protected List<RepoModel> doInBackground(Void... params) {
            try {
                return repoDao.getSampleRepoModelsFromApi();
            } catch (IOException apiAxeption) {
                Log.d(MainActivity.class.getName(), "Exception during api call: " + apiAxeption.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<RepoModel> repoModels) {
            super.onPostExecute(repoModels);
            if (repoModels != null) {
                reposAdapter.setRepoModelList(repoModels);
                reposAdapter.notifyDataSetChanged();
            }
        }
    }
}
