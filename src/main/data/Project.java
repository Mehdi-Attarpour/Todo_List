package main.data;

/**
 * A class to hold details about each project such as name.
 */
public class Project {
    private String title;

    public Project(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
