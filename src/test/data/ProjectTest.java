package data;

import main.data.Project;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @org.junit.jupiter.api.Test
    void equals() {
        Project otherProject1 = new Project("SDA");
        Project otherProject2 = new Project("sda");
        Project otherProject3 = new Project(" sda");
        Project otherProject4 = null;
        Project otherProject5= new Project(null);
        Project otherProject6 = new Project("");

        assertTrue(otherProject1.equals(otherProject2) && !otherProject1.equals(otherProject3) &&
                        !otherProject1.equals(otherProject4) &&
                !otherProject1.equals(otherProject5) && !otherProject1.equals(otherProject6));
    }
}