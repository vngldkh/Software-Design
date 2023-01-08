package entities;

import console.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileSystem {
    private final Reader reader;
    private final Graph graph;
    private final HashMap<String, FileInfo> filesDictionary;
    private boolean correct;

    public FileSystem(String initDirectory) {
        reader = new Reader(initDirectory);
        graph = new Graph();
        filesDictionary = new HashMap<>();
        correct = reader.IsCorrect();
        if (!correct) {
            return;
        }
        listAllFiles();
        if (!correct) {
            return;
        }
        setUpGraph();
    }

    private void listAllFiles() {
        var files = reader.listAllFiles();
        correct = reader.IsCorrect();
        if (!correct) {
            return;
        }
        for (FileInfo file : files) {
            filesDictionary.put(file.name(), file);
        }
    }

    private void setUpGraph() {
        for (Map.Entry<String, FileInfo> pair : filesDictionary.entrySet()) {
            for (String child : pair.getValue().dependencies()) {
                if (filesDictionary.containsKey(child)) {
                    graph.Add(pair.getKey(), child);
                }
                correct = !graph.Cycled();
                if (!correct) {
                    // Сообщение об ошибке.
                    return;
                }
            }
        }
    }

    public String MakeText() {
        ArrayList<String> order = graph.Order();
        StringBuilder text = new StringBuilder();
        for (String fileName : order) {
            text.append(filesDictionary.get(fileName).content());
        }
        return text.toString();
    }
}
