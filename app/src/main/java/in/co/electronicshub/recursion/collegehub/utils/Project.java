package in.co.electronicshub.recursion.collegehub.utils;

import android.graphics.drawable.Drawable;

public class Project {
    private int id, likes, views;
    private Drawable author;
    private String title;

    public Project() {
    }

    public Project(Drawable author, String title, int id, int views, int likes) {
        this.id = id;
        this.likes = likes;
        this.views = views;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }

    public Drawable getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
