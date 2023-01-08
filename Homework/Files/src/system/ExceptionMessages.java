package system;

public class ExceptionMessages {
    // Exception messages text.
    private static final String NO_DIRECTORY = "There's no such directory!\n";
    private static final String NO_ACCESS = "You don't have an access to files or directories there!\n";
    private static final String CYCLED = "The graph is cycled!\n";
    private static final String UNEXPECTED = "Unexpected exception occurred!\n";
    private static final String INCORRECT = "The file system is incorrect!\nImpossible to display!\n";

    /***
     * Inform user about the occurred exception.
     * @param code Code of the occured exception.
     */
    public static void Message(int code) {
        String message;
        switch (code) {
            case 0 -> message = NO_DIRECTORY;
            case 1 -> message = NO_ACCESS;
            case 2 -> message = CYCLED;
            case 3 -> message = INCORRECT;
            default -> message = UNEXPECTED;
        }
        System.out.println(message);
    }
}
