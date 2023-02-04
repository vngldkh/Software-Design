package entities;

public class RemotePrint {
    private IPrint IPrint;

    public RemotePrint(IPrint IPrint) {
        this.IPrint = IPrint;
    }

    public void setPrinter(IPrint IPrint) {
        this.IPrint = IPrint;
    }

    public void print(String text) {
        IPrint.print(text);
    }
}
