package entities;

public class NewConsolePrinter implements Printer {
    public NewConsolePrinter() { }

    @Override
    public void print(String text) {
        System.out.println("\nNew Console Printer prints...");
        System.out.println(text + '\n');
    }
}
