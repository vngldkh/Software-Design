package system;

import entities.FileInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Reader {
    private final File initDirectory;
    private boolean correct;

    public Reader(String initDirectory) {
        this.initDirectory = new File(initDirectory);
        correct = this.initDirectory.exists();
    }

    public boolean IsCorrect() {
        return correct;
    }

    public ArrayList<entities.FileInfo> listAllFiles() {
        ArrayList<entities.FileInfo> files = new ArrayList<>();
        try {
            listAllFiles(initDirectory, files);
        } catch (IOException e) {
            ExceptionMessages.Message(1);
            correct = false;
        } catch (NullPointerException e) {
            ExceptionMessages.Message(0);
            correct = false;
        }
        return files;
    }

    static final Pattern pattern = Pattern.compile("^[ \\t\\n]*require ‘(.*)’[ \\t\\n]*$");

    private void listAllFiles(File currentFile, ArrayList<entities.FileInfo> list)
            throws NullPointerException, IOException {
        for (File file : currentFile.listFiles()) {
            if (file.isDirectory()) {
                listAllFiles(file, list);
            } else {
                StringBuilder content = new StringBuilder();
                ArrayList<String> dependencies = new ArrayList<>();
                Stream<String> stream = Files.lines(file.toPath());
                stream.forEachOrdered((String line) -> {
                    content.append(line).append('\n');
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches()) {
                        dependencies.add(matcher.group(1).replace('/', '\\'));
                    }
                });
                stream.close();
                list.add(new FileInfo(file.getPath().substring(initDirectory.getPath().length() + 1),
                         content.toString(), dependencies));
            }
        }
    }
}
