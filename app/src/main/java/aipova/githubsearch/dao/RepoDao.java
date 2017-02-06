package aipova.githubsearch.dao;

import java.util.Arrays;
import java.util.List;

/**
 * Created by aipova on 07.02.17.
 */

public class RepoDao {
    private static final List<RepoModel> SAMPLE_REPO_LIST = Arrays.asList(
            new RepoModel("Interesting Repo", "Very interesting project"),
            new RepoModel("Not interesting Repo", "Not so interesting project"));

    public List<RepoModel> getSampleRepoModels() {
        return SAMPLE_REPO_LIST;

    }
}
