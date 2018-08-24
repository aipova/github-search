package aipova.githubsearch

import aipova.githubsearch.data.RepoDataSource
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var reposAdapter = ReposAdapter(mutableListOf())
    private var repoDataSource: RepoDataSource = Injection.repoDao

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
            repoDataSource.searchRepos(searchString, page).subscribe({ result ->
                reposAdapter.addRepoModelsToList(result.items)
                reposAdapter.notifyDataSetChanged()
            }, { onError() })
        }
    }

    private fun onError() {
        Toast.makeText(this@MainActivity, "Cannot load repositories from GitHub", Toast.LENGTH_LONG).show()
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
}
