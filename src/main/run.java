package main;

import main.data.Project;
import main.data.Status;
import main.data.Task;
import main.data.TaskList;
import main.print.Print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class run {
    public static void main(String[] args) {

        Project p = new Project("SDA");
        Project pp = new Project("Novare");
        Task a = new Task("IP", "2019-11-01", p);
        Task b = new Task("first", "2018-11-01", p);
        Task c = new Task("Zeta", "2012-11-01", pp);
        Task d = new Task("absolut", "2018-11-02", p);

        TaskList l = new TaskList();



        ArrayList<Task> h = l.sortTaskListByDueDate();



        Print.printMap(l.groupedByProject());
    }
}
