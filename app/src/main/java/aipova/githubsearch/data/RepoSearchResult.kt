package aipova.githubsearch.data

import com.google.gson.annotations.SerializedName


data class RepoSearchResult(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    var items: List<Repo>
)
