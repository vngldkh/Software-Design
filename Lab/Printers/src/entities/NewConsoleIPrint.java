package entities;

public class NewConsoleIPrint implements IPrint {
    public NewConsoleIPrint() { }

    @Override
    public void print(String text) {
        System.out.println("\nNew Console Printer prints...");
        System.out.println(text + '\n');
    }
}
