package aipova.githubsearch.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by aipova on 09.02.17.
 */

class RepoOwnerModel {
    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null
}
