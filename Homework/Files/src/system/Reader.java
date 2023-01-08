package system;

import entities.FileInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/***
 * Class read the information of the file system.
 */
public class Reader {
    /***
     * Root folder.
     */
    private final File initDirectory;
    /***
     * Shows whether the read file system is correct.
     */
    private boolean correct;

    /***
     * Constructor of the reader class object.
     * @param initDirectory Path to the file system's root folder.
     */
    public Reader(String initDirectory) {
        this.initDirectory = new File(initDirectory);
        correct = this.initDirectory.exists();
    }

    /***
     * Inform the 'external world' about the read file system state.
     * @return Read file system state.
     */
    public boolean IsCorrect() {
        return correct;
    }

    /***
     * List all files from the root directory and its subdirectories.
     * @return ArrayList of the read files.
     */
    public ArrayList<entities.FileInfo> ListAllFiles() {
        ArrayList<entities.FileInfo> files = new ArrayList<>();
        try {
            ListAllFiles(initDirectory, files);
        } catch (IOException e) {
            ExceptionMessages.Message(1);
            correct = false;
        } catch (NullPointerException e) {
            ExceptionMessages.Message(0);
            correct = false;
        }
        return files;
    }

    /***
     * Pattern for 'dependency line'.
     */
    static final Pattern pattern = Pattern.compile("^[ \\t\\n]*require ‘(.*)’[ \\t\\n]*$");

    /***
     * Recursive method that lists all files from the current directory.
     * @param currentFile Current file or directory.
     * @param list Listed files.
     * @throws NullPointerException Possible only if there's no root folder.
     * @throws IOException Throws if user has no permission.
     */
    private void ListAllFiles(File currentFile, ArrayList<entities.FileInfo> list)
            throws NullPointerException, IOException {
        for (File file : currentFile.listFiles()) {
            if (file.isDirectory()) {
                ListAllFiles(file, list);
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
