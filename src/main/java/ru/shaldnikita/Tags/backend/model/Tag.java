package ru.shaldnikita.Tags.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@Entity
public class Tag extends AbstractEntity{

    @NotNull
    private String name;

    @JoinColumn(name = "author_id")
    private User author;

    @ElementCollection
    private List<String> categories;

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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
