package ru.shaldnikita.Tags.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * @author n.shaldenkov on 19.11.2017
 */
public class User {

    private String id;

    @JsonIgnore
    private String password;
    private String login;

    private List<Tag> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
