package main.data;

import java.time.LocalDate;

/**
 * A class to hold details about each tasks
 * such as title, due date, project and status.
 */


public class Task {

    private String title;
    private LocalDate dueDate;
    private Project project;
    private Status status;

    /**
     * Constructor for objects of class Task.
     * @param title The task's title.
     * @param dueDate The task's due date.
     * @param project The task's project
     */
    public Task(String title, LocalDate dueDate, Project project) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = Status.Not_Done;
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-20s* Task: %-15s  Due Date: %-14s Status: %-14s Project: %s",
                "",title, dueDate , status , project.getTitle());
    }

    /**
     * Check if two tasks are equal.
     * @param otherTask The other task to check
     * @return True if tasks are ewual.
     * @see Project for projects equality conditions.
     */
    @Override
    public boolean equals(Object otherTask){
        if (!(otherTask instanceof Task))
            return false;
        Task t = (Task) otherTask;
        return t.title.equalsIgnoreCase(this.title) && t.dueDate.equals(this.dueDate) && t.project.equals(this.project);
    }
}
