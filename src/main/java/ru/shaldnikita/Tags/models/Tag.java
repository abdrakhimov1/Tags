package ru.shaldnikita.Tags.models;

/**
 * @author n.shaldenkov on 19.11.2017
 */
public class Tag {

    private String id;
    private String name;

    private Category category;
    private User user;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
