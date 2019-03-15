package main.comparator;

import main.data.Task;

import java.util.Comparator;

public class DateComparator implements Comparator<Task> {
    public int compare(Task o1, Task o2)
    {
        return o1.getDueDate().compareTo(o2.getDueDate());
    }
}

