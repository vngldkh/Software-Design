package console;

import entities.FileInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Reader {
    private final File initDirectory;
    private final boolean exists;

    public Reader(String initDirectory) {
        this.initDirectory = new File(initDirectory);
        exists = this.initDirectory.exists();
    }

    public boolean exists() {
        return exists;
    }

    public ArrayList<entities.FileInfo> listAllFiles() {
        ArrayList<entities.FileInfo> files = new ArrayList<>();
        try {
            listAllFiles(initDirectory, files);
        } catch (FileNotFoundException ignored) {
        }
        return files;
    }

    static final Pattern pattern = Pattern.compile("^require '.*'[ \t\n]*$");

    private void listAllFiles(File currentFile, ArrayList<entities.FileInfo> list) throws FileNotFoundException {
        for (File file : currentFile.listFiles()) {
            if (file.isDirectory()) {
                listAllFiles(file, list);
            } else {
                StringBuilder content = new StringBuilder();
                ArrayList<String> dependencies = new ArrayList<>();
                try (Stream<String> stream = Files.lines(file.toPath())) {
                    stream.forEachOrdered((String line) -> {
                        content.append(line).append('\n');
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.matches()) {
                            dependencies.add(line.substring(9, line.lastIndexOf('\'')));
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                list.add(new FileInfo(file.getPath(), content.toString(), dependencies));
            }
        }
    }
}
