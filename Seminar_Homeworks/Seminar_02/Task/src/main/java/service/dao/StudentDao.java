package main.java.service.dao;

import main.java.entities.Student;
import main.java.service.db.JdbcTemplate;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Data access object for main.java.entities.Student. All interaction with the database takes place through this class.
 * This class directly interacts with the {@link JdbcTemplate} class, which in turn refers to the database.
 * This is done to ensure that there are no leaks in the connections.
 *
 * Check hikari.properties in /src/main/resources/ to configure your connection
 */
public class StudentDao {
    private final JdbcTemplate jdbcTemplate;

    /**
     * The method saves a collection of students to the database
     * @param students - collection of {@link Student}
     * @throws SQLException
     */
    public void saveStudent(Collection<Student> students) throws SQLException {
        jdbcTemplate.preparedStatement("insert into st_schema.students(id, name, marks) values (?, ?, ?);",
                insertStudent -> {
                    for (Student student : students){
                        insertStudent.setInt(1, student.getId());
                        insertStudent.setString(2, student.getName());
                        insertStudent.setArray(3, jdbcTemplate.getArray(student.getMarks()));
                        insertStudent.execute();
                    }
                });
    }

    /**
     * The method returns an optional student object for id from the database
     * @param id -- student ID
     * @return {@code Optional<main.java.entities.Student>}
     * @throws SQLException
     */
    public Optional<Student> getStudentById(int id) throws SQLException {
        return jdbcTemplate.preparedStatement("select id, name, marks from st_schema.students where id = ?;",
                selectStudent -> {
                    selectStudent.setInt(1, id);
                    ResultSet resultSet = selectStudent.executeQuery();
                    Student student = null;
                    while (resultSet.next()){
                        student = new Student();
                        student.setId(resultSet.getInt(1));
                        student.setName(resultSet.getString(2));
                        Array arr = resultSet.getArray(3);
                        student.setMarks((Integer[]) arr.getArray());
                    }

                    return Optional.ofNullable(student);
                });
    }

    public void addMarkById(int id, int mark) throws SQLException {
        Optional<Student> studentOptional = getStudentById(id);
        if (studentOptional.isPresent()) {
            studentOptional.get().addMark(mark);
            jdbcTemplate.preparedStatement("UPDATE st_schema.students SET marks = ? WHERE id = ?;",
                    obj -> {
                        obj.setArray(1, jdbcTemplate.getArray(studentOptional.get().getMarks()));
                        obj.setInt(2, id);
                    });
        }
    }

    /**
     * The method returns all users from the database
     * @return all students from the database
     * @throws SQLException
     */
    public List<Student> getAllStudents() throws SQLException {
        return jdbcTemplate.statement(
                selectStudent -> {
                    List<Student> studentList = new ArrayList<>();
                    ResultSet resultSet = selectStudent.executeQuery("select id, name, marks from st_schema.students;");
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setId(resultSet.getInt(1));
                        student.setName(resultSet.getString(2));
                        student.setMarks((Integer[]) resultSet.getArray(3).getArray());
                        studentList.add(student);
                    }

                    return studentList;
                });
    }

    /**
     * This method removes the student with the given id
     * @param id - main.java.entities.Student ID
     * @throws SQLException
     */
    public void deleteById(Long id) throws SQLException{
        jdbcTemplate.preparedStatement("delete from st_schema.students where id = ?;",
                deleteStudent -> {
                    deleteStudent.setLong(1, id);
                    deleteStudent.execute();
                });
    }

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
