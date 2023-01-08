package entities;

import java.util.ArrayList;

/***
 * Record contains information about the file.
 * @param name Name of the file.
 * @param content File content.
 * @param dependencies The names of the files on which this file depends.
 */
public record FileInfo(String name, String content, ArrayList<String> dependencies) { }
