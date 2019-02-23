package main.comparator;

import main.data.Task;

import java.util.Comparator;

public class TitleComparator implements Comparator<Task> {
    public int compare(Task o1, Task o2)
    {
        return o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
    }
}
