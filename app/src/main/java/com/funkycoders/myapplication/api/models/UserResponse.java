package com.funkycoders.myapplication.api.models;

import com.google.gson.annotations.SerializedName;

public class UserResponse extends UsersResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("company")
    private String company;

    @SerializedName("blog")
    private String blog;

    @SerializedName("location")
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("hireable")
    private Object hireable;

    @SerializedName("bio")
    private String bio;

    @SerializedName("twitter_username")
    private String twitterUsername;

    @SerializedName("public_repos")
    private int publicRepos;

    @SerializedName("public_gists")
    private int publicGists;

    @SerializedName("followers")
    private int followers;

    @SerializedName("following")
    private int following;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public UserResponse(String name, String company, String blog, String location, String email, Object hireable, String bio, String twitterUsername, int publicRepos, int publicGists, int followers, int following, String createdAt, String updatedAt) {
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.twitterUsername = twitterUsername;
        this.publicRepos = publicRepos;
        this.publicGists = publicGists;
        this.followers = followers;
        this.following = following;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public UserResponse(String login, Integer id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
        super(login, id, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl, reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin);
    }

    public UserResponse() {
        super();
    }

    public String getName() {
        return name;
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public String getNodeId() {
        return super.getNodeId();
    }

    @Override
    public void setNodeId(String nodeId) {
        super.setNodeId(nodeId);
    }

    @Override
    public String getAvatarUrl() {
        return super.getAvatarUrl();
    }

    @Override
    public void setAvatarUrl(String avatarUrl) {
        super.setAvatarUrl(avatarUrl);
    }

    @Override
    public String getGravatarId() {
        return super.getGravatarId();
    }

    @Override
    public void setGravatarId(String gravatarId) {
        super.setGravatarId(gravatarId);
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url);
    }

    @Override
    public String getHtmlUrl() {
        return super.getHtmlUrl();
    }

    @Override
    public void setHtmlUrl(String htmlUrl) {
        super.setHtmlUrl(htmlUrl);
    }

    @Override
    public String getFollowersUrl() {
        return super.getFollowersUrl();
    }

    @Override
    public void setFollowersUrl(String followersUrl) {
        super.setFollowersUrl(followersUrl);
    }

    @Override
    public String getFollowingUrl() {
        return super.getFollowingUrl();
    }

    @Override
    public void setFollowingUrl(String followingUrl) {
        super.setFollowingUrl(followingUrl);
    }

    @Override
    public String getGistsUrl() {
        return super.getGistsUrl();
    }

    @Override
    public void setGistsUrl(String gistsUrl) {
        super.setGistsUrl(gistsUrl);
    }

    @Override
    public String getStarredUrl() {
        return super.getStarredUrl();
    }

    @Override
    public void setStarredUrl(String starredUrl) {
        super.setStarredUrl(starredUrl);
    }

    @Override
    public String getSubscriptionsUrl() {
        return super.getSubscriptionsUrl();
    }

    @Override
    public void setSubscriptionsUrl(String subscriptionsUrl) {
        super.setSubscriptionsUrl(subscriptionsUrl);
    }

    @Override
    public String getOrganizationsUrl() {
        return super.getOrganizationsUrl();
    }

    @Override
    public void setOrganizationsUrl(String organizationsUrl) {
        super.setOrganizationsUrl(organizationsUrl);
    }

    @Override
    public String getReposUrl() {
        return super.getReposUrl();
    }

    @Override
    public void setReposUrl(String reposUrl) {
        super.setReposUrl(reposUrl);
    }

    @Override
    public String getEventsUrl() {
        return super.getEventsUrl();
    }

    @Override
    public void setEventsUrl(String eventsUrl) {
        super.setEventsUrl(eventsUrl);
    }

    @Override
    public String getReceivedEventsUrl() {
        return super.getReceivedEventsUrl();
    }

    @Override
    public void setReceivedEventsUrl(String receivedEventsUrl) {
        super.setReceivedEventsUrl(receivedEventsUrl);
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public Boolean getSiteAdmin() {
        return super.getSiteAdmin();
    }

    @Override
    public void setSiteAdmin(Boolean siteAdmin) {
        super.setSiteAdmin(siteAdmin);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getHireable() {
        return hireable;
    }

    public void setHireable(Object hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
