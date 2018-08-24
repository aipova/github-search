package aipova.githubsearch.data

import com.google.gson.annotations.SerializedName

data class RepoOwner(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String
)

