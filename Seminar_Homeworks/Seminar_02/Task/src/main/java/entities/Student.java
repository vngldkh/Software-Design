package main.java.entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private final ArrayList<Integer> marks;

    public Student() {
        marks = new ArrayList<>();
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        marks = new ArrayList<>();
    }

    public void addMark(int mark) {
        marks.add(mark);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append(":\t");
        for (int mark : marks) {
            sb.append(mark).append("\t");
        }
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getMarks() {
        return marks;
    }

    public void setMarks(Integer[] arr) {
        for (Integer mark : arr) {
            this.addMark(mark);
        }
    }
}
