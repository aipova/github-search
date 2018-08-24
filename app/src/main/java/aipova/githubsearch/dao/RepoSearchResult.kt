package aipova.githubsearch.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by aipova on 09.02.17.
 */

class RepoSearchResult {
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null
    @SerializedName("items")
    @Expose
    var items: List<RepoModel>? = null

}
