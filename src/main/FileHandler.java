package main;

import main.data.Task;
import main.data.TaskList;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static final String filePath = "file";

    public boolean save(ArrayList<Task> list){
        boolean success = false;
        try{
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            ois.writeObject(list);
            ois.close();
            success = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public TaskList load(){
        boolean success = false;
        TaskList list = new TaskList();
        try{
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (TaskList) ois.readObject();
            ois.close();
            success = true;
        } catch (Exception e){
            e.getMessage();
        }
        if (success == true) {
            System.out.println("Load is successful");
        }
        return list;
    }
}
