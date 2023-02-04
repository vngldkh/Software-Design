package entities;

public class OldConsoleIPrint implements IPrint {
    public OldConsoleIPrint() { }

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
