package entities;

import system.ExceptionMessages;
import system.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FileSystem {
    private final Reader reader;
    private final Graph graph;
    private LinkedList<String> order;
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
        correct = !graph.Cycled();
        if (!correct) {
            ExceptionMessages.Message(2);
            return;
        }
        order = graph.Order();
    }

    private void ListAllFiles() {
        var files = reader.ListAllFiles();
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
                    graph.Add(child, pair.getKey());
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

    public LinkedList<String> GetOrder() {
        return order;
    }

    public boolean IsCorrect() {
        return correct;
    }
}
