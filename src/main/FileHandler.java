package main;

import main.data.Task;
import main.data.TaskList;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public File file;

    public FileHandler(String filePath) {
        this.file = new File(filePath);
    }

    public boolean save(ArrayList<Task> list){
        boolean success = false;
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            ois.writeObject(list);
            ois.close();
            success = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (success == true) {
            System.out.println("Save is successful");
        }
        return success;
    }

    public TaskList load(){
        boolean success = false;
        TaskList list = new TaskList();
        try{
            FileInputStream fis = new FileInputStream(file);
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
