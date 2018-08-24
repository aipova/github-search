package aipova.githubsearch.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by aipova on 07.02.17.
 */

class RepoModel {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var title: String? = null
    @SerializedName("full_name")
    @Expose
    var fullName: String? = null
    @SerializedName("owner")
    @Expose
    var owner: RepoOwnerModel? = null
    @SerializedName("private")
    @Expose
    var _private: Boolean? = null
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null


    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("git_url")
    @Expose
    var gitUrl: String? = null
    @SerializedName("ssh_url")
    @Expose
    var sshUrl: String? = null
    @SerializedName("clone_url")
    @Expose
    var cloneUrl: String? = null
    @SerializedName("svn_url")
    @Expose
    var svnUrl: String? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("watchers_count")
    @Expose
    var watchersCount: Int? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("score")
    @Expose
    var score: Double? = null

    constructor() {}

    constructor(name: String, description: String) {
        this.title = name
        this.description = description
    }
}
