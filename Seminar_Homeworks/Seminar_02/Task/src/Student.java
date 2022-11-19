import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    String name;
    private ArrayList<Integer> marks;

    Student(String name) {
        this.name = name;
        marks = new ArrayList<>();
    }

    private void addMark(int mark) {
        marks.add(mark);
    }

    public void answer() {
        System.out.println("Отвечает студент " + name);
        System.out.print("Присутствует ли на паре? (y/n)\n> ");
        Scanner console = new Scanner(System.in);
        String attendance = console.nextLine();
        if (!attendance.equals("y")) {
            return;
        }
        System.out.print("Оценка за ответ:\n> ");
        int mark = console.nextInt();
        this.addMark(mark);
    }

    public void print() {
        System.out.print(name + '\t');
        for (Integer mark : marks) {
            System.out.print(mark + "\t");
        }
        System.out.println();
    }
}
