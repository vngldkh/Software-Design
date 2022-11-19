import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Group {
    private final ArrayList<Student> group;

    Group(Student[] data) {
        group = new ArrayList<>();
        Collections.addAll(group, data);
    }

    public void list() {
        for (int i = 0; i < group.size(); ++i) {
            System.out.print((i + 1) + "\t");
            group.get(i).print();
        }
    }

    public Student randomStudent() {
        return group.get((int) (Math.random() * (group.size() - 1)));
    }

}
