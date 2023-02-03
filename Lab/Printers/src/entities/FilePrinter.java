package entities;

import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Printer {
    String path;

    public FilePrinter(String path) {
        this.path = path;
    }

    @Override
    public void print(String text) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("File Printer prints...\n");
            fw.write(text);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Exception occurred during the writing!");
        }
    }
}
