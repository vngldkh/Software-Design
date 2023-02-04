import entities.*;

import java.util.Scanner;

public class Main {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        RemotePrint remotePrint = new RemotePrint(new NewConsoleIPrint());
        boolean flag;
        do {
            System.out.println("Select the preferred option:");
            System.out.println("1             - Print.");
            System.out.println("2             - Select printer.");
            System.out.println("Anything else - Exit.");
            try {
                int input = scn.nextLine().charAt(0) - '0';
                flag = true;
                switch (input) {
                    case 1 -> remotePrint.print(scan());
                    case 2 -> remotePrint.setPrinter(choosePrinter());
                    default -> flag = false;
                }
            } catch (Exception ex) {
                flag = false;
            }
        } while (flag);
    }

    private static String scan() {
        System.out.println("Write the text you want to print:");
        return scn.nextLine();
    }

    private static IPrint choosePrinter() {
        System.out.println("Select the printer:");
        System.out.println("1             - File Printer.");
        System.out.println("2             - Old Console Printer.");
        System.out.println("Anything else - New Console Printer.");
        try {
            int input = scn.nextLine().charAt(0) - '0';
            switch (input) {
                case 1 -> {
                    System.out.println("Enter the path:");
                    String path = scn.nextLine();
                    try {
                        var printer = new FileIPrint(path);
                        return printer;
                    } catch (Exception ex) {
                        return new NewConsoleIPrint();
                    }
                }
                case 2 -> {
                    return new OldConsoleIPrint();
                }
                default -> {
                    return new NewConsoleIPrint();
                }
            }
        } catch (Exception ex) {
            return new NewConsoleIPrint();
        }
    }
}