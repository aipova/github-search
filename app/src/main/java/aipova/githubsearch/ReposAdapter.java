package aipova.githubsearch;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import aipova.githubsearch.dao.RepoModel;

/**
 * Created by aipova on 07.02.17.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder>{

    List<RepoModel> repoModelList;

    public void clear() {
        repoModelList.clear();
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public RepoViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.repo_title);
            description = (TextView) itemView.findViewById(R.id.repo_description);
        }
    }

    public ReposAdapter(List<RepoModel> repoModelList) {
        this.repoModelList = repoModelList;
    }

    public List<RepoModel> getRepoModelList() {
        return repoModelList;
    }

    public void setRepoModelList(List<RepoModel> repoModelList) {
        this.repoModelList = repoModelList;
    }

    public void addRepoModelsToList(List<RepoModel> repoModelList) {
        this.repoModelList.addAll(repoModelList);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View repoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
        return new RepoViewHolder(repoView);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        RepoModel repoModel = repoModelList.get(position);
        holder.title.setText(repoModel.getTitle());
        holder.description.setText(repoModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return repoModelList != null ? repoModelList.size() : 0;
    }


}
