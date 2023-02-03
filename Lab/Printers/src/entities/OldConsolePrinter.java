package entities;

public class OldConsolePrinter implements Printer {
    public OldConsolePrinter() { }

    @Override
    public void print(String text) {
        System.out.println("\nNew Console Printer prints... bzz...");
        for (int i = 0; i < text.length(); ++i) {
            char symbol = text.charAt(i);
            System.out.print((char) (symbol - 1));
        }
        System.out.println();
    }
}
