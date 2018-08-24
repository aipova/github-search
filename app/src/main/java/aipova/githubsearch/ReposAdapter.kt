package aipova.githubsearch


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import aipova.githubsearch.dao.RepoModel

/**
 * Created by aipova on 07.02.17.
 */

class ReposAdapter(internal var repoModelList: MutableList<RepoModel>?) :
    RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    fun clear() {
        repoModelList!!.clear()
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var description: TextView

        init {
            title = itemView.findViewById<View>(R.id.repo_title) as TextView
            description = itemView.findViewById<View>(R.id.repo_description) as TextView
        }
    }

    fun getRepoModelList(): List<RepoModel>? {
        return repoModelList
    }

    fun setRepoModelList(repoModelList: MutableList<RepoModel>) {
        this.repoModelList = repoModelList
    }

    fun addRepoModelsToList(repoModelList: List<RepoModel>) {
        this.repoModelList!!.addAll(repoModelList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val repoView =
            LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return RepoViewHolder(repoView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repoModel = repoModelList!![position]
        holder.title.text = repoModel.title
        holder.description.text = repoModel.description
    }

    override fun getItemCount(): Int {
        return if (repoModelList != null) repoModelList!!.size else 0
    }


}
