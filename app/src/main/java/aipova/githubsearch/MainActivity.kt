package aipova.githubsearch

import aipova.githubsearch.dao.RepoDao
import aipova.githubsearch.dao.RepoModel
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    private var reposRecyclerView: RecyclerView? = null
    private var reposAdapter: ReposAdapter? = null
    private var editText: EditText? = null
    private var repoDao: RepoDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById<View>(R.id.search_text) as EditText

        reposRecyclerView = findViewById<View>(R.id.repo_recycler_view) as RecyclerView
        repoDao = RepoDao()
        reposAdapter = ReposAdapter(ArrayList())
        val layoutManager = LinearLayoutManager(applicationContext)
        reposRecyclerView!!.layoutManager = layoutManager
        reposRecyclerView!!.itemAnimator = DefaultItemAnimator()
        reposRecyclerView!!.adapter = reposAdapter
        reposRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var loading: Boolean = false
            private var pageCount = 1
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val lastvisibleitemposition = layoutManager.findLastVisibleItemPosition()

                if (lastvisibleitemposition == reposAdapter!!.itemCount - 1) {

                    if (!loading) {
                        loading = true
                        fetchData(++pageCount)
                    }
                }
            }

            private fun fetchData(page: Int) {
                val searchString = editText!!.text.toString()
                if (!searchString.isEmpty()) {
                    val getReposTask = GetReposTask()
                    getReposTask.execute(searchString, page.toString())
                }
                loading = false
            }
        })
    }

    fun searchForRepo(view: View) {

        reposAdapter!!.clear()
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }

        val searchString = editText!!.text.toString()
        if (!searchString.isEmpty()) {
            val getReposTask = GetReposTask()
            getReposTask.execute(searchString, "1")
        }
    }

    internal inner class GetReposTask : AsyncTask<String, Void, List<RepoModel>>() {
        override fun doInBackground(vararg params: String): List<RepoModel>? {
            try {
                return repoDao!!.getRepoModelsFromApi(params[0], Integer.valueOf(params[1]))
            } catch (apiAxeption: IOException) {
                Log.d(
                    MainActivity::class.java.name,
                    "Exception during api call: " + apiAxeption.message
                )
            }

            return null
        }

        override fun onPostExecute(repoModels: List<RepoModel>?) {
            super.onPostExecute(repoModels)
            if (repoModels != null) {
                reposAdapter!!.addRepoModelsToList(repoModels)
                reposAdapter!!.notifyDataSetChanged()
            }
        }
    }
}
