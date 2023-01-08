package system;

public class ExceptionMessages {
    private static final String noDirectory = "There's no such directory!\n";
    private static final String noAccess = "You don't have access to files or directories there!\n";
    private static final String cycled = "The graph is cycled!\n";
    private static final String unexpected = "Unexpected exception occurred!\n";

    public static void Message(int code) {
        String message;
        switch (code) {
            case 0 -> message = noDirectory;
            case 1 -> message = noAccess;
            case 2 -> message = cycled;
            default -> message = unexpected;
        }
        System.out.print(message);
    }
}
