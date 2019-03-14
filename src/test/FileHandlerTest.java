import main.FileHandler;
import main.data.Project;
import main.data.Status;
import main.data.Task;
import main.data.TaskList;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    FileHandler fileHandler;
    File file;

    @BeforeEach
    void setUp(){
        fileHandler =  new FileHandler("fileForTest");
    }

    @Test
    void saveAndLoad() {
        // Setup data to save.
        Project project1 = new Project("KTH");
        Project project2 = new Project("Novare");

        Task firstTask = new Task("Code", LocalDate.parse("2019-05-14"), project1, Status.Not_Done);
        Task secondTask = new Task("Watch Netflix", LocalDate.parse("2019-05-12"), project1, Status.Done);
        Task thirdTask = new Task("Sleep", LocalDate.parse("2019-05-13"), project1, Status.Not_Done);
        Task fourthTask = new Task("Code", LocalDate.parse("2019-05-12"),project2, Status.Done);
        TaskList list = new TaskList();
        list.add(firstTask);
        list.add(secondTask);
        list.add(thirdTask);
        list.add(fourthTask);

        // Setting for testing the message which must be printed in console.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        fileHandler.save(list);
        TaskList loadedTaskList = fileHandler.load();

        // Checking if the loaded TaskList is actually equals to the original one.
        assertEquals(list, loadedTaskList);

        // Checking the printed message in console
        String expectedMessage = "Save is successful\nLoad is successful\n";
        assertEquals(expectedMessage, output.toString());
    }

    @AfterEach
    void tearDown(){
        fileHandler.file.delete();
    }
}