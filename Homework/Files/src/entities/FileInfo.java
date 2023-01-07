package entities;

import java.util.ArrayList;

public record FileInfo(String name, String content, ArrayList<String> dependencies) { }
