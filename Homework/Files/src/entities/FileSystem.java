package entities;

import system.ExceptionMessages;
import system.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileSystem {
    private final Reader reader;
    private final Graph graph;
    private ArrayList<String> order;
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
        ListAllFiles();
        if (!correct) {
            return;
        }
        SetUpGraph();
        order = graph.Order();
    }

    private void ListAllFiles() {
        var files = reader.listAllFiles();
        correct = reader.IsCorrect();
        if (!correct) {
            return;
        }
        for (FileInfo file : files) {
            filesDictionary.put(file.name(), file);
        }
    }

    private void SetUpGraph() {
        for (Map.Entry<String, FileInfo> pair : filesDictionary.entrySet()) {
            graph.Add(pair.getKey());
            for (String child : pair.getValue().dependencies()) {
                if (filesDictionary.containsKey(child)) {
                    graph.Add(pair.getKey(), child);
                }
                correct = !graph.Cycled();
                if (!correct) {
                    ExceptionMessages.Message(2);
                    return;
                }
            }
        }
    }

    public String MakeText() {
        StringBuilder text = new StringBuilder();
        for (String fileName : order) {
            text.append(filesDictionary.get(fileName).content());
        }
        return text.toString();
    }

    public ArrayList<String> GetOrder() {
        return order;
    }

    public boolean IsCorrect() {
        return correct;
    }
}
