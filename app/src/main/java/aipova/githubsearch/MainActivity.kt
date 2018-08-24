package aipova.githubsearch

import aipova.githubsearch.data.Repo
import aipova.githubsearch.data.RepoDao
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var reposAdapter = ReposAdapter(mutableListOf())
    private var repoDao: RepoDao = Injection.repoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn.setOnClickListener { searchForRepos() }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(repoRecyclerView) {
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            adapter = reposAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                private var loading: Boolean = false
                private var pageCount = 1
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    if (!loading && lastVisibleItemPosition == reposAdapter.itemCount - 1) {
                        loading = true
                        fetchData(++pageCount)
                        loading = false

                    }
                }
            })
        }

    }

    private fun fetchData(page: Int = 1) {
        val searchString = searchTxt.text.toString()
        if (!searchString.isEmpty()) {
            val getReposTask = GetReposTask()
            getReposTask.execute(searchString, page.toString())
        }
    }

    private fun searchForRepos() {
        reposAdapter.clear()
        hideKeyboard()
        fetchData()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }

    inner class GetReposTask : AsyncTask<String, Void, List<Repo>>() {
        override fun doInBackground(vararg params: String): List<Repo>? {
            try {
                return repoDao.getRepoModelsFromApi(params[0], Integer.valueOf(params[1]))
            } catch (apiException: IOException) {
                Log.d(
                    MainActivity::class.java.name,
                    "Exception during api call: " + apiException.message
                )
            }

            return null
        }

        override fun onPostExecute(repoModels: List<Repo>?) {
            super.onPostExecute(repoModels)
            if (repoModels != null) {
                reposAdapter.addRepoModelsToList(repoModels)
                reposAdapter.notifyDataSetChanged()
            }
        }
    }
}
