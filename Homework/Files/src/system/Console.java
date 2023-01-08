package system;

import entities.FileSystem;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static String Init() {
        System.out.println("Enter the path to the initial directory: ");
        System.out.print(">> ");
        return scanner.nextLine().replace('/', '\\');
    }

    public static boolean Continue() {
        System.out.print("If you want to continue write 'y'.\nOr anything else otherwise.\n>> ");
        String answer = scanner.nextLine();
        System.out.println();
        return "y".equals(answer);
    }

    public static void DisplayFileSystem(FileSystem fileSystem) {
        if (!fileSystem.IsCorrect()) {
            ExceptionMessages.Message(3);
            return;
        }
        System.out.println("Ordered file list:\n");
        for (var fileName : fileSystem.GetOrder()) {
            System.out.println("- " + fileName);
        }
        System.out.println("\nConcatenated text:\n");
        System.out.println(fileSystem.MakeText());
    }
}
