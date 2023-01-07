package entities;

import java.util.ArrayList;

record File (String name, String content, ArrayList<String> dependencies) { }
