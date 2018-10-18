package ru.parser.models;

/**
 * Date 20.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class Category {
    private Long id;
    private String name;
    private Long progress;

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
