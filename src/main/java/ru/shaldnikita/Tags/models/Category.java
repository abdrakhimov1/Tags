package ru.shaldnikita.Tags.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * @author n.shaldenkov on 19.11.2017
 */
public class Category {

    private String id;
    private String name;

    @JsonIgnore
    private List<Tag> tags;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
