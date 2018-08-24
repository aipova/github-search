package aipova.githubsearch


import aipova.githubsearch.data.Repo
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ReposAdapter(private val repoModelList: MutableList<Repo>) :
    RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    fun clear() {
        repoModelList.clear()
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById<View>(R.id.repo_title) as TextView
        var description: TextView = itemView.findViewById<View>(R.id.repo_description) as TextView
    }

    fun addRepoModelsToList(repoModelList: List<Repo>) {
        this.repoModelList.addAll(repoModelList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val repoView = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return RepoViewHolder(repoView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repoModel = repoModelList[position]
        holder.title.text = repoModel.name
        holder.description.text = repoModel.description
    }

    override fun getItemCount(): Int {
        return repoModelList.size
    }


}
