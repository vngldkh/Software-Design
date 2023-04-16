package main.java;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import main.java.entities.Student;
import main.java.service.dao.StudentDao;
import main.java.service.db.DbInit;
import main.java.service.db.JdbcTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig("src/main/resources/hikari.properties");
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        StudentDao studentDao = new StudentDao(jdbcTemplate);

        Student[] init = {
                new Student(1, "John"),
                new Student(2, "Carl"),
                new Student(3, "Joly"),
                new Student(4, "Felice"),
                new Student(5, "Felix")
        };

        DbInit dbInit = new DbInit(jdbcTemplate);
        try {
            dbInit.create();
            System.out.println();
            System.out.println("Таблица успешно создана!");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
            System.out.println();
            System.out.println("Произошла ошибка инициализации БД");;
            return;
        }
        System.out.println();

        for (var student : init) {
            try {
                studentDao.saveStudent(Collections.singletonList(student));
                System.out.println();
                System.out.println("Студент успешно добавлен!");
                System.out.println();
            } catch (SQLException e) {
                System.out.println("Ошибка добавления пользователя");
                return;
            }
        }


        Scanner console = new Scanner(System.in);
        boolean to_continue = true;
        do {
            System.out.print("> ");
            String command = console.nextLine();
            switch (command) {
                case "/h":
                    System.out.println("1. /r - choose random student");
                    System.out.println("2. /l - list of students with grades");
                    System.out.println("3. /e - exit the program");
                    break;
                case "/l":
                    try {
                        var arr = studentDao.getAllStudents();
                        for (var student : arr) {
                            System.out.println(student.toString());
                        }
                    } catch (SQLException e) {
                        System.out.println("Error occurred during the process of getting the list of students.");
                        return;
                    }
                    break;
                case "/r":
                    int rId = (int) (Math.random() * 4 + 1);
                    try {
                        var student = studentDao.getStudentById(rId);
                        if (student.isEmpty()) {
                            System.out.println("No such student.");
                            return;
                        }
                        System.out.println("Chosen student: " + student.get().getName());
                        System.out.print("Are they present? (y/n)\n> ");
                        String attendance = console.nextLine();
                        if (!attendance.equals("y")) {
                            break;
                        }
                        System.out.print("Mark:\n> ");
                        int mark = console.nextInt();
                        studentDao.addMarkById(rId, mark);
                    } catch (SQLException e) {
                        System.out.println("Impossible to get student.");
                        return;
                    }
                    break;
                case "/e":
                    to_continue = false;
                    break;
                default:
                    System.out.println("No such command!");
                    break;
            }
        } while (to_continue);
    }
}