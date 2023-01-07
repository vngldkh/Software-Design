package entities;

import java.util.ArrayList;

record FileInfo(String name, String content, ArrayList<String> dependencies) { }
