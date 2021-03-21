package in.co.electronicshub.recursion.collegehub.utils;

import android.graphics.drawable.Drawable;

public class Domain {
    private String icon;
    private String title;
    private int count, id;

    public Domain(String icon, String title, int count, int id) {
        this.icon = icon;
        this.title = title;
        this.count = count;
        this.id = id;
    }

    public Domain() {
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }
}
