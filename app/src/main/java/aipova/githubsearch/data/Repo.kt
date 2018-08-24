package aipova.githubsearch.data

import com.google.gson.annotations.SerializedName

data class Repo (
    val id: Long,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val owner: RepoOwner?,
    val description: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("git_url")
    var gitUrl: String?,
    @SerializedName("watchers_count")
    var watchersCount: Int?,
    var language: String?,
    var score: Double?
)
