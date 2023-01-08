import entities.FileSystem;
import system.Console;

public class Main {
    public static void main(String[] args) {
        do {
            String initialDirectory = Console.Init();
            FileSystem fileSystem = new FileSystem(initialDirectory);
            Console.DisplayFileSystem(fileSystem);
        } while (Console.Continue());
    }
}