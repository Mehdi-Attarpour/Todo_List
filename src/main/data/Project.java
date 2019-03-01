package main.data;

import java.io.Serializable;

/**
 * A class to hold details about each project such as name.
 */
public class Project implements Serializable {
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

    /**
     * Checks if the title of two projects are same (consider them as equal)
     * @param otheProject The project to check if they are equal.
     * @return True if both projects have the same title.
     */
    @Override
    public boolean equals(Object otheProject) {
        if (!(otheProject instanceof Project))
            return false;
        Project p = (Project) otheProject;
        return p.title.equalsIgnoreCase(this.title);
    }

}
