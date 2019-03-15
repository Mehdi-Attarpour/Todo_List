package data;

import main.data.Project;
import main.data.Status;
import main.data.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Project project = new Project("KTH");
    Task task = new Task("Code", LocalDate.parse("1986-05-24"), project, Status.Done);

    @Test
    void getTitleTest() {
        assertEquals("Code", task.getTitle());
    }

    @Test
    void setTitleTest() {
        task.setTitle("Sleep");
        assertEquals("Sleep", task.getTitle());
    }

    @Test
    void getDueDateTest() {
        LocalDate expectedDate = LocalDate.parse("1986-05-24");
        assertEquals(expectedDate, task.getDueDate());
    }

    @Test
    void setDueDateTest() {
        LocalDate newDate = LocalDate.parse("1987-02-13");
        task.setDueDate(newDate);
        assertEquals(newDate, task.getDueDate());
    }

    @Test
    void getProjectTest() {
        assertEquals(project, task.getProject());
    }

    @Test
    void setProjectTest() {
        Project newProject = new Project("Novare");
        task.setProject(newProject);
        assertEquals(newProject, task.getProject());
    }

    @Test
    void setStatusTest() {
        task.setStatus(Status.Not_Done);
        assertEquals(Status.Not_Done, task.getStatus());
    }


    @Test
    void getStatusTest() {
        assertEquals(Status.Done, task.getStatus());
    }

    @Test
    void equalsTest() {
        Task otherTask1 = new Task("Code", LocalDate.parse("1986-05-24"), project, Status.Done);
        Task otherTask2 = new Task("Sleep", LocalDate.parse("1986-05-24"), project, Status.Done);
        Task otherTask3 = new Task("Code", LocalDate.parse("1987-02-13"), project, Status.Done);
        Task otherTask4 = new Task("Code", LocalDate.parse("1986-05-24"), new Project("Novare"), Status.Done);
        Task otherTask5 = new Task("Code", LocalDate.parse("1986-05-24"), project, Status.Not_Done);

        assertTrue(task.equals(otherTask1) && !task.equals(otherTask2) && !task.equals(otherTask3) &&
                    !task.equals(otherTask4) && !task.equals(otherTask5));
    }

    @Test
    void toStringTest() {
        String expectedString = "    Task: Code             Due Date: 1986-05-24     Status: Done           Project: KTH";
        assertEquals(expectedString, task.toString());
    }
}