package aipova.githubsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import aipova.githubsearch.dao.RepoDao;

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

        reposAdapter = new ReposAdapter(repoDao.getSampleRepoModels());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        reposRecyclerView.setLayoutManager(layoutManager);
        reposRecyclerView.setItemAnimator(new DefaultItemAnimator());
        reposRecyclerView.setAdapter(reposAdapter);

        reposAdapter.notifyDataSetChanged();
    }
}
