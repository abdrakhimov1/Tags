package ru.shaldnikita.Tags.backend.models;

/**
 * @author n.shaldenkov on 19.11.2017
 */
public class Tag {

    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
