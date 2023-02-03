package entities;

public class RemotePrint {
    private Printer printer;

    public RemotePrint(Printer printer) {
        this.printer = printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public void print(String text) {
        printer.print(text);
    }
}
