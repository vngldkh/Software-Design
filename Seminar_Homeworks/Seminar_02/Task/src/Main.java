import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Student[] init = {
                new Student("John"),
                new Student("Carl"),
                new Student("Joly"),
                new Student("Felice"),
                new Student("Felix")
        };
        var group = new Group(init);
        Scanner console = new Scanner(System.in);
        boolean to_continue = true;
        do {
            System.out.print("> ");
            String command = console.nextLine();
            switch (command) {
                case "/h":
                    System.out.println("1. /r - choose random student");
                    System.out.println("2. /l - list of students with grades");
                    System.out.println("3. /e - exit the program");
                    break;
                case "/l":
                    group.list();
                    break;
                case "/r":
                    group.randomStudent().answer();
                    break;
                case "/e":
                    to_continue = false;
                    break;
                default:
                    System.out.println("No such command!");
                    break;
            }
        } while (to_continue);
    }
}