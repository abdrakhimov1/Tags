package ru.shaldnikita.Tags.backend.models;

import java.util.Map;

/**
 * @author n.shaldenkov on 19.11.2017
 */
public class Category {

    private String name;

    private Map<String,Tag> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Tag> getTags() {
        return tags;
    }

    public void setTags(Map<String, Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", tags=" + tags +
                '}';
    }
}
