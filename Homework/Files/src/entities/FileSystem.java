package entities;

import system.ExceptionMessages;
import system.Reader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/***
 * Class of the file system.
 */
public class FileSystem {
    /***
     * Object of the Reader class.
     * Needed to read information about the file system.
     */
    private final Reader reader;
    /***
     * Graph equal to the file system.
     */
    private final Graph graph;
    /***
     * Ordered list of the file system.
     */
    private LinkedList<String> order;
    /***
     * Pool of the files.
     */
    private final HashMap<String, FileInfo> filesDictionary;
    /***
     * Informs whether the system is correct.
     */
    private boolean correct;

    /***
     * Constructor.
     * 1. Reads the information about the file system. Fill the pool.
     * 2. Builds up equivalent graph.
     * 3. Makes an ordered list (based on the graph).
     * If there's a problem occurred during the construction, user will be informed.
     * @param initDirectory Path to the root folder.
     */
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

    /***
     * Reads the information about the file system (all its files) and fills the pool up.
     */
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

    /***
     * Builds up a graph equivalent to file system.
     */
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

    /***
     * Concatenate files' texts according the ordered list.
     * @return Concatenated text.
     */
    public String MakeText() {
        StringBuilder text = new StringBuilder();
        for (String fileName : order) {
            text.append(filesDictionary.get(fileName).content());
        }
        return text.toString();
    }

    /***
     * Getter to the ordered list.
     * @return Ordered list.
     */
    public LinkedList<String> GetOrder() {
        return order;
    }

    /***
     * Informs the 'external world' whether the file system is correct.
     * @return 'true' if it's correct and 'false' otherwise.
     */
    public boolean IsCorrect() {
        return correct;
    }
}
