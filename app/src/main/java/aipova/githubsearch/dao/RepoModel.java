package aipova.githubsearch.dao;

/**
 * Created by aipova on 07.02.17.
 */

public class RepoModel {
    private String title;
    private String description;

    public RepoModel() {
    }

    public RepoModel(String name, String description) {
        this.title = name;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
