package system;

import entities.FileSystem;

import java.util.Scanner;

/***
 * Class to interact with user through the console.
 */
public class Console {
    /***
     * Object of Scanner class to read text from the console.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /***
     * Static method to initialize work of the program.
     * Reads the path to the root folder.
     * @return Path to the root folder.
     */
    public static String Init() {
        System.out.println("Enter the path to the initial directory: ");
        System.out.print(">> ");
        return scanner.nextLine().replace('/', '\\');
    }

    /***
     * Static method to find out whether user wants to continue.
     * @return 'true' if user wants to continue and 'false' otherwise.
     */
    public static boolean Continue() {
        System.out.print("If you want to continue write 'y'.\nOr anything else otherwise.\n>> ");
        String answer = scanner.nextLine();
        System.out.println();
        return "y".equals(answer);
    }

    /***
     * Static method to display information about file system:
     * ordered list and concatenated text.
     * @param fileSystem Object of FileSystem class.
     */
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
