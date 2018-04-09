package aipova.githubsearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aipova.githubsearch.dao.RepoDao;
import aipova.githubsearch.dao.RepoModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView reposRecyclerView;
    private ReposAdapter reposAdapter;
    private EditText editText;
    private RepoDao repoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.search_text);

        reposRecyclerView = (RecyclerView) findViewById(R.id.repo_recycler_view);
        repoDao = new RepoDao();
        reposAdapter = new ReposAdapter(new ArrayList<RepoModel>());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        reposRecyclerView.setLayoutManager(layoutManager);
        reposRecyclerView.setItemAnimator(new DefaultItemAnimator());
        reposRecyclerView.setAdapter(reposAdapter);
        reposRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean loading;
            private int pageCount = 1;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int lastvisibleitemposition = layoutManager.findLastVisibleItemPosition();

                if (lastvisibleitemposition == reposAdapter.getItemCount() - 1) {

                    if (!loading) {
                        loading = true;
                        fetchData((++pageCount));
                    }
                }
            }

            private void fetchData(int page) {
                String searchString = editText.getText().toString();
                if (!searchString.isEmpty()) {
                    GetReposTask getReposTask = new GetReposTask();
                    getReposTask.execute(searchString, String.valueOf(page));
                }
                loading=false;
            }
        });
    }

    public void searchForRepo(View view) {

        reposAdapter.clear();
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String searchString = editText.getText().toString();
        if (!searchString.isEmpty()) {
            GetReposTask getReposTask = new GetReposTask();
            getReposTask.execute(searchString, "1");
        }
    }

    class GetReposTask extends AsyncTask<String, Void, List<RepoModel>> {
        @Override
        protected List<RepoModel> doInBackground(String... params) {
            try {
                return repoDao.getRepoModelsFromApi(params[0], Integer.valueOf(params[1]));
            } catch (IOException apiAxeption) {
                Log.d(MainActivity.class.getName(), "Exception during api call: " + apiAxeption.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<RepoModel> repoModels) {
            super.onPostExecute(repoModels);
            if (repoModels != null) {
                reposAdapter.addRepoModelsToList(repoModels);
                reposAdapter.notifyDataSetChanged();
            }
        }
    }
}
