package data;

import main.comparator.DateComparator;
import main.comparator.TitleComparator;
import main.data.Project;
import main.data.Status;
import main.data.Task;
import main.data.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    Project project1 = new Project("KTH");
    Project project2 = new Project("Novare");

    Task firstTask = new Task("Code", LocalDate.parse("2019-05-14"), project1, Status.Not_Done);
    Task secondTask = new Task("Watch Netflix", LocalDate.parse("2019-05-12"), project1, Status.Done);
    Task thirdTask = new Task("Sleep", LocalDate.parse("2019-05-13"), project1, Status.Not_Done);
    Task fourthTask = new Task("Code", LocalDate.parse("2019-05-12"),project2, Status.Done);
    TaskList list = new TaskList();

    @BeforeEach
    void addTaskToList(){
        list.add(firstTask);
        list.add(secondTask);
        list.add(thirdTask);
        list.add(fourthTask);
    }

    @Test
    void addTask() {
        // Setting for testing the error which must be printed in console.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        TaskList listToAddTasks = new TaskList();
        // It should add the first and second task but not the third one.
        boolean firstAdd = listToAddTasks.addTask("Code", LocalDate.parse("2019-05-14"), project1, Status.Not_Done);
        boolean secondAdd = listToAddTasks.addTask("Watch Netflix", LocalDate.parse("2019-05-12"), project1, Status.Done);
        boolean duplicateTaskAdd = listToAddTasks.addTask("Code", LocalDate.parse("2019-05-14"), project1, Status.Not_Done);

        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(firstTask);
        expectedTaskList.add(secondTask);

        String expectedErrorOnTerminal = "\nYou already have the same task. Please try again\n";

        // Check the return boolean for each addTask.
        assertTrue(firstAdd && secondAdd && !duplicateTaskAdd);
        // Check if adding is actually successful
        assertEquals(expectedTaskList,listToAddTasks);
        // Check if the printing error in console is correct.
        assertEquals(expectedErrorOnTerminal, output.toString());
    }

    @Test
    void removeAllTaskByTitle() {
        list.removeAllTaskByTitle("Code");

        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(secondTask);
        expectedTaskList.add(thirdTask);

        assertEquals(list, expectedTaskList);
    }

    @Test
    void taskByStatus() {

        ArrayList<Task> doneTasks = list.taskByStatus(Status.Done);
        ArrayList<Task> notDoneTasks = list.taskByStatus(Status.Not_Done);

        ArrayList<Task> expectedDone = new ArrayList<>();
        expectedDone.add(secondTask);
        expectedDone.add(fourthTask);

        ArrayList<Task> expectedNotDone = new ArrayList<>();
        expectedNotDone.add(firstTask);
        expectedNotDone.add(thirdTask);

        assertEquals(expectedDone, doneTasks);
        assertEquals(expectedNotDone, notDoneTasks);
    }

    @Test
    void taskByProject() {
        ArrayList<Task> taskByProject = list.taskByProject("KTH");

        ArrayList<Task> expectedArrayList = new ArrayList<>();
        expectedArrayList.add(firstTask);
        expectedArrayList.add(secondTask);
        expectedArrayList.add(thirdTask);

        assertEquals(expectedArrayList, taskByProject);
    }

    @Test
    void taskByDueDate() {
        ArrayList<Task> taskByDueDate = list.taskByDueDate(LocalDate.parse("2019-05-12"));

        ArrayList<Task> expected = new ArrayList<>();
        expected.add(secondTask);
        expected.add(fourthTask);

        assertEquals(expected, taskByDueDate);
    }

    @Test
    void sortList() {
        ArrayList<Task> sortedListByTitle = list.sortList(new TitleComparator());
        ArrayList<Task> sortedListByDate = list.sortList(new DateComparator());

        ArrayList<Task> expectedListByTitle = new ArrayList<>();
        expectedListByTitle.add(firstTask);
        expectedListByTitle.add(fourthTask);
        expectedListByTitle.add(thirdTask);
        expectedListByTitle.add(secondTask);

        ArrayList<Task> expectedListByDate = new ArrayList<>();
        expectedListByDate.add(secondTask);
        expectedListByDate.add(fourthTask);
        expectedListByDate.add(thirdTask);
        expectedListByDate.add(firstTask);

        assertEquals(expectedListByTitle, sortedListByTitle);
        assertEquals(expectedListByDate, sortedListByDate);
    }

    @Test
    void groupedByProject() {
        Map<Project, List<Task>> groupedByProject = list.groupedByProject();

        List<Task> tasksByPtojectKTH = list.taskByProject("kth");
        List<Task> tasksByPtojectNovare = list.taskByProject("Novare");

        Map<Project, List<Task>> expectedMap = new HashMap<>();
        expectedMap.put(project1, tasksByPtojectKTH);
        expectedMap.put(project2, tasksByPtojectNovare);

        assertEquals(expectedMap, groupedByProject);
    }

    @Test
    void summary() {
        String summary = list.summary();
        String expectedString = ">> You have 2 Tasks todo and 2 Tasks are done.";

        assertEquals(expectedString, summary);
    }
}